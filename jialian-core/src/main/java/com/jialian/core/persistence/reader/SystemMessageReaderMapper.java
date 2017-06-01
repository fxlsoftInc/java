package com.jialian.core.persistence.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jialian.api.domain.entity.SystemMessage;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.SystemMessageQuery;
import com.jialian.api.domain.vo.SystemMsgVo;

public interface SystemMessageReaderMapper {
	
    int countByWhere(Where where);

    List<SystemMessage> selectByWhere(Where where);

    SystemMessage selectByPrimaryKey(Integer id);

    /**
     * 
     *@Description:
     *@Author: shx  408640463@qq.com
     *@Since:2015年12月16日 下午4:37:25
     *@Version:1.0
     */
	List<SystemMessage> selectAllSystemMessageById(long userId);

	int getSystemMessageCountByQuery(@Param("query")SystemMessageQuery query);

	List<SystemMessage> getSystemMessageListByQuery(@Param("query")SystemMessageQuery query);

	List<SystemMsgVo> selectSystemMessageByWhere(@Param("query")SystemMessageQuery query);

	int countSystemMsgByWhere();


}