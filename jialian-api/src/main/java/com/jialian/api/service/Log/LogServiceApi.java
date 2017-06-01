package com.jialian.api.service.Log;

import java.util.List;

import com.jialian.api.domain.entity.OperateLog;
import com.jialian.api.domain.entity.Where;

public interface LogServiceApi {

	int countByWhere(Where where);

	List<OperateLog> selectByWhere(Where where);

	OperateLog selectByPrimaryKey(Long id);

	int deleteByWhere(Where where);

	int deleteByPrimaryKey(Long id);

	int insert(OperateLog record);

	int insertSelective(OperateLog record);

	int logByAdmin(String userName, Long userId, String ip, String module, String content);
}
