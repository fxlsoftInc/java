package com.jialian.core.service.impl.Log;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.entity.OperateLog;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.core.persistence.reader.OperateLogReaderMapper;
import com.jialian.core.persistence.writer.OperateLogWriterMapper;

@Service("logService")
public class LogServiceImpl implements LogServiceApi{

	@Resource
	private OperateLogReaderMapper logReaderMapper;
	
	@Resource
	private OperateLogWriterMapper logWriterMapper;
	
	@Override
	public int countByWhere(Where where) {
		return logReaderMapper.countByWhere(where);
	}

	@Override
	public List<OperateLog> selectByWhere(Where where) {
		return logReaderMapper.selectByWhere(where);
	}

	@Override
	public OperateLog selectByPrimaryKey(Long id) {
		return logReaderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByWhere(Where where) {
		return logWriterMapper.deleteByWhere(where);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return logWriterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OperateLog record) {
		return logWriterMapper.insert(record);
	}

	@Override
	public int insertSelective(OperateLog record) {
		return logWriterMapper.insertSelective(record);
	}

	@Override
	public int logByAdmin(String userName, Long userId, String ip, String module, String content) {
		OperateLog log = new OperateLog();
		log.setOperateName(userName);
		log.setOperateId(userId);
		log.setOperateModule(module);
		log.setOperateContent(content);
		log.setIp(ip);
		return logWriterMapper.insertSelective(log);
	}

}
