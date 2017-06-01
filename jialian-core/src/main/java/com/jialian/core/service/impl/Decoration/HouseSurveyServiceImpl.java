package com.jialian.core.service.impl.Decoration;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.HouseSurvey;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Decoration.HouseSurveyServiceApi;
import com.jialian.core.persistence.reader.HouseSurveyReaderMapper;
import com.jialian.core.persistence.writer.HouseSurveyWriterMapper;

@Service("houseSurveyService")
public class HouseSurveyServiceImpl implements HouseSurveyServiceApi {
	
	@Resource
	private HouseSurveyReaderMapper houseSurveyReaderMapper;
	
	@Resource
	private HouseSurveyWriterMapper houseSruveyWriterMapper;
	
	public int countByWhere(Where where){
		return houseSurveyReaderMapper.countByWhere(where);
	}

	public List<HouseSurvey> selectByWhere(Where where){
		return houseSurveyReaderMapper.selectByWhere(where);
	}

	public HouseSurvey selectByPrimaryKey(Long id){
		return houseSurveyReaderMapper.selectByPrimaryKey(id);
	}
    
	public int deleteByWhere(Where where){
		return houseSruveyWriterMapper.deleteByWhere(where);
	}

	public int deleteByPrimaryKey(Long id){
		return houseSruveyWriterMapper.deleteByPrimaryKey(id);
	}

	public HouseSurvey insert(HouseSurvey record){
		houseSruveyWriterMapper.insert(record);
		return record;
	}

	public HouseSurvey insertSelective(HouseSurvey record){
		houseSruveyWriterMapper.insertSelective(record);
		return record;
	}

	public int updateByWhereSelective(HouseSurvey record, Where where){
		return houseSruveyWriterMapper.updateByWhereSelective(record, where);
	}

	public int updateByWhere(HouseSurvey record, Where where){
		return houseSruveyWriterMapper.updateByWhere(record, where);
	}

	public int updateByPrimaryKeySelective(HouseSurvey record){
		return houseSruveyWriterMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(HouseSurvey record){
		return houseSruveyWriterMapper.updateByPrimaryKey(record);
	}
}
