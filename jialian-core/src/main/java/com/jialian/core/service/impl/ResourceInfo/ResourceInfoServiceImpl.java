package com.jialian.core.service.impl.ResourceInfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.core.persistence.reader.ResourceInfoReaderMapper;
import com.jialian.core.persistence.writer.ResourceInfoWriterMapper;

@Service("resourceInfoService")
public class ResourceInfoServiceImpl implements ResourceInfoServiceApi{

	@Resource
	private ResourceInfoReaderMapper resourceInfoReaderMapper;
	
	@Resource
	private ResourceInfoWriterMapper resourceInfoWriterMapper;
	
	@Override
	public int countByWhere(Where where) {
		return resourceInfoReaderMapper.countByWhere(where);
	}

	@Override
	public List<ResourceInfo> selectByWhere(Where where) {
		return resourceInfoReaderMapper.selectByWhere(where);
	}

	@Override
	public ResourceInfo selectByPrimaryKey(Long id) {
		return resourceInfoReaderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByWhere(Where where) {
		return resourceInfoWriterMapper.deleteByWhere(where);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return resourceInfoWriterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ResourceInfo insert(ResourceInfo record) {
		resourceInfoWriterMapper.insert(record);
		return record;
	}

	@Override
	public ResourceInfo insertSelective(ResourceInfo record) {
		resourceInfoWriterMapper.insertSelective(record);
		return record;
	}

	@Override
	public int updateByWhereSelective(ResourceInfo record, Where where) {
		return resourceInfoWriterMapper.updateByWhereSelective(record, where);
	}

	@Override
	public int updateByWhere(ResourceInfo record, Where where) {
		return resourceInfoWriterMapper.updateByWhere(record, where);
	}

	@Override
	public int updateByPrimaryKeySelective(ResourceInfo record) {
		return resourceInfoWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ResourceInfo record) {
		return resourceInfoWriterMapper.updateByPrimaryKey(record);
	}

	@Override
	public ServiceResult<List<ResourceInfo>> selectResourceInfoByHomeType(
			Short type) {
		ServiceResult<List<ResourceInfo>> result = new ServiceResult<List<ResourceInfo>>();
		List<ResourceInfo> homeImgList = resourceInfoReaderMapper.selectResourceInfoByHomeType(type);
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setData(homeImgList);
		return result;
	}

	@Override
	public ServiceResult<List<ResourceInfo>> selectResouceInfoByType(Short type) {
		ServiceResult<List<ResourceInfo>> result = new ServiceResult<List<ResourceInfo>>();
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("object_type", type);
		List<ResourceInfo> homeImgList = resourceInfoReaderMapper.selectByWhere(where);
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setData(homeImgList);
		return result;
	}

}
