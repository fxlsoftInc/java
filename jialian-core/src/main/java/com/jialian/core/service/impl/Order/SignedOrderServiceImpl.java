package com.jialian.core.service.impl.Order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.SignedOrder;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.DecOrderDetailVO;
import com.jialian.api.domain.vo.DecorationOrderVO;
import com.jialian.api.service.Order.SignedOrderServiceApi;
import com.jialian.core.persistence.reader.SignedOrderReaderMapper;
import com.jialian.core.persistence.writer.SignedOrderWriterMapper;

@Service("signedOrderService")
public class SignedOrderServiceImpl implements SignedOrderServiceApi{
	
	@Resource
	private SignedOrderReaderMapper signedOrderReaderMapper;
	
	@Resource
	private SignedOrderWriterMapper signedOrderWriterMapper;
	
	public int countByWhere(Where where){
		return signedOrderReaderMapper.countByWhere(where);
	}

	public List<SignedOrder> selectByWhere(Where where){
		return signedOrderReaderMapper.selectByWhere(where);
	}

	public SignedOrder selectByPrimaryKey(Long id){
		return signedOrderReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return signedOrderWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return signedOrderWriterMapper.deleteByPrimaryKey(id);
	}

	public SignedOrder insert(SignedOrder record){
		int flag = signedOrderWriterMapper.insert(record);
		if(flag > 0){
			return record;
		}
		return null;
	}

	public SignedOrder insertSelective(SignedOrder record){
		int flag = signedOrderWriterMapper.insertSelective(record);
		if(flag > 0){
			return record;
		}
		return null;
	}

	public int updateByWhereSelective(SignedOrder record, Where where){
		return signedOrderWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhere(SignedOrder record, Where where){
		return signedOrderWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(SignedOrder record){
		return signedOrderWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(SignedOrder record){
		return signedOrderWriterMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<DecorationOrderVO> selectSignedOrderList(Where where) {
		return signedOrderReaderMapper.selectSignedOrderList(where);
	}

	@Override
	public int countSignedOrderByWhere(Where where) {
		return signedOrderReaderMapper.countSignedOrderByWhere(where);
	}

	@Override
	public DecOrderDetailVO selectWithUserByPrimaryKey(Long id) {
		return signedOrderReaderMapper.selectWithUserByPrimaryKey(id);
	}
}
