/**
 * 
 */package com.jialian.core.service.impl.Shop;/** * @author  zhls E-mail:zhls1992@qq.com * @date 创建时间：2016年1月9日 下午6:11:40  */import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Material;
import com.jialian.api.domain.entity.MaterialAttribute;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.ShopCart;
import com.jialian.api.domain.entity.ShopOrder;
import com.jialian.api.domain.entity.ShopOrderDetail;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.Shop.ShopOrderServiceApi;
import com.jialian.core.persistence.reader.MaterialAttributeReaderMapper;
import com.jialian.core.persistence.reader.MaterialReaderMapper;
import com.jialian.core.persistence.reader.OrderTrackReaderMapper;
import com.jialian.core.persistence.reader.ShopCartReaderMapper;
import com.jialian.core.persistence.reader.ShopOrderReaderMapper;
import com.jialian.core.persistence.writer.MaterialAttributeWriterMapper;
import com.jialian.core.persistence.writer.ShopCartWriterMapper;
import com.jialian.core.persistence.writer.ShopOrderDetailWriterMapper;
import com.jialian.core.persistence.writer.ShopOrderWriterMapper;

/**
 *@Description: 商城订单
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月9日 下午6:11:40
 *@Version:1.0
 */
@Service("shopOrderService")
public class ShopOrderServiceImpl implements ShopOrderServiceApi {

	@Resource
	private ShopOrderWriterMapper shopOrderWriterMapper;
	@Resource
	private ShopOrderReaderMapper shopOrderReaderMapper;
	@Resource
	private ShopCartWriterMapper shopCartWriterMapper;
	@Resource
	private ShopOrderDetailWriterMapper shopOrderDetailWriterMapper;
	@Resource
	private ShopCartReaderMapper shopCartReaderMapper;
	@Resource
	private MaterialReaderMapper materialReaderMapper;
	@Resource
	private MaterialAttributeReaderMapper materialAttributeReaderMapper;
	@Resource
	private MaterialAttributeWriterMapper materialAttributeWriterMapper;
	@Resource
	private OrderTrackReaderMapper orderTrackReaderMapper;
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#addShopOrder(com.jialian.api.domain.entity.ShopOrder, java.util.List)
	 */
	@Override
	public ServiceResult<Object> addShopOrder(Long userId, Double totalAmt, Double postage,
			Double discountAmt, Double orderAmt, Double prepayAmt, Double payedAmt, String useraddr, Long addrid,
			Short ispinkageAmt,String shopCartIds, String orderNo,Integer ptype, String linktel, String linkuser) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		try {
			Date nowdate = new Date();
			
			//订单信息
			ShopOrder shopOrder = new ShopOrder();
			shopOrder.setAddrid(addrid);
			//shopOrder.setCompleteTime(nowdate);
			shopOrder.setConfirmTime(nowdate);
			shopOrder.setCreateTime(nowdate);
			shopOrder.setDiscountAmt(discountAmt);
			shopOrder.setIspinkageAmt(ispinkageAmt);
			shopOrder.setOrderAmt(orderAmt);
			shopOrder.setOrderNo(orderNo);
			shopOrder.setOrderStatus((short)2);
			shopOrder.setPayedAmt(payedAmt);
			shopOrder.setPaytype((short)1);
			shopOrder.setPostage(postage);
			shopOrder.setPrepayAmt(prepayAmt);
			shopOrder.setRemark("");
			shopOrder.setSendStatus((short)1);
			shopOrder.setStatus((short)1);
			shopOrder.setTotalAmt(totalAmt);
			shopOrder.setUpdateTime(nowdate);
			shopOrder.setUseraddr(useraddr);
			shopOrder.setUserId(userId);
			shopOrder.setLinktel(linktel);
			shopOrder.setConsignee(linkuser);
			//添加shopOrder
			shopOrderWriterMapper.insertSelective(shopOrder);
			if(ptype==1){//购物车
				//购物车所选商品
				//List<ShopOrderDetail> sodlist = new ArrayList<ShopOrderDetail>();
				if(shopCartIds!=null&&!shopCartIds.equals("")){//选中的商品所属购物车 ,间隔
					String [] scidlist = shopCartIds.split(",");
					for (String scid : scidlist) {//遍历
						if(scid!=null&&!scid.equals("")){
							ShopCart sc = shopCartReaderMapper.selectById(Long.parseLong(scid));
							Material mt = sc.getMaterial();
							MaterialAttribute ma = sc.getMaterialAttribute();
							String propic="";
							List<ResourceInfo> rilist= mt.getResourceInfoList();
							if(rilist!=null&&rilist.size()>0){
								propic = rilist.get(0).getPath()+"/"+rilist.get(0).getName()+rilist.get(0).getExtension();
							}
							ShopOrderDetail sod = new ShopOrderDetail();
							sod.setAttributeName(ma.getAttributeName());
							sod.setAttributeNo(ma.getAttributeNo());
							sod.setAttributeRemark(ma.getRemark());
							sod.setCount(sc.getCount());
							sod.setMaterialAftersale(mt.getMaterialAftersale());
							sod.setMaterialStandard(mt.getMaterialStandard());
							sod.setMaterialTechnology(mt.getMaterialTechnology());
							sod.setOrderId(shopOrder.getId());
							sod.setProAttrId(ma.getId());
							sod.setPropic(propic);
							sod.setProtitle(mt.getBrandName());
							sod.setProid(mt.getId());
							sod.setRemark(ma.getRemark());
							sod.setStatus((short)1);
							sod.setSummoney(ma.getPrice().getPrice()*sc.getCount());
							sod.setUnitmoney(ma.getPrice().getPrice());
							//插入数据库
							shopOrderDetailWriterMapper.insertSelective(sod);
							//sodlist.add(sod);
							//库存减
							ma.setCurrentInventory(ma.getCurrentInventory()-sc.getCount());
							materialAttributeWriterMapper.updateByPrimaryKeySelective(ma);
							//删除购物车
							shopCartWriterMapper.deleteByProdTypeId(userId,ma.getId());
							
							
						}
					}
				}
			}else if(ptype==2){//立即结算
				String [] scidlist = shopCartIds.split(",");
				//String prodid=scidlist[0];
				String prodattrid=scidlist[1];
				String count=scidlist[2];
				//Material mt = materialReaderMapper.selectByPrimaryKey(Long.parseLong(prodid));
				MaterialAttribute ma = materialAttributeReaderMapper.selectShopProdByPrimaryKey(Long.parseLong(prodattrid));
				Material mt =ma.getMaterial();
				String propic="";
				List<ResourceInfo> rilist= mt.getResourceInfoList();
				if(rilist!=null&&rilist.size()>0){
					propic = rilist.get(0).getPath()+"/"+rilist.get(0).getName()+rilist.get(0).getExtension();
				}
				ShopOrderDetail sod = new ShopOrderDetail();
				sod.setAttributeName(ma.getAttributeName());
				sod.setAttributeNo(ma.getAttributeNo());
				sod.setAttributeRemark(ma.getRemark());
				sod.setCount(Integer.parseInt(count));
				sod.setMaterialAftersale(mt.getMaterialAftersale());
				sod.setMaterialStandard(mt.getMaterialStandard());
				sod.setMaterialTechnology(mt.getMaterialTechnology());
				sod.setOrderId(shopOrder.getId());
				sod.setProAttrId(ma.getId());
				sod.setPropic(propic);
				sod.setProtitle(mt.getBrandName());
				sod.setProid(mt.getId());
				sod.setRemark(ma.getRemark());
				sod.setStatus((short)1);
				sod.setSummoney(ma.getPrice().getPrice()*Integer.parseInt(count));
				sod.setUnitmoney(ma.getPrice().getPrice());
				//插入数据库
				shopOrderDetailWriterMapper.insertSelective(sod);
				//库存减
				ma.setCurrentInventory(ma.getCurrentInventory()-Integer.parseInt(count));
				materialAttributeWriterMapper.updateByPrimaryKeySelective(ma);
			}
			
			//Map<String, Object> dataMap = new HashMap<String, Object>();
		//	dataMap.put("shopOrder", shopOrder);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("添加成功");
			result.setData(shopOrder);
//			result.setDataMap(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		return result;
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#insert(com.jialian.api.domain.entity.ShopOrder)
	 */
	@Override
	public int insert(ShopOrder shopOrder) {
		return shopOrderWriterMapper.insertSelective(shopOrder);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#selectByPrimaryKey(java.lang.Long)
	 */
	@Override
	public ShopOrder selectByPrimaryKey(Long id) {
		return shopOrderReaderMapper.selectByPrimaryKey(id);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#selectByOrderno(java.lang.String)
	 */
	@Override
	public ShopOrder selectByOrderno(String out_trade_no) {
		return shopOrderReaderMapper.selectByOrderno(out_trade_no);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#updateByPrimaryKeySelective(com.jialian.api.domain.entity.ShopOrder)
	 */
	@Override
	public int updateByPrimaryKeySelective(ShopOrder record) {
		return shopOrderWriterMapper.updateByPrimaryKeySelective(record);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#selectByChargeId(java.lang.String)
	 */
	@Override
	public ShopOrder selectByChargeId(String chargeId) {
		return shopOrderReaderMapper.selectByChargeId(chargeId);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#selectByUserId_Orderno(java.lang.Long, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ServiceResult<Object> selectByUserId_Orderno(Long userId,
			String orderno, Integer pageIndex, Integer pageSize) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		if(pageSize==null||pageSize<=0)pageSize=5;
		if(pageIndex==null||pageIndex<=0)pageIndex=1;
		try {
			if(userId==null||userId<1){
				result.setOk(false);
				result.setMsgCode(Const.ERROR_USER_NULL);
				result.setComment("用户不存在");
				return result;
			}
			Where where = new Where(pageSize,pageIndex,"id DESC");
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("user_id", userId);
			criteria.andEqualTo("status", 1);
			criteria.andLike("order_no", "%"+orderno+"%");
			List<ShopOrder> shopOrders = shopOrderReaderMapper.selectByWhere(where);
			int sum = shopOrderReaderMapper.countByWhere(where);
			//未支付订单
			criteria.andEqualTo("order_status", 2);
			int count = shopOrderReaderMapper.countByWhere(where);
			Page<ShopOrder> pageData = new Page<ShopOrder>(pageIndex,sum,pageSize);
			pageData.setList(shopOrders);
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("count", count);
			dataMap.put("pageData", pageData);
			result.setDataMap(dataMap);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("查询成功");
			
		}catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		return result;
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#deleteByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		return shopOrderWriterMapper.deleteByPrimaryKey(id);
	}
	/* （非 Javadoc）
	 * @see com.jialian.api.service.Shop.ShopOrderServiceApi#getShopOrderList(java.lang.String, java.lang.Short, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ServiceResult<Object> getShopOrderList(String orderno,
			Short orderstatus, String telephone, String stime, String etime,
			Integer pageSize, Integer pageIndex) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		if(pageSize==null||pageSize<=0)pageSize=5;
		if(pageIndex==null||pageIndex<=0)pageIndex=1;
		if(orderstatus==null)orderstatus=0;	
		try {
			
			Where where = new Where(pageSize,pageIndex,"id DESC");
			Criteria criteria = where.createCriteria();
			if(telephone!=null&&!telephone.equals("")){
				criteria.andLike("linktel", telephone);
			}
			if(orderno!=null&&!orderno.equals("")){
				criteria.andLike("order_no", "%"+orderno+"%");
			}
			if(stime!=null&&!stime.equals("")){
				criteria.andGreaterThanOrEqualTo("create_time", stime);
			}
			if(etime!=null&&!etime.equals("")){
				criteria.andLessThanOrEqualTo("create_time", etime);
			}
			if(orderstatus!=0){
				criteria.andEqualTo("order_status", orderstatus);
			}
			criteria.andEqualTo("status", 1);
			
			List<ShopOrder> shopOrders = shopOrderReaderMapper.selectByWhere(where);
			int sum = shopOrderReaderMapper.countByWhere(where);
			
			Page<ShopOrder> pageData = new Page<ShopOrder>(pageIndex,sum,pageSize);
			pageData.setList(shopOrders);
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
		
			dataMap.put("pageData", pageData);
			result.setDataMap(dataMap);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("查询成功");
			
		}catch (Exception e) {
			e.printStackTrace();
			result.setOk(false);
			result.setMsgCode(Const.ERROR_CODE);
		}
		return result;
	}
	@Override
	public Integer countByWhere(Where where) {
		return shopOrderReaderMapper.countByWhere(where);
	}
	
}
