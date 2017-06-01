package com.jialian.platform.controller.combo;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.Combo;
import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.Item;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.para.ComboInfoPara;
import com.jialian.api.domain.para.ResourceInfoReplacePara;
import com.jialian.api.domain.query.ComboQuery;
import com.jialian.api.domain.vo.ComboComplexVO;
import com.jialian.api.service.Combo.ComboInfoServiceApi;
import com.jialian.api.service.Combo.ComboServiceApi;
import com.jialian.api.service.Item.ItemServiceApi;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.platform.controller.BaseController;

@Controller
@RequestMapping("/combo")
public class ComboController extends BaseController{

	@Resource
	private ComboServiceApi comboService;
	
	@Resource
	private ComboInfoServiceApi comboInfoService;
	
	@Resource
	private ItemServiceApi itemService;
	
	@Resource
	private IdWorkerServiceApi idWorker;
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private LogServiceApi logService;
	/**
	 * @Description: 根据条件查询套餐信息，带分页
	 * @Param:@param startIndex 起始查询位移偏量
	 * @Param:@param pageSize 需要查询的页数
	 * @Param:@return JsonResult
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月11日上午10:20:30
	 * @Version:1.0
	 */
	@RequestMapping("/comboList.do")
	@ResponseBody
	public JsonResult selectComboListByWhere(@RequestBean Combo combo,
			@RequestParam(value = "startIndex", required = false, defaultValue = "0") int startIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
		JsonResult result = new JsonResult();
		Where where = new Where();
		where.setOffset(pageSize);
		where.setLimit(startIndex);
		Criteria criteria = where.createCriteria();
		if(combo.getName() != null || !"".equals(combo.getName())){
			criteria.andLike("name", "%" +combo.getName().trim() +"%" );
		}
		if(combo.getNo() != null && !"".equals(combo.getNo())){
			criteria.andLike("no", "%" +combo.getNo().trim() +"%" );
		}
		criteria.andEqualTo("status", 1);
		List<Combo> comboList = comboService.selectByWhere(where);
		int totalItems = comboService.countByWhere(where);
		result.setSuccess(true);
		result.setMessage("查询成功！");
		result.addData("comboList", comboList);
		result.addData("totalItems", totalItems);
		return result;
	}
	
	/**
	 * 根据ID查询套餐的复杂数据
	 * @Description:
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月7日下午2:48:54
	 * @Version:1.0
	 */
	@RequestMapping("/getComplexComboById.do")
	@ResponseBody
	public JsonResult getComplexComboById(Long id){
		JsonResult result = new JsonResult();
		if(id == null){
			result.setSuccess(false);
			result.setMessage("参数错误");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		} 
		ComboComplexVO combo = comboService.selectComplexComboByPrimaryKey(id);
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.setStateCode(Const.SUCCESS_CODE);
		result.addData("combo", combo);
		return result;
	}
	/**
	 * @Description:获取套餐详情
	 * @Param:@param id 套餐主键的ID
	 * @Param:@return JsonResult
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月11日下午1:49:52
	 * @Version:1.0
	 */
	@RequestMapping("/selectComboInfoListByParentId.do")
	@ResponseBody
	public JsonResult selectComboInfoListByParentId(Long id){
		JsonResult result = new JsonResult();
		//对参数进行校验
		if(id == null || id <= 0){
			result.setSuccess(false);
			result.setMessage("参数错误");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
		//组装查询条件
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("combo_id", id);
		List<ComboInfo> comboInfoList = comboInfoService.selectByWhere(where);
		//结果返回
		result.setSuccess(true);
		result.setMessage("查询成功");
		result.setStateCode(Const.SUCCESS_CODE);
		result.addData("comboInfoList", comboInfoList);
		return result;
	}
	
	/**
	 * @Description:根据套餐ID删除套餐信息
	 * @Param:@param id
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月11日下午6:13:51
	 * @Version:1.0
	 */
	@RequestMapping("/deleteComboById.do")
	@ResponseBody
	public JsonResult deleteComboById(Long id){
		JsonResult result = new JsonResult();
		//对参数进行校验
		if(id == null || id <= 0){
			result.setSuccess(false);
			result.setMessage("参数错误");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
		//查询该条记录
		Combo combo = comboService.selectByPrimaryKey(id);
		if(combo == null || combo.getStatus() == 0){
			result.setSuccess(true);
			result.setMessage("该记录已被删除");
			result.setStateCode(Const.ERROR_RECORD_DELETED);
			return result;
		}else{
			combo.setStatus((short)0);
			combo.setUpdateTime(new Date());
			comboService.updateByPrimaryKeySelective(combo);
			result.setSuccess(true);
			result.setMessage("该记录已成功被删除");
			result.setStateCode(Const.SUCCESS_CODE);
			
			AdminUser user = getAdminUser(getRequest());
			logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-》套餐管理", "删除了套餐");
			
			return result;
		}
		
	}
	
	
	/**
	* @Description: 套餐信息修改
	* @param @param combo
	* @param @param comboInfo
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月21日 下午4:17:50
	 */
	@RequestMapping("/updateCombo.do")
	@ResponseBody
	public JsonResult updateCombo(Combo combo,ComboInfo comboInfo){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Object> result=comboService.updateCombo(combo, comboInfo);
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setSuccess(result.isOk());
		jsonResult.setMessage(result.getComment());
		AdminUser user = getAdminUser(getRequest());
		logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-》套餐管理", "修改了套餐信息");
		return jsonResult;
	}
	
	
	/**
	* @Description: 套餐信息列表（分页）
	* @param @param query
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月4日 下午3:45:40
	 */
	@RequestMapping("/getComboList.do")
	public JsonResult getComboList(ComboQuery query){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<Combo>> result=comboService.getComboListByQuery(query);
		jsonResult.setStateCode(result.getMsgCode());
		if(result.isOk()){
			jsonResult.setSuccess(true);
			jsonResult.setDataObj(result.getData());
		}
		return jsonResult;
	}
	
	
	/**
	* @Description: 套餐信息添加
	* @param @param combo
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月4日 下午4:14:24
	 */
	@RequestMapping("/addCombo.do")
	@ResponseBody
	public JsonResult addCombo(Combo combo){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Object> result=comboService.addCombo(combo);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		AdminUser user = getAdminUser(getRequest());
		logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "装修专区-》套餐管理", "添加了套餐信息");
		return jsonResult;
	}
	
	/**
	 * @Description: 保存套餐详情
	 * @Param:@param comboInfo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月8日下午3:31:29
	 * @Version:1.0
	 */
	@RequestMapping("/saveComboInfo.do")
	@ResponseBody
	public JsonResult saveComboInfo(@RequestBean ComboInfoPara comboInfo){
		JsonResult result = new JsonResult();
		if(comboInfo.getId() == null || comboInfo.getComboId() == null || comboInfo.gethStrCatId() == null || 
			comboInfo.getItemIntroduction() == null || comboInfo.getItemName() == null || 
			comboInfo.getWorkArea() == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数出错");
		}
		//添加
		if(comboInfo.getId() == 0){
			Item item = new Item();
			item.setItemNo("ITEM" + idWorker.nextId());
			item.setItemName(comboInfo.getItemName());
			item.setStatus((short)1);
			item.setRemark("套餐项目");
			item.setHouseStructureCategoryId(comboInfo.gethStrCatId());
			item = itemService.addItem(item);
			ComboInfo cInfo = new ComboInfo();
			cInfo.setItemId(item.getId());
			cInfo.setRemark("套餐详情");
			cInfo.setStatus((short) 1);
			cInfo.setComboId(comboInfo.getComboId());
			cInfo.setItemIntroduction(comboInfo.getItemIntroduction());
			cInfo.setWorkArea(comboInfo.getWorkArea());
			comboInfoService.insertSelective(cInfo);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setSuccess(true);
			result.setMessage("添加成功");
		//修改
		}else{
			Item item = new Item();
			item.setId(comboInfo.getItemId());
			item.setItemName(comboInfo.getItemName());
			item.setHouseStructureCategoryId(comboInfo.gethStrCatId());
			itemService.updateByPrimaryKeySelective(item);
			ComboInfo cInfo = new ComboInfo();
			cInfo.setId(comboInfo.getId());
			cInfo.setItemIntroduction(comboInfo.getItemIntroduction());
			cInfo.setWorkArea(comboInfo.getWorkArea());
			comboInfoService.updateByPrimaryKeySelective(cInfo);
			result.setStateCode(Const.SUCCESS_CODE);
			result.setSuccess(true);
			result.setMessage("保存成功");
		}
		return result;
		
	}
	
	/**
	 * @Description:根据ID更新套餐信息
	 * @Param:@param comboInfo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月8日下午4:17:09
	 * @Version:1.0
	 */
	@RequestMapping("/updateComboInfoById.do")
	@ResponseBody
	public JsonResult updateComboInfoById(@RequestBean ComboInfo comboInfo){
		JsonResult result = new JsonResult();
		if(comboInfo.getId() == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		comboInfoService.updateByPrimaryKeySelective(comboInfo);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("修改成功");
		return result;
	}
	
	/**
	 * 修改套餐主表
	 * @Description:
	 * @Param:@param combo
	 * @Param:@param reosurceInfo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月9日下午3:15:41
	 * @Version:1.0
	 */
	@RequestMapping("/updateComboAndResourceInfo.do")
	@ResponseBody
	public JsonResult updateComboAndResourceInfo(@RequestBean Combo combo, @RequestBean ResourceInfoReplacePara resourceInfo){
		JsonResult result = new JsonResult();
		if(combo.getId() == null){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		if(resourceInfo != null){
			if(resourceInfo.getFromId() == null || resourceInfo.getFromNo() == null ||
					resourceInfo.getTargetId() == null || resourceInfo.getTargetNo() == null){
				result.setStateCode(Const.ERROR_PARAM_CODE);
				result.setSuccess(false);
				result.setMessage("参数错误");
				return result;
			}
			ResourceInfo resInf = new ResourceInfo();
			resInf.setId(resourceInfo.getFromId());
			resInf.setNo(resourceInfo.getTargetNo());
			resourceInfoService.updateByPrimaryKeySelective(resInf);
			resInf.setId(resourceInfo.getTargetId());
			resInf.setNo(resourceInfo.getFromNo());
			resourceInfoService.updateByPrimaryKeySelective(resInf);
		}
		comboService.updateByPrimaryKeySelective(combo);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("修改成功");
		return result;
	}
	
	/**
	 * @Description:添加套餐
	 * @Param:@param combo
	 * @Param:@param resourceInfo
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月9日下午7:16:02
	 * @Version:1.0
	 */
	@RequestMapping("/addComboAndResourceInfo.do")
	@ResponseBody
	public JsonResult addComboAndResourceInfo(@RequestBean Combo combo, @RequestBean ResourceInfo resourceInfo){
		JsonResult result = new JsonResult();
		if(combo.getId() != null || combo.getName() == null || combo.getName().equals("") ||
				combo.getPrice() == null || "".equals(combo.getPrice())){
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setSuccess(false);
			result.setMessage("参数错误");
			return result;
		}
		combo.setNo("TC" + idWorker.nextId());
		combo = comboService.insertSelective(combo);
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("status", 1);
		criteria.andEqualTo("combo_id", 0);
		ComboInfo comboInfo = new ComboInfo();
		comboInfo.setComboId(combo.getId());
		comboInfoService.updateByWhereSelective(comboInfo, where);
		resourceInfo.setObjectId(combo.getId());
		resourceInfo.setNo(combo.getNo());
		resourceInfoService.updateByPrimaryKeySelective(resourceInfo);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.setMessage("添加套餐成功");
		return result;
	}
	
	/**
	 * 查询套餐名称
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月18日 上午10:47:21
	 *@Version:1.0
	 */
	@RequestMapping("/selectComboList.do")
	@ResponseBody
	public JsonResult selectComboList(){
		JsonResult jsonResult = new JsonResult();
		List<Combo> comboList = comboService.selectComboList();
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.addData("comboList", comboList);
		jsonResult.setMessage("查询成功");
		return jsonResult;
	}
}
