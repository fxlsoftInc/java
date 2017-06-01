package com.jialian.core.service.impl.Order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.SubOrder;
import com.jialian.api.domain.query.OrderQuery;
import com.jialian.api.domain.vo.SubLookVo;
import com.jialian.api.domain.vo.SubOrderInfoVO;
import com.jialian.api.service.Order.SubOrderServiceApi;
import com.jialian.core.persistence.reader.SubOrderReaderMapper;
import com.jialian.core.persistence.writer.SubOrderWriterMapper;

@Service("subOrderService")
public class SubOrderServiceImpl implements SubOrderServiceApi {
	
	@Resource
	SubOrderWriterMapper subOrderWriterMapper;

	@Resource
	SubOrderReaderMapper subOrderReaderMapper;
	
	@Override
	public ServiceResult<Object> updateSubscribeOrderStatus(SubOrder subOrder) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=subOrderWriterMapper.updateByPrimaryKeySelective(subOrder);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}else{
			result.setComment("修改失败");
		}
		return result;
	}

	@Override
	public SubOrderInfoVO selectByOrderNo(String orderNo) {
		return subOrderReaderMapper.selectByOrderNo(orderNo);
	}

	@Override
	public ServiceResult<Page<SubLookVo>> getSubLookOrderList(OrderQuery query) {
		ServiceResult<Page<SubLookVo>> result=new ServiceResult<Page<SubLookVo>>();
		int countRecord=subOrderReaderMapper.selectSubLookOrderCount(query);
		Page<SubLookVo> page=new Page<>(query.getCurrentPage(), countRecord, query.getOnePageCount());
		if(countRecord>0){
			query.setStartIndex(page.getStartIndex());
			List<SubLookVo> list=subOrderReaderMapper.selectSubLookOrderList(query);
			page.setList(list);
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		result.setOk(true);
		result.setData(page);
		return result;
	}

}
