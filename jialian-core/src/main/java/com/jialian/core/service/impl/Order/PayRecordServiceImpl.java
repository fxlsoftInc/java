package com.jialian.core.service.impl.Order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.PayRecord;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Order.PayRecordServiceApi;
import com.jialian.core.persistence.reader.PayRecordReaderMapper;
import com.jialian.core.persistence.writer.PayRecordWriterMapper;

@Service("payRecordService")
public class PayRecordServiceImpl implements PayRecordServiceApi{
	
	@Resource
	private PayRecordReaderMapper payRecordReaderMapper;
	
	@Resource
	private PayRecordWriterMapper payRecordWriterMapper;
	
	public int countByWhere(Where where){
		return payRecordReaderMapper.countByWhere(where);
	}

	public List<PayRecord> selectByWhere(Where where){
		return payRecordReaderMapper.selectByWhere(where);
	}

	public PayRecord selectByPrimaryKey(Long id){
		return payRecordReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return payRecordWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return payRecordWriterMapper.deleteByPrimaryKey(id);
	}

	public PayRecord insert(PayRecord record){
		int flag = payRecordWriterMapper.insert(record);
		if(flag > 0){
    		return record;
    	}
    	return null;
	}

	public PayRecord insertSelective(PayRecord record){
		int flag = payRecordWriterMapper.insertSelective(record);
		if(flag > 0){
    		return record;
    	}
    	return null;
	}

	public int updateByWhereSelective(PayRecord record,  Where where){
		return payRecordWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhere(PayRecord record,  Where where){
		return payRecordWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(PayRecord record){
		return payRecordWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(PayRecord record){
		return payRecordWriterMapper.updateByPrimaryKey(record);
	}

	@Override
	public Double sumPayAmtByWhere(Where where) {
		return payRecordReaderMapper.sumPayAmtByWhere(where);
	}
}
