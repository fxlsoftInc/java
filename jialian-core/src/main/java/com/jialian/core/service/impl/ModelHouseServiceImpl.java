package com.jialian.core.service.impl;

import java.util.List;

import javax.annotation.Resource;













import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Address;
import com.jialian.api.domain.entity.ModelHouse;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.para.ResourceInfoReplacePara;
import com.jialian.api.domain.query.ModelHouseQuery;
import com.jialian.api.domain.vo.ModelHouseDetailVo;
import com.jialian.api.domain.vo.ModelHouseVo;
import com.jialian.api.service.ModelHouseServiceApi;
import com.jialian.core.persistence.reader.AddressReaderMapper;
import com.jialian.core.persistence.reader.ModelHouseReaderMapper;
import com.jialian.core.persistence.reader.ResourceInfoReaderMapper;
import com.jialian.core.persistence.writer.AddressWriterMapper;
import com.jialian.core.persistence.writer.ModelHouseWriterMapper;
import com.jialian.core.persistence.writer.ResourceInfoWriterMapper;

@Service("modelHouseService")
public class ModelHouseServiceImpl implements ModelHouseServiceApi {
	
	@Resource
	ModelHouseReaderMapper modelHouseReaderMapper;
	
	@Resource
	ModelHouseWriterMapper modelHouseWriterMapper;
	
	@Resource
	AddressReaderMapper addressReaderMapper;
	
	@Resource
	AddressWriterMapper addressWriterMapper;
	
	@Resource
	ResourceInfoReaderMapper resourceInfoReaderMapper;
	
	@Resource
	ResourceInfoWriterMapper resourceInfoWriterMapper;

	@Override
	public ServiceResult<Page<ModelHouseVo>> getModelHouseListByQuery(ModelHouseQuery query) {
		ServiceResult<Page<ModelHouseVo>> result=new ServiceResult<Page<ModelHouseVo>>();
		int countRecord=modelHouseReaderMapper.getModelHouseCountByQuery(query);
		Page<ModelHouseVo> page=new Page<ModelHouseVo>(query.getCurrentPage(), countRecord, query.getOnePageCount());
		if(countRecord>0){
			query.setStartIndex(page.getStartIndex());
			List<ModelHouseVo> list=modelHouseReaderMapper.getModelHouseListByQuery(query);
			page.setList(list);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		result.setData(page);
		
		return result;
	}

	/**
	 * 后台--样板房列表，包括分页
	 */
	@Override
	public List<ModelHouse> selectByWhere(Where where) {
		return modelHouseReaderMapper.selectByWhere(where);
	}

	/**
	 * 后台--样板房列表计数
	 */
	@Override
	public int countByWhere(Where where) {
		return modelHouseReaderMapper.countByWhere(where);
	}

	/**
	 * 后台--根据id查询样板房
	 */
	@Override
	public ModelHouse selectByPrimaryKey(Long id) {
		return modelHouseReaderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 后台--根据id查询样板房
	 */
	@Override
	public ModelHouseDetailVo selectModelHouseDetail(Long id) {
		return modelHouseReaderMapper.selectModelHouseDetail(id);
	}
	/**
	 * 后台--根据样板房id删除
	 */
	@Override
	public int updateModelHouseByPrimaryKey(ModelHouse modelHouse) {
		return modelHouseWriterMapper.updateByPrimaryKeySelective(modelHouse);
	}

	/**
	 * 后台--添加样板房
	 */
	@Override
	public ModelHouse addModelHouse(ModelHouse modelHouse) {
		int result = modelHouseWriterMapper.insertSelective(modelHouse);
		return result > 0?modelHouse: null;
	}

	@Override
	public ServiceResult<Object> updateModelHouseById(ModelHouse modelHouse) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=modelHouseWriterMapper.updateByPrimaryKeySelective(modelHouse);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}else{
			result.setComment("修改失败");
		}
		return result;
	}

	/**
	 * 后台--样板房修改
	 */
	@Override
	public ServiceResult<ModelHouseDetailVo> editModelHouse(ModelHouse modelHouse, Address address,
			ResourceInfoReplacePara resourceInfo) {
		ServiceResult<ModelHouseDetailVo> result = new ServiceResult<ModelHouseDetailVo>();
		int res = 0;
		modelHouse = modelHouseReaderMapper.selectByPrimaryKey(modelHouse.getId());
		if(modelHouse == null || modelHouse.equals("")){
			result.setOk(false);
			result.setComment("该样板房已不存在或已删除");
		}else{
			address = addressReaderMapper.selectByPrimaryKey(modelHouse.getAddress());
			if(address == null || address.equals("")){
				result.setOk(false);
				result.setComment("该样板房已不存在或已删除");
			}else{//可以修改
				 res = addressWriterMapper.updateByPrimaryKeySelective(address);
				 if(res == 1){
					 if(resourceInfo.getFromId() == null || resourceInfo.getFromNo() == null ||
								resourceInfo.getTargetId() == null || resourceInfo.getTargetNo() == null){
							 result.setOk(false);
							 result.setComment("修改失败");
							return result;
						}
						ResourceInfo resInf = new ResourceInfo();
						resInf.setId(resourceInfo.getFromId());
						resInf.setNo(resourceInfo.getTargetNo());
						resInf.setObjectId(0l);
						res = resourceInfoWriterMapper.updateByPrimaryKeySelective(resInf);
						resInf.setId(resourceInfo.getTargetId());
						resInf.setNo(resourceInfo.getFromNo());
						resInf.setObjectId(modelHouse.getId());
						res = resourceInfoWriterMapper.updateByPrimaryKeySelective(resInf);
						if(res == 1){
							res = modelHouseWriterMapper.updateByPrimaryKeySelective(modelHouse);
							if(res == 1){
								 result.setOk(true);
								 result.setComment("修改成功");
							}else{
								 result.setOk(false);
								 result.setComment("修改失败");
							}
						}else{
							 result.setOk(false);
							 result.setComment("修改失败");
						}
				 }else{
					 result.setOk(false);
					 result.setComment("修改失败");
				 }
			}
		}
		return result;
	}

	
}
