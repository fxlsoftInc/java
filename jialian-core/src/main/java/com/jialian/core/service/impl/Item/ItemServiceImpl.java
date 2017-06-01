package com.jialian.core.service.impl.Item;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Item;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Item.ItemServiceApi;
import com.jialian.core.persistence.reader.ItemReaderMapper;
import com.jialian.core.persistence.writer.ItemWriterMapper;

@Service("itemService")
public class ItemServiceImpl  implements ItemServiceApi {
	
	@Resource
	ItemReaderMapper itemReaderMapper;
	
	@Resource
	ItemWriterMapper itemWriterMapper;

	@Override
	public ServiceResult<Object> updateItem(Item item) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=itemWriterMapper.updateByPrimaryKeySelective(item);
		if(i==1){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("修改成功");
		}
		return result;
	}

	@Override
	public List<Item> selectByWhere(Where where) {
		return itemReaderMapper.selectByWhere(where);
	}

	@Override
	public int countByWhere(Where where) {
		return itemReaderMapper.countByWhere(where);
	}

	@Override
	public int updateByPrimaryKeySelective(Item item) {
		return itemWriterMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public Item selectByPrimaryKey(Long id) {
		return itemReaderMapper.selectByPrimaryKey(id);
	}

	@Override
	public Item addItem(Item item) {
		itemWriterMapper.insertSelective(item);
		return item;
	}

	@Override
	public List<Item> selectWithHouseStruByWhere(Where where) {
		return itemReaderMapper.selectWithHouseStruByWhere(where);
	}

}
