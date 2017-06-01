package com.jialian.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.vo.HomeVo;
import com.jialian.api.service.HomeServiceApi;
import com.jialian.core.persistence.reader.CaseHouseReaderMapper;
import com.jialian.core.persistence.reader.ResourceInfoReaderMapper;

@Service("homeService")
public class HomeServiceImpl implements HomeServiceApi {
	
	@Resource
	ResourceInfoReaderMapper resourceInfoReaderMapper;
	
	@Resource
	CaseHouseReaderMapper caseHouseReaderMapper;

	@Override
	public ServiceResult<HomeVo> getHomeMes() {
		ServiceResult<HomeVo> result=new ServiceResult<HomeVo>();
		//轮播图数据
		List<ResourceInfo> imgDate=resourceInfoReaderMapper.selectImgByObjectTypeAndObjectId((short) 2, null,null);
		//案例数据
		List<HomeVo> caseList=caseHouseReaderMapper.selectHomeCase();
		//填充
		Map<String, Object> dataMap=new HashMap<String, Object>();
		dataMap.put("imgDate", imgDate);
		dataMap.put("caseList", caseList);
		result.setDataMap(dataMap);
		result.setOk(true);
		if(imgDate.size()>0 && caseList.size()>0){
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		return result;
	}

}
