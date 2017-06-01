package com.jialian.core.service.impl.PubDoc;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.PubDoc;
import com.jialian.api.domain.vo.PubDocVo;
import com.jialian.api.service.PubDoc.PubDocServiceApi;
import com.jialian.core.persistence.reader.PubDocReaderMapper;
import com.jialian.core.persistence.writer.PubDocWriterMapper;

@Service("pubDocService")
public class PubDocServiceImpl implements PubDocServiceApi {

	@Resource
	PubDocReaderMapper pubDocReaderMapper;
	
	@Resource
	PubDocWriterMapper pubDocWriterMapper;
	
	@Override
	public ServiceResult<List<PubDocVo>> getPubDoc() {
		ServiceResult<List<PubDocVo>> result=new ServiceResult<List<PubDocVo>>();
		List<PubDocVo>  list=pubDocReaderMapper.selectPubDoc();
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setData(list);
		return result;
	}

	@Override
	public ServiceResult<PubDocVo> getPubDocDetailed(Long id) {
		ServiceResult<PubDocVo> result=new ServiceResult<PubDocVo>();
		PubDocVo pubDocVo=pubDocReaderMapper.selectPubDocDetailed(id);
		if(pubDocVo==null){
			result.setMsgCode(Const.ERROR_NODATA_CODE);
			result.setComment("数据不存在");
			return result;
		}
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setData(pubDocVo);
		return result;
	}

	@Override
	public ServiceResult<Object> updatePubDoc(PubDoc pubDoc) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		pubDoc.setUpdateTime(new Date());
		int i=pubDocWriterMapper.updateByPrimaryKeySelective(pubDoc);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}else{
			result.setComment("修改失败");
		}
		return result;
	}

}
