package com.jialian.core.service.impl.Subscribe;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.HouseType;
import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.SignedOrder;
import com.jialian.api.domain.entity.SignedRecord;
import com.jialian.api.domain.entity.SubHouseInfo;
import com.jialian.api.domain.entity.SubOrder;
import com.jialian.api.domain.entity.Subscribe;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.vo.SubscribeVo;
import com.jialian.api.service.Subscribe.SubscribeServiceApi;
import com.jialian.core.persistence.reader.UserReaderMapper;
import com.jialian.core.persistence.writer.AddressWriterMapper;
import com.jialian.core.persistence.writer.HouseTypeWriterMapper;
import com.jialian.core.persistence.writer.OrderTrackWriterMapper;
import com.jialian.core.persistence.writer.SignedOrderWriterMapper;
import com.jialian.core.persistence.writer.SignedRecordWriterMapper;
import com.jialian.core.persistence.writer.SubHouseInfoWriterMapper;
import com.jialian.core.persistence.writer.SubOrderWriterMapper;
import com.jialian.core.persistence.writer.SubscribeWriterMapper;

@Service("subscribeService")
public class SubscribeServiceImpl implements SubscribeServiceApi {

	@Resource
	SubscribeWriterMapper subscribeWriterMapper;
	
	@Resource
	AddressWriterMapper addressWriterMapper;
	
	@Resource
	SubHouseInfoWriterMapper subHouseInfoWriterMapper;
	
	@Resource
	SubOrderWriterMapper subOrderWriterMapper;
	
	@Resource
	HouseTypeWriterMapper houseTypeWriterMapper;
	
	@Resource
	IdWorkerServiceApi idWorkerService;
	
	@Resource
	SignedOrderWriterMapper signedOrderWriterMapper;
	
	@Resource
	SignedRecordWriterMapper signedRecordWriterMapper;
	
	@Resource
	UserReaderMapper userReaderMapper;
	
	@Resource
	OrderTrackWriterMapper orderTrackWriterMapper;
	
	//事物管理（待配置..）
	@Override
	public ServiceResult<Subscribe> addSubscribe(SubscribeVo subscribeVo) {
		ServiceResult<Subscribe> result=new ServiceResult<Subscribe>();
		//用户预约信息添加
		Subscribe s=new Subscribe();
		s.setUserid(subscribeVo.getUserId());
		s.setTelphone(subscribeVo.getTelphone());
		s.setType((short) 1);
		s.setRemark("套餐预约");
		int i1=subscribeWriterMapper.insertSelectiveBackId(s);
		//预约地址添加
		Address a=new Address();
		a.setAddressType((short) 2);
		a.setProvince(subscribeVo.getProvince());
		a.setCity(subscribeVo.getCity());
		a.setCounty(subscribeVo.getCounty());
		a.setAddress(subscribeVo.getAddress());
		int i2=addressWriterMapper.insertSelectiveBackId(a);
		//房屋类型信息添加
		HouseType houseType=new HouseType();
		String houseTypeNo="HT"+idWorkerService.nextId();
		String actualHouseTypeNo = "HT"+idWorkerService.nextId();
		
		if(subscribeVo.getS() != null){
			houseType.setHouseTypeNo(houseTypeNo);
			houseType.setHouseTypeNumbser(subscribeVo.getS());
			houseType.setHouseType((short)1);
			houseType.setHouseTypeName("室");
			houseTypeWriterMapper.insertSelective(houseType);
			houseType.setHouseTypeNo(actualHouseTypeNo);
			houseTypeWriterMapper.insertSelective(houseType);
		}
		if(subscribeVo.getT() != null){
			houseType.setHouseTypeNo(houseTypeNo);
			houseType.setHouseTypeNumbser(subscribeVo.getT());
			houseType.setHouseType((short)2);
			houseType.setHouseTypeName("厅");
			houseTypeWriterMapper.insertSelective(houseType);
			houseType.setHouseTypeNo(actualHouseTypeNo);
			houseTypeWriterMapper.insertSelective(houseType);
		}
		if(subscribeVo.getC() != null){
			houseType.setHouseTypeNo(houseTypeNo);
			houseType.setHouseTypeNumbser(subscribeVo.getC());
			houseType.setHouseType((short)3);
			houseType.setHouseTypeName("厨");
			houseTypeWriterMapper.insertSelective(houseType);
			houseType.setHouseTypeNo(actualHouseTypeNo);
			houseTypeWriterMapper.insertSelective(houseType);
		}
		if(subscribeVo.getW() != null){
			houseType.setHouseTypeNo(houseTypeNo);
			houseType.setHouseTypeNumbser(subscribeVo.getW());
			houseType.setHouseType((short)4);
			houseType.setHouseTypeName("卫");
			houseTypeWriterMapper.insertSelective(houseType);
			houseType.setHouseTypeNo(actualHouseTypeNo);
			houseTypeWriterMapper.insertSelective(houseType);
		}
		if(subscribeVo.getY() != null){
			houseType.setHouseTypeNo(houseTypeNo);
			houseType.setHouseTypeNumbser(subscribeVo.getY());
			houseType.setHouseType((short)5);
			houseType.setHouseTypeName("阳台");
			houseTypeWriterMapper.insertSelective(houseType);
			houseType.setHouseTypeNo(actualHouseTypeNo);
			houseTypeWriterMapper.insertSelective(houseType);
		}
		//确认订单
		SignedOrder signedOrder = new SignedOrder();
		signedOrder.setOrderNo("ORD" + idWorkerService.nextId());
		signedOrder.setUserId(subscribeVo.getUserId());
		signedOrder.setComboId(subscribeVo.getComboId());
		signedOrder.setRemark("套餐预订订单");
		signedOrderWriterMapper.insertSelective(signedOrder);
		//生成合同
		SignedRecord signedRecord = new SignedRecord();
		signedRecord.setOrderNo(signedOrder.getOrderNo());
		signedRecord.setContractNo("HT" + idWorkerService.nextId());
		signedRecordWriterMapper.insertSelective(signedRecord);
		//预约房屋信息添加
		SubHouseInfo shi=new SubHouseInfo();
		shi.setStructure(houseTypeNo);
		shi.setArea(subscribeVo.getArea());
		shi.setType(subscribeVo.getType());
		if(subscribeVo.getType()==1){
			shi.setTypeName("新房装修");
		}else{
			shi.setTypeName("旧房翻新");
		}
		shi.setHouseStyleId(subscribeVo.getHouseStyleId());
		shi.setAddressId(a.getId());
		shi.setPlanDecorationTime(subscribeVo.getPlanDecorationTime());
		int i3=subHouseInfoWriterMapper.insertSelectiveBackId(shi);
		//预约订单生成
		SubOrder so=new SubOrder();
		so.setStatus((short) 1);
		so.setUserId(subscribeVo.getId());
		so.setSubscribeId(s.getId());
		so.setSubHouseInfoId(shi.getId());
		shi.setId(null);
		int i4=subHouseInfoWriterMapper.insertSelectiveBackId(shi);
		so.setActualSubHouseInfoId(shi.getId());
		so.setOrderNo(signedOrder.getOrderNo());
		int i5=subOrderWriterMapper.insertSelective(so);
		//产生消息
		User user = userReaderMapper.selectByPrimaryKey(subscribeVo.getUserId());
		OrderTrack orderTrack = new OrderTrack();
		orderTrack.setUserId(user.getId());
		orderTrack.setOrderId(signedOrder.getId());
		orderTrack.setTrackTitle("装修订单基础信息更改");
		String trackContent;
		if(user.getSex() == 0){
			trackContent =  user.getNickName() + " 女士，您好！你的预约装修订单   " + signedOrder.getOrderNo() +" 已经产生，我们会尽快联系你，查看详情请单击！";
		}else{
			trackContent =  user.getNickName() + " 先生，您好！你的预约装修订单    " + signedOrder.getOrderNo() +" 已经产生，我们会尽快联系你，查看详情请单击！";
		}
		orderTrack.setTrackContent(trackContent);
		orderTrack.setTrackId(signedOrder.getId());
		orderTrack.setTrackType((short)2);
		orderTrackWriterMapper.insertSelective(orderTrack);
		if(i1==1 && i2==1 && i3==1 && i4==1 && i5==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("预约成功");
		}else{
			result.setComment("预约失败");
		}
		return result;
	}

	/**
	 * 单表选择性插入
	 */
	@Override
	public int insertSelective(Subscribe record) {
		return subscribeWriterMapper.insertSelective(record);
	}

}
