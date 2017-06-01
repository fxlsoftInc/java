/**
 * 
 */
package com.jialian.core.service.impl.Shop;

/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月8日 下午3:43:41  */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.ShopProdType;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Shop.ShopIndexServiceApi;
import com.jialian.common.util.StringUtils;
import com.jialian.core.persistence.reader.MaterialReaderMapper;
import com.jialian.core.persistence.reader.ResourceInfoReaderMapper;
import com.jialian.core.persistence.reader.ShopProdTypeReaderMapper;

/**
 * @Description: 商城首页
 * @Author: zhls 联系方式：zhls1992@qq.com
 * @Since:2016年1月8日 下午3:43:41
 * @Version:1.0
 */
@Service("shopIndexService")
public class ShopIndexServiceImpl implements ShopIndexServiceApi {

	@Resource
	private ResourceInfoReaderMapper resourceInfoReaderMapper;
	@Resource
	private MaterialReaderMapper materialReaderMapper;
	@Resource
	private ShopProdTypeReaderMapper shopProdTypeReaderMapper;
	
	/*
	 * （非 Javadoc）
	 * 
	 * @see com.jialian.api.service.Shop.ShopIndexServiceApi#getShopIndex()
	 */
	@Override
	public ServiceResult<Object> getShopProdTypes() {
		ServiceResult<Object> result = new ServiceResult<Object>();

		try {
			//商品分类
			JSONArray protype = new JSONArray();
			//查找主分类
			Where where3 = new Where(null,null,"sequence");
			Criteria criteria3 = where3.createCriteria();
			criteria3.andEqualTo("rank", 1);
			List<ShopProdType> firlist = shopProdTypeReaderMapper.selectByWhere(where3);
			for (ShopProdType spt : firlist) {//循环并查找子分类
				JSONObject jo = new JSONObject();
				jo.put("partype", spt);
				Where where4 = new Where(null,null,"sequence");
				Criteria criteria4 = where4.createCriteria();
				criteria4.andEqualTo("rank", 2).andEqualTo("supid", spt.getId());
				List<ShopProdType> seclist = shopProdTypeReaderMapper.selectByWhere(where4);
				if(seclist!=null){
					jo.put("childtypelist", seclist);
				}
				protype.add(jo);
			}
			// 填充
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("protype", protype);
			result.setDataMap(dataMap);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);

		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		
		return result;
	}
	@Override
	public ServiceResult<Object> getShopIndex() {
		ServiceResult<Object> result = new ServiceResult<Object>();
		
		try {
			// 轮播图数据
			List<ResourceInfo> imgDate = resourceInfoReaderMapper.selectResourceInfoByHomeType((short) 8);
			// 人气新品
			Where where = new Where(8,1,"id");
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("prod_category", 1);
			criteria.andEqualTo("material_type", 3);
			criteria.andEqualTo("fbzt", 1);
			List<Material> popList = materialReaderMapper.selectByWhereWithBLOBs(where);
			// 热销单品
			Where where2 = new Where(8,1,"id");
			Criteria criteria2 = where2.createCriteria();
			criteria2.andEqualTo("prod_category", 2);
			criteria2.andEqualTo("material_type", 3);
			criteria2.andEqualTo("fbzt", 1);
			List<Material> hotList = materialReaderMapper.selectByWhereWithBLOBs(where2);
			//商品分类
			JSONArray protype = new JSONArray();
			//查找主分类
			Where where3 = new Where(null,null,"sequence");
			Criteria criteria3 = where3.createCriteria();
			criteria3.andEqualTo("rank", 1);
			List<ShopProdType> firlist = shopProdTypeReaderMapper.selectByWhere(where3);
			for (ShopProdType spt : firlist) {//循环并查找子分类
				JSONObject jo = new JSONObject();
				jo.put("partype", spt);
				Where where4 = new Where(null,null,"sequence");
				Criteria criteria4 = where4.createCriteria();
				criteria4.andEqualTo("rank", 2).andEqualTo("supid", spt.getId());
				List<ShopProdType> seclist = shopProdTypeReaderMapper.selectByWhere(where4);
				if(seclist!=null){
					jo.put("childtypelist", seclist);
				}
				protype.add(jo);
			}
			// 填充
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("imgDate", imgDate);
			dataMap.put("hotList", hotList);
			dataMap.put("popList", popList);
			dataMap.put("protype", protype);
			result.setDataMap(dataMap);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		
		return result;
	}

	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopIndexServiceApi#getShopProduct(java.lang.String, java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ServiceResult<Object> getShopProduct(Integer pricesort, Integer salessort,Double minPrice, Double maxPrice,String prodName,
			Long proTypeId, Integer pageSize, Integer pageIndex) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		if(pageSize==null||pageSize<=0)pageSize=20;
		if(pageIndex==null||pageIndex<=0)pageIndex=1;
		if(minPrice==null||minPrice<=0)minPrice=0.0;
		if(salessort==null)salessort=1;
		if(pricesort==null)pricesort=1;
		try {
			
			// 商品数据
			Where where = new Where();
			if(salessort==1){//正序 ss是计算出来的销量
				if(pricesort==1){//价格正序
					where = new Where(pageSize,pageIndex,"ss asc, material.material_price asc");
				}else{//价格倒叙
					where = new Where(pageSize,pageIndex,"ss asc, material.material_price desc");
				}
				
			}else{//倒叙
				if(pricesort==1){//价格正序
					where = new Where(pageSize,pageIndex,"ss desc, material.material_price asc");
				}else{//价格倒叙
					where = new Where(pageSize,pageIndex,"ss desc, material.material_price desc");
				}
				
			}
			
//			where.setLimit(pageSize);
//			where.setOffset(pageIndex);
//			where.setOrderByClause("id");
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("1", "1");
			criteria.andEqualTo("fbzt", "1");
			criteria.andEqualTo("material_type", 3);
			//System.out.println(prodName);
			if(prodName!=null&&!prodName.equals("")){
				prodName=StringUtils.deleteEnter(prodName).trim();
				if(StringUtils.isMessyCode(prodName)){
					prodName=StringUtils.encodeString(prodName, "ISO-8859-1", "utf-8");
				}
				
				criteria.andLike("material.material_name", "%"+prodName+"%");
			}
		
			if(proTypeId!=null&&proTypeId>0){
				criteria.andEqualTo("material.prod_type_id", proTypeId);
			}
			
			criteria.andGreaterThanOrEqualTo("material.material_price", minPrice);
			if(maxPrice!=null&&maxPrice>0){
				criteria.andLessThanOrEqualTo("material.material_price", maxPrice);
			}
			Object[] msgParams = new Object[8];
			msgParams[0]=pageSize;
			msgParams[1]=pageIndex;
			msgParams[2]=prodName;
			msgParams[3]=proTypeId;
			msgParams[4]=salessort;
			msgParams[5]=minPrice;
			msgParams[6]=maxPrice;
			msgParams[7]=pricesort;
			List<Material> prodList = materialReaderMapper.selectProdListByWhereWithBLOBs(where);
			//分页明天来做
			int sum = materialReaderMapper.countProdListByWhere(where);
			Page<Material> pageData = new Page<Material>(pageIndex,sum,pageSize);
			pageData.setList(prodList);
			result.setData(pageData);
			result.setComment("成功");
			result.setMsgParams(msgParams);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);

		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		
		return result;
	}

	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopIndexServiceApi#getProdDetail(java.lang.Long)
	 */
	@Override
	public ServiceResult<Object> getProdDetail(Long prodId) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		
		try {
			//判断参数
			if(prodId!=null&&prodId<=0){
				result.setOk(false);
				result.setMsgCode(Const.ERROR_PARAM_CODE);
				return result;
			}
			
			Object[] msgParams = new Object[1];
			msgParams[0]=prodId;
			Material prod = materialReaderMapper.selectByPrimaryKey(prodId);
			result.setData(prod);
			result.setComment("成功");
			result.setMsgParams(msgParams);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);

		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		return result;
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopIndexServiceApi#getShopProdList(java.lang.String, java.lang.String, java.lang.Long, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ServiceResult<Object> getShopProdList(String pname, String pno,
			Long type, Integer pubstatus, Integer recommend, Integer pageSize, Integer pageIndex) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		if(pageSize==null||pageSize<=0)pageSize=5;
		if(pageIndex==null||pageIndex<=0)pageIndex=1;
		
		try {
			
			// 商品数据
			Where where = new Where(pageSize,pageIndex,"id desc");
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("status", 1);
			criteria.andEqualTo("material_type", 3);
			if(pname!=null&&!pname.equals("")){
				criteria.andLike("material_name", "%"+pname+"%");
			}
			if(pno!=null&&!pno.equals("")){
				criteria.andLike("material_no", "%"+pno+"%");
			}
			if(pubstatus!=null){
				criteria.andEqualTo("fbzt", pubstatus);
			}
			if(recommend!=null){
				criteria.andEqualTo("prod_category", recommend);
			}
			if(type!=null){
				criteria.andEqualTo("prod_type_id", type);
			}
			//System.out.println(prodName);
			
		
			List<Material> prodList = materialReaderMapper.selectByWhereWithBLOBs(where);
			//分页明天来做
			int sum = materialReaderMapper.countByWhere(where);
			Page<Material> pageData = new Page<Material>(pageIndex,sum,pageSize);
			pageData.setList(prodList);
			result.setData(pageData);
			result.setComment("成功");
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);

		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		
		return result;
	}

}
