/**
 * 
 */
package com.jialian.platform.controller.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.MaterialAttribute;
import com.jialian.api.domain.entity.MaterialPrice;
import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.ShopOrder;
import com.jialian.api.domain.entity.ShopProdType;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Material.MaterialAttributeServiceApi;
import com.jialian.api.service.Material.MaterialPriceServiceApi;
import com.jialian.api.service.Material.MaterialServiceApi;
import com.jialian.api.service.Order.OrderTrackServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.api.service.Shop.ShopIndexServiceApi;
import com.jialian.api.service.Shop.ShopOrderServiceApi;
import com.jialian.api.service.Shop.ShopProdTypeServiceApi;
import com.jialian.common.custom.RequestBean;

/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月20日 下午1:59:05  */
/**
 * @Description:
 * @Author: zhls 联系方式：zhls1992@qq.com
 * @Since:2016年1月20日 下午1:59:05
 * @Version:1.0
 */
@Controller
@RequestMapping("/shop")
public class ShopManageController {

	@Resource
	private ShopProdTypeServiceApi shopProdTypeServiceApi;
	@Resource
	private IdWorkerServiceApi idWorkerServiceApi;
    @Resource
    private ShopIndexServiceApi shopIndexServiceApi;
    @Resource
    private MaterialServiceApi materialServiceApi;
    @Resource
    private MaterialPriceServiceApi materialPriceServiceApi;
    @Resource 
    private MaterialAttributeServiceApi materialAttributeServiceApi;

    @Resource
    private ResourceInfoServiceApi resourceInfoService;
    @Resource
    private ShopOrderServiceApi shopOrderServiceApi;

    @Resource
    private OrderTrackServiceApi orderTrackService;
	/**
	  * @Title: getShopMenu
	  * @Description: 获取商城菜单
	  * @param typeId 一级菜单id
	  * @param type 菜单类型 1是1级菜单2是2级菜单
	  * @param pageSize
	  * @param pageIndex
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月20日 下午2:19:35
	 */
	@RequestMapping("/getShopMenu.do")
	@ResponseBody
	public JsonResult getShopMenu(@RequestBean Long typeId,@RequestBean  Integer type,@RequestBean  Integer pageSize,@RequestBean  Integer pageIndex,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		if(pageSize==null||pageSize<0)pageSize=5;
		if(pageIndex==null||pageIndex<=0)pageIndex=1;
		// 判断参数是否合法
		if (type == null||(typeId != null && typeId < 0)) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			Where where = new Where(pageSize,pageIndex,"id");
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("rank", type);
			if(typeId!=null && typeId!=0){
				criteria.andEqualTo("supid", typeId);
			}
			List<ShopProdType> shopProdTypelist = shopProdTypeServiceApi.selectByWhere(where);
			int sum = shopProdTypeServiceApi.countByWhere(where);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("查询成功");
			jsonResult.addData("shopProdTypelist", shopProdTypelist);
			jsonResult.addData("totalItems", sum);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}

		return jsonResult;
	}
	/**
	  * @Title: saveShopMenu
	  * @Description: 保存菜单名称
	  * @param id
	  * @param sptname
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月20日 下午4:50:33
	 */
	@RequestMapping("/saveShopMenu.do")
	@ResponseBody
	public JsonResult saveShopMenu(@RequestBean Long id,@RequestBean  String sptname,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (id == null||sptname == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			ShopProdType spt = shopProdTypeServiceApi.selectByPrimaryKey(id);
			if(spt!=null){
				spt.setSptypename(sptname);
				int flag = shopProdTypeServiceApi.updateByPrimaryKeySelective(spt);
				if(flag == 1){
					jsonResult.setSuccess(true);
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					jsonResult.setMessage("保存成功");
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_CODE);
					jsonResult.setMessage("保存失败");
				}
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setMessage("记录不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: deleteShopProdTypeById
	  * @Description: 删除商品分类
	  * @param id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月20日 下午4:53:04
	 */
	@RequestMapping("/deleteShopProdTypeById.do")
	@ResponseBody
	public JsonResult deleteShopProdTypeById(@RequestBean Long id,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (id == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			
			int flag = shopProdTypeServiceApi.deleteByPrimaryKey(id);
			if(flag == 1){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("删除成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setMessage("删除失败");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: addShopProdType
	  * @Description: 添加商品分类
	  * @param id 一级菜单id
	  * @param sptname 菜单名称
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月20日 下午5:00:50
	 */
	@RequestMapping("/addShopProdType.do")
	@ResponseBody
	public JsonResult addShopProdType(@RequestBean Long id,@RequestBean String sptname,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (sptname == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			Date createtime = new Date();
			String sptno = "SPT"+idWorkerServiceApi.nextId();
			ShopProdType spt = new ShopProdType();
			
			spt.setCreatetime(createtime);
			spt.setSptypename(sptname);
			spt.setSptypeno(sptno);
			if(id!=null&&id!=0){
				spt.setSupid(id);
				spt.setRank(2);
			}else{
				spt.setRank(1);
			}
			
			int flag = shopProdTypeServiceApi.insertSelective(spt);
			if(flag == 1){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("添加成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setMessage("添加失败");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: getShopProdList
	  * @Description: 商品管理获取商品list
	  * @param pname 商品名称
	  * @param pno 商品编号
	  * @param type 商品类型
	  * @param pubstatus 发布状态
	  * @param recommend 推荐位置
	  * @param pageSize
	  * @param pageIndex
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月21日 下午3:08:59
	 */
	@RequestMapping("/getShopProdList.do")
	@ResponseBody
	public JsonResult getShopProdList(@RequestBean String pname,@RequestBean String pno, @RequestBean Long type, @RequestBean Integer pubstatus, @RequestBean Integer recommend, @RequestBean Integer pageSize, @RequestBean Integer pageIndex, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		
		try {
			ServiceResult<Object> result = shopIndexServiceApi.getShopProdList(pname,pno,type,pubstatus,recommend,pageSize,pageIndex);
			jsonResult.addData("pageData",result.getData());
			jsonResult.setSuccess(result.isOk());
			jsonResult.setStateCode(result.getMsgCode());
			jsonResult.setComment(result.getComment());
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: deleteShopProdById
	  * @Description: 删除商品
	  * @param id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月21日 下午6:24:40
	 */
	@RequestMapping("/deleteShopProdById.do")
	@ResponseBody
	public JsonResult deleteShopProdById(@RequestBean Long id,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (id == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			Material ma = materialServiceApi.selectByPrimaryKey(id);
			if(ma!=null){
				ma.setStatus((short) 0);
				int flag = materialServiceApi.updateByPrimaryKey(ma);
				if(flag == 1){
					jsonResult.setSuccess(true);
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					jsonResult.setMessage("删除成功");
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_CODE);
					jsonResult.setMessage("删除失败");
				}
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setMessage("记录不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: getShopProdById
	  * @Description: 获取商品
	  * @param id
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月21日 下午7:06:52
	 */
	@RequestMapping("/getShopProdById.do")
	@ResponseBody
	public JsonResult getShopProdById(@RequestBean Long id,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (id == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			Material ma = materialServiceApi.selectByPrimaryKey(id);
			if(ma!=null){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("查询成功");
				jsonResult.addData("ma", ma);
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setMessage("记录不存在");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}

	/**
	  * @Title: saveShopProdAttr
 * @Description: 保存商品属性
	  * @param shopProdAttr
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月22日 下午2:39:41
	 */
	@RequestMapping("/saveShopProdAttr.do")
	@ResponseBody
	public JsonResult saveShopProdAttr(@RequestBean MaterialAttribute shopProdAttr,@RequestBean Double price,
			HttpServletRequest request) {
		JsonResult result = new JsonResult();
		if(price==null)price=0.0;
		if(shopProdAttr.getId() == null || shopProdAttr.getAttributeName() == null || shopProdAttr.getInitialInventory() == null || 
			shopProdAttr.getCurrentInventory() == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数出错");
			return result;
		}
		Date createTime = new Date();
		//添加
		if(shopProdAttr.getId() == 0){
			//价格
			MaterialPrice mp = new MaterialPrice();
			mp.setCreateTime(createTime);
			mp.setCurrency("CNY");
			mp.setMaxPrice(price);
			mp.setMinPrice(price);
			mp.setOrigPrice(price);
			mp.setPrice(price);
			mp.setPriceType(0.0);
			mp.setSpecialPrice(price);
			mp.setStatus((short) 1);
			mp.setUpdateTime(createTime);
			mp=materialPriceServiceApi.insertSelective(mp);
			
			MaterialAttribute ma = new MaterialAttribute();
			
			ma.setAttributeName(shopProdAttr.getAttributeName());
			String attributeNo = "MA"+idWorkerServiceApi.nextId();
			ma.setAttributeNo(attributeNo );
			ma.setAttributeType((short) 1);
			ma.setCreateTime(createTime);
			ma.setCurrentInventory(shopProdAttr.getCurrentInventory());
			ma.setInitialInventory(shopProdAttr.getInitialInventory());
			ma.setPriceId(mp.getId());
			ma.setMaterialId(shopProdAttr.getMaterialId());
			ma.setSfkf(shopProdAttr.getSfkf());
			materialAttributeServiceApi.insertSelective(ma);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setSuccess(true);
			result.setMessage("添加成功");
		//修改
		}else{
			MaterialPrice mp = shopProdAttr.getPrice();
			if(mp.getPrice()!=price){
				mp.setPrice(price);
				mp.setUpdateTime(createTime);
				materialPriceServiceApi.updateByPrimaryKeySelective(mp);
				shopProdAttr.setPrice(mp);
			}
			shopProdAttr.setUpdateTime(createTime);
			materialAttributeServiceApi.updateByPrimaryKeySelective(shopProdAttr);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setSuccess(true);
			result.setMessage("保存成功");
		}
		return result;
	}
	/**
	  * @Title: updateShopProdAttrById
	  * @Description: 删除商品属性
	  * @param id
	  * @param status 0
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月22日 下午4:42:14
	 */
	@RequestMapping("/deleteShopProdAttrById.do")
	@ResponseBody
	public JsonResult deleteShopProdAttrById(@RequestBean Long id,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (id == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			
			int flag=materialAttributeServiceApi.deleteByPrimaryKey(id);
			if(flag==1){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("删除成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setMessage("删除失败");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: addShopProd
	  * @Description: 编辑商品
	  * @param shopProd
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月22日 下午7:00:19
	 */
	@RequestMapping("/addShopProd.do")
	@ResponseBody
	public JsonResult addShopProd(@RequestBean Material shopProd,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (shopProd == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			
			if(shopProd.getId() != 0){//更新
				if(shopProd.getStatus()==2){
					shopProd.setStatus((short) 1);
				}
				materialServiceApi.updateByPrimaryKeySelective(shopProd);
			}else{//插入
				shopProd = materialServiceApi.insert(shopProd);
			}
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("操作成功");
			jsonResult.addData("shopProd", shopProd);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: getShopProdByStatus
	  * @Description: 根据状态查找商品
	  * @param status
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月22日 下午7:05:05
	 */
	@RequestMapping("/getShopProdByStatus.do")
	@ResponseBody
	public JsonResult getShopProdByStatus(@RequestBean Short status,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (status == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("status", status);
			criteria.andEqualTo("material_type", 3);
			List<Material> lma = materialServiceApi.selectByWhereWithBLOBs(where);
			if(lma!=null&&lma.size()>0){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("查询成功");
				jsonResult.addData("ma", lma.get(0));
			}else{
				Material ma = new Material();
				
				String materialNo = "MATERIAL"+idWorkerServiceApi.nextId();
				ma.setMaterialNo(materialNo);
				ma.setStatus(status);
				ma.setMaterialType((short)3);
				ma = materialServiceApi.insertSelective(ma);
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("创建成功");
				jsonResult.addData("ma", ma);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}

	
	/**
	 * 商品管理>>广告大图设置
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月12日 下午3:55:46
	 *@Version:1.0
	 */
	@RequestMapping("/selectResourceInfoByNo.do")
	@ResponseBody
	public JsonResult selectResourceInfoByNo(){
		JsonResult jsonResult = new JsonResult();
		Where where = new Where();
		where.setOrderByClause("no");
		Criteria criteria = where.createCriteria();
		List<Object> list = new ArrayList<Object>();
		list.add("SHOP0001");
		list.add("SHOP0002");
		list.add("SHOP0003");
		list.add("SHOP0004");
		list.add("SHOP0005");
		
		criteria.andIn("no", list);
		List<ResourceInfo> result = resourceInfoService.selectByWhere(where);
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setMessage("查询成功");
		if (result != null) {
			jsonResult.addData("shopImgList", result);
			jsonResult.setDataObj(result.size());
		} else {
			jsonResult.setDataObj(0);
		}
		return jsonResult;
	}
	
	/**
	  * @Title: getShopOrderList
	  * @Description: 获取订单列表
	  * @param orderno  订单编号
	  * @param orderstatus 订单状态
	  * @param telephone 手机号
	  * @param stime 开始时间
	  * @param etime 结束时间
	  * @param pageSize
	  * @param pageIndex
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月23日 下午5:30:42
	 */
	@RequestMapping("/getShopOrderList.do")
	@ResponseBody
	public JsonResult getShopOrderList(@RequestBean String orderno,@RequestBean Short orderstatus, @RequestBean String telephone, @RequestBean String stime, @RequestBean String etime, @RequestBean Integer pageSize, @RequestBean Integer pageIndex, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		try {
			ServiceResult<Object> result = shopOrderServiceApi.getShopOrderList(orderno,orderstatus,telephone,stime,etime,pageSize,pageIndex);
			jsonResult.addData("pageData",result.getDataMap());
			jsonResult.setSuccess(result.isOk());
			jsonResult.setStateCode(result.getMsgCode());
			jsonResult.setComment(result.getComment());
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	
	/**
	 * 商城订单详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月25日 下午12:01:03
	 *@Version:1.0
	 */
	@RequestMapping("/getShopOrderDetail.do")
	@ResponseBody
	public JsonResult getShopOrderDetail(@RequestBean Long orderId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(orderId == null || orderId < 0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
			return jsonResult;
		}
		try{
			ShopOrder result = shopOrderServiceApi.selectByPrimaryKey(orderId);
			if(result!=null){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setComment("成功");
				jsonResult.addData("shopOrder", result);
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setComment("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		return jsonResult;
	}
	
	/**
	 * 
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月25日 下午3:17:05
	 *@Version:1.0
	 */
	@RequestMapping("/getOrderTrackList.do")
	@ResponseBody
	public JsonResult getOrderTrackList(@RequestBean Long orderId, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		try{
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("order_id", orderId);
			criteria.andEqualTo("track_order_type", 2);
			criteria.andEqualTo("status", 1);
			List<OrderTrack> orderTracks = orderTrackService.selectByWhere(where);
			if(orderTracks!=null){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setComment("成功");
				jsonResult.addData("orderTracks", orderTracks);
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setComment("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	
	/**
	  * @Title: saveShopOrder
	  * @Description: 保存商城订单
	  * @param shopOrder
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月25日 下午3:00:51
	 */
	@RequestMapping("/saveShopOrder.do")
	@ResponseBody
	public JsonResult saveShopOrder(@RequestBean ShopOrder shopOrder,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (shopOrder == null || shopOrder.getId() == 0) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			shopOrderServiceApi.updateByPrimaryKeySelective(shopOrder);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("操作成功");
			jsonResult.addData("shopOrder", shopOrder);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	/**
	  * @Title: addOrderTrack
	  * @Description: 添加订单跟踪记录
	  * @param orderTrack
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月25日 下午3:07:02
	 */
	@RequestMapping("/addOrderTrack.do")
	@ResponseBody
	public JsonResult addOrderTrack(@RequestBean OrderTrack orderTrack,
			HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 判断参数是否合法
		if (orderTrack == null) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		try {
			Date nowdate = new Date(); 
			orderTrack.setCreatTime(nowdate);
			orderTrack.setTrackTime(nowdate);
			orderTrack.setUpdateTime(nowdate);
			orderTrack.setTrackOrderType((short)2);
			orderTrack=orderTrackService.insertSelective(orderTrack);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("操作成功");
			jsonResult.addData("orderTrack", orderTrack);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("服务异常");
		}
		
		return jsonResult;
	}
	
	/**
	 * 删除跟踪状态
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月25日 下午6:11:25
	 *@Version:1.0
	 */
	@RequestMapping("/deleteOrdertrack.do")
	@ResponseBody
	public JsonResult deleteOrdertrack(@RequestBean Long id){
		JsonResult jsonResult = new JsonResult();
		if (id == null || id < 0) {
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不合法");
			return jsonResult;
		}
		OrderTrack orderTrack = orderTrackService.selectByPrimaryKey(id); 
		if(orderTrack == null ){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("此数据已删除或不存在");
			return jsonResult;
		}
		orderTrack.setStatus(0l);
		int result = orderTrackService.updateByPrimaryKeySelective(orderTrack);
		if(result > 0){
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("删除成功");
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("删除失败");
		}
		return jsonResult;
	}
}
