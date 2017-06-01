package com.jialian.core.service.impl.Combo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.Combo;
import com.jialian.api.domain.entity.ComboInfo;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.ComboQuery;
import com.jialian.api.domain.vo.ComboComplexVO;
import com.jialian.api.domain.vo.ComboVo;
import com.jialian.api.service.Combo.ComboServiceApi;
import com.jialian.core.persistence.reader.ComboReaderMapper;
import com.jialian.core.persistence.reader.ResourceInfoReaderMapper;
import com.jialian.core.persistence.writer.ComboInfoWriterMapper;
import com.jialian.core.persistence.writer.ComboWriterMapper;

@Service("comboService")
public class ComboServiceImpl implements ComboServiceApi{

	@Resource
	private ComboReaderMapper comboReaderMapper;
	
	@Resource
	private ComboWriterMapper comboWriterMapper;
	
	@Resource
	private ComboInfoWriterMapper comboInfoWriterMapper;
	
	@Resource
	private ResourceInfoReaderMapper resourceInfoReaderMapper;
	
	@Override
	public int countByWhere(Where where) {
		return comboReaderMapper.countByWhere(where);
	}

	@Override
	public List<Combo> selectByWhere(Where where) {
		return comboReaderMapper.selectByWhere(where);
	}

	@Override
	public Combo selectByPrimaryKey(Long id) {
		return comboReaderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByWhere(Where where) {
		return comboWriterMapper.deleteByWhere(where);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return comboWriterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Combo insert(Combo record) {
		comboWriterMapper.insert(record);
		return record;
	}

	@Override
	public Combo insertSelective(Combo record) {
		comboWriterMapper.insertSelective(record);
		return record;
	}

	@Override
	public int updateByWhereSelective(Combo record, Where where) {
		return comboWriterMapper.updateByWhereSelective(record, where);
	}

	@Override
	public int updateByWhere(Combo record, Where where) {
		return comboWriterMapper.updateByWhere(record, where);
	}

	@Override
	public int updateByPrimaryKeySelective(Combo record) {
		return comboWriterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Combo record) {
		return comboWriterMapper.updateByPrimaryKey(record);
	}

	@Override
	public ServiceResult<List<ComboVo>> getComboList(Long id) {
		ServiceResult<List<ComboVo>> result=new ServiceResult<List<ComboVo>>();
		List<ComboVo> list=comboReaderMapper.selectComboListById(id);
		result.setData(list);
		if(list.size()>0){
			String no=list.get(0).getNo();
			List<ResourceInfo> infos=resourceInfoReaderMapper.selectImgByObjectTypeAndObjectId((short) 7, (long) 0,no);
			Map<String, Object> dataMap=new HashMap<String, Object>();
			dataMap.put("imgDate", infos);
			result.setDataMap(dataMap);
		}
		
		
		result.setOk(true);
		if(list.size()>0){
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		
		return result;
	}

	//待完善食物管理
	@Override
	public ServiceResult<Object> updateCombo(Combo combo, ComboInfo comboInfo) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		comboWriterMapper.updateByPrimaryKeySelective(combo);
		comboInfoWriterMapper.updateByPrimaryKeySelective(comboInfo);
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setComment("修改成功");
		return result;
	}

	@Override
	public ServiceResult<Page<Combo>> getComboListByQuery(ComboQuery query) {
		ServiceResult<Page<Combo>> result=new ServiceResult<Page<Combo>>();
		int countRecord=comboReaderMapper.selectComboCountByQuery(query);
		Page<Combo> page=new Page<Combo>(query.getCurrentPage(), countRecord, query.getOnePageCount());
		if(countRecord>0){
			page.setStartIndex(page.getStartIndex());
			List<Combo> list=comboReaderMapper.selectComboListByQuery(query);
			page.setList(list);
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
		}
		result.setData(page);
		return result;
	}

	@Override
	public ServiceResult<Object> addCombo(Combo combo) {
		ServiceResult<Object> result=new ServiceResult<Object>();
		int i=comboWriterMapper.insertSelective(combo);
		if(i>0){
			result.setOk(true);
			result.setMsgCode(Const.SUCCESS_CODE);
			result.setComment("添加成功");
		}else{
			result.setComment("添加失败");
		}
		return result;
	}

	@Override
	public ComboComplexVO selectComplexComboByPrimaryKey(Long id) {
		return comboReaderMapper.selectComplexComboByPrimaryKey(id);
	}

	@Override
	public List<Combo> selectComboList() {
		return comboReaderMapper.selectComboList();
	}

	@Override
	public ServiceResult<List<Combo>> getComboAll() {
		ServiceResult<List<Combo>> result=new ServiceResult<List<Combo>>();
		List<Combo> list=comboReaderMapper.selectComboAll();
		result.setOk(true);
		result.setMsgCode(Const.SUCCESS_CODE);
		result.setData(list);
		return result;
	}

}
