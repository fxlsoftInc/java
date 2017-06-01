package com.jialian.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.CaseHouse;
import com.jialian.api.domain.entity.CaseHouseInfo;
import com.jialian.api.domain.entity.Collect;
import com.jialian.api.domain.entity.HouseInfo;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.query.CaseHouseQuery;
import com.jialian.api.domain.vo.CaseHouseVo;
import com.jialian.api.service.CaseHouseServiceApi;
import com.jialian.core.persistence.reader.CaseHouseReaderMapper;
import com.jialian.core.persistence.reader.CollectReaderMapper;
import com.jialian.core.persistence.reader.ResourceInfoReaderMapper;
import com.jialian.core.persistence.writer.AddressWriterMapper;
import com.jialian.core.persistence.writer.CaseHouseInfoWriterMapper;
import com.jialian.core.persistence.writer.CaseHouseWriterMapper;
import com.jialian.core.persistence.writer.CollectWriterMapper;
import com.jialian.core.persistence.writer.HouseInfoWriterMapper;
import com.jialian.core.persistence.writer.HouseStyleWriterMapper;
import com.jialian.core.persistence.writer.ResourceInfoWriterMapper;

@Service("caseHouseService")
public class CaseHouseServiceImpl implements CaseHouseServiceApi {
	
	@Resource
	AddressWriterMapper addressWriterMapper;
	
	@Resource
	CaseHouseWriterMapper caseHouseWriterMapper;
	
	@Resource
	CaseHouseInfoWriterMapper caseHouseInfoWriterMapper;
	
	@Resource
	CaseHouseReaderMapper caseHouseReaderMapper;
	
	@Resource
	ResourceInfoReaderMapper resourceInfoReaderMapper;
	
	@Resource
	CollectReaderMapper collectReaderMapper;
	
	@Resource
	CollectWriterMapper collectWriterMapper;
	
	@Resource
	HouseInfoWriterMapper houseInfoWriterMapper;
	
	@Resource
	HouseStyleWriterMapper houseStyleWriterMapper;
	
	@Resource
	ResourceInfoWriterMapper resourceInfoWriterMapper;

	@Override
	public ServiceResult<Page<CaseHouseVo>> getCaseHouseListByQuery(CaseHouseQuery query) {
		ServiceResult<Page<CaseHouseVo>> result=new ServiceResult<Page<CaseHouseVo>>();
		int countRecord=caseHouseReaderMapper.getCaseHouseCountByQuery(query);
		Page<CaseHouseVo> page=new Page<CaseHouseVo>(query.getCurrentPage(), countRecord, query.getOnePageCount());
		if(countRecord>0){
			query.setStartIndex(page.getStartIndex());
			List<CaseHouseVo> list=caseHouseReaderMapper.getCaseHouseListByQuery(query);
			page.setList(list);
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		result.setOk(true);
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<CaseHouseVo> getCaseHouse(Long id,Long userId) {
		ServiceResult<CaseHouseVo> result=new ServiceResult<CaseHouseVo>();
		CaseHouseVo caseHouseVo=caseHouseReaderMapper.selectCaseHouseVo(id);
		if(caseHouseVo==null){
			result.setMsgCode(Const.ERROR_CHECK_NULL);
			result.setComment("查询不到数据");
			return result;
		}
		Collect col=collectReaderMapper.selectCollectByUidAndParidAndTypeid(userId, id, (short) 1);
		if(col!=null){
			caseHouseVo.setCollectStatus(col.getCollectStatus());
		}
		List<ResourceInfo> list=resourceInfoReaderMapper.selectImgByObjectTypeAndObjectId((short)3,id,null);
		caseHouseVo.setImgs(list);
		result.setOk(true);
		result.setData(caseHouseVo);
		return result;
	}

	//待完善事务管理
	@Override
	public ServiceResult<Object> addCaseHouse(CaseHouse caseHouse, CaseHouseInfo caseHouseInfo,HouseInfo houseInfo,long[] resourceInfoIds) {
		ServiceResult<Object> result=new ServiceResult<Object>();
//		//添加地址信息
//		int i1=addressWriterMapper.insertSelectiveBackId(address);
		//添加房屋信息
		int houseInfo_s=houseInfoWriterMapper.insertSelectiveBackId(houseInfo);
		//添加案例馆信息
//		caseHouse.setAddressId(address.getId());
		caseHouse.setHouseInfoId(houseInfo.getId());
		int caseHouse_s=caseHouseWriterMapper.insertSelectiveBackId(caseHouse);
		//添加案例馆详情信息
		if(caseHouseInfo==null){
			caseHouseInfo=new CaseHouseInfo();
		}
		caseHouseInfo.setParentId(caseHouse.getId());
		int caseHouseInfo_s=caseHouseInfoWriterMapper.insertSelective(caseHouseInfo);
		//图片id添加
		for(int i=0;i<resourceInfoIds.length;i++){
			ResourceInfo record=new ResourceInfo();
			record.setId(resourceInfoIds[i]);
			record.setObjectId(caseHouse.getId());
			resourceInfoWriterMapper.updateByPrimaryKeySelective(record);
		}
		if(houseInfo_s==1 && caseHouse_s==1 && caseHouseInfo_s==1){
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setOk(true);
			result.setComment("添加成功");
		}
		return result;
	}

	//待完善事务管理
	@Override
	public ServiceResult<Object> updateCaseHouse(CaseHouse caseHouse,CaseHouseInfo caseHouseInfo,HouseInfo houseInfo,List<ResourceInfo> infos) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		if(caseHouse !=null){
			//修改案例馆信息
			caseHouseWriterMapper.updateByPrimaryKeySelective(caseHouse);
		}
		if(caseHouseInfo !=null){
			//修改案例馆详情信息
			caseHouseInfoWriterMapper.updateByPrimaryKeySelective(caseHouseInfo);
		}
		if(houseInfo !=null){
			//修改房屋信息
			houseInfoWriterMapper.updateByPrimaryKeySelective(houseInfo);
		}
		//图片信息修改
		for(int i=0;i<infos.size();i++){
			ResourceInfo resourceInfo=infos.get(i);
			resourceInfoWriterMapper.updateByPrimaryKeySelective(resourceInfo);
		}
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setOk(true);
		result.setComment("修改成功");
		return result;
	}

	@Override
	public ServiceResult<Object> addCollecCaseHouse(Long userId,Long id) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		Collect col=collectReaderMapper.selectCollectByUidAndParidAndTypeid(userId, id, (short) 1);
		if(col !=null){
			result.setComment("您已经收藏过啦 ");
			return result;
		}
		Collect collect=new Collect();
		collect.setParentId(id);
		collect.setUserId(userId);
		collect.setCollectType((short) 1);
		collect.setCollectStatus((long) 1);
		int i=collectWriterMapper.insertSelective(collect);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("收藏成功");
		}else{
			result.setComment("收藏失败");
		}
		return result;
	}

}
