package com.jialian.core.service.impl.Order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.OrderTrack;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.vo.OrderTrackVO;
import com.jialian.api.service.Order.OrderTrackServiceApi;
import com.jialian.core.persistence.reader.OrderTrackReaderMapper;
import com.jialian.core.persistence.writer.OrderTrackWriterMapper;

@Service("orderTrackService")
public class OrderTrackServiceImpl implements OrderTrackServiceApi{
	
	@Resource
	private OrderTrackReaderMapper orderTrackReaderMapper;
	
	@Resource
	private OrderTrackWriterMapper orderTrackWriterMapper;
	
	public int countByWhere(Where where){
		return orderTrackReaderMapper.countByWhere(where);
	}

    public List<OrderTrack> selectByWhere(Where where){
    	return orderTrackReaderMapper.selectByWhere(where);
    }

    public OrderTrack selectByPrimaryKey(Long id){
    	return orderTrackReaderMapper.selectByPrimaryKey(id);
    }
    
    public int deleteByWhere(Where where){
    	return orderTrackWriterMapper.deleteByWhere(where);
    }

    public int deleteByPrimaryKey(Long id){
    	return orderTrackWriterMapper.deleteByPrimaryKey(id);
    }

    public OrderTrack insert(OrderTrack record){
    	int flag = orderTrackWriterMapper.insert(record);
    	if(flag > 0){
    		return record;
    	}
    	return null;
    }

    public OrderTrack insertSelective(OrderTrack record){
    	int flag = orderTrackWriterMapper.insertSelective(record);
    	if(flag > 0){
    		return record;
    	}
    	return null;
    }

    public int updateByWhereSelective(OrderTrack record, Where where){
    	return orderTrackWriterMapper.updateByWhereSelective(record, where);
    }

    public int updateByWhere(OrderTrack record, Where where){
    	return orderTrackWriterMapper.updateByWhere(record, where);
    }

    public int updateByPrimaryKeySelective(OrderTrack record){
    	return orderTrackWriterMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(OrderTrack record){
    	return orderTrackWriterMapper.updateByPrimaryKey(record);
    }

	@Override
	public OrderTrackVO selectTrackOrderAndPayRecord(Long id) {
		return orderTrackReaderMapper.selectTrackOrderAndPayRecord(id);
	}

	@Override
	public OrderTrackVO selectTrackOrderAndSemDecOrder(Long id) {
		return orderTrackReaderMapper.selectTrackOrderAndSemDecOrder(id);
	}
    
    
}
