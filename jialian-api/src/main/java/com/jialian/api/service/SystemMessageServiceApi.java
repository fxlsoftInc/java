package com.jialian.api.service;

import java.util.List;

import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.SystemMessage;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.query.SystemMessageQuery;
import com.jialian.api.domain.vo.SystemMsgVo;

public interface SystemMessageServiceApi {

	/**
	 * 查询所有系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月16日 下午4:15:41
	 *@Version:1.0
	 */
	ServiceResult<List<SystemMessage>> selectAllSystemMessage(String mobile);

	/**
	 * 系统消息详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月17日 下午1:57:58
	 *@Version:1.0
	 */
	ServiceResult<SystemMessage> selectByWhere(long id);

	/**
	 * 后台--系统消息列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午6:11:38
	 *@Version:1.0
	 */
	List<SystemMsgVo> selectSystemMessageByWhere(SystemMessageQuery systemMessageQuery);

	/**
	 * 后台--系统消息列表计数
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午6:12:58
	 *@Version:1.0
	 */
	int countByWhere(Where where);

	/**
	 * 后台 -- 根据id查询系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午6:55:08
	 *@Version:1.0
	 */
	SystemMessage selectByPrimaryKey(Integer id);

	/**
	 * 后台-- 删除系统消息，修改状态为0
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午6:55:54
	 *@Version:1.0
	 */
	int updateSystemMessageByPrimaryKey(SystemMessage systemMessage);

	/**
	 * 后台-- 添加系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 上午11:35:52
	 *@Version:1.0
	 */
	int addSystemMessage(SystemMessage systemMessage);

	/**
	 * o2o-- 删除系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月25日 下午2:22:23
	 *@Version:1.0
	 */
	ServiceResult<SystemMessage> deleteSystemMessageById(Long id);

	/**
	 * o2o-- 系统消息列表(带分页)
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月25日 下午5:25:02
	 *@Version:1.0
	 */
	ServiceResult<Page<SystemMessage>> getSystemMessageListByQuery(SystemMessageQuery query,String mobile);

	/**
	 * 发送系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月19日 下午5:37:51
	 *@Version:1.0
	 */
	int insertSystemMessage(SystemMessage systemMessage);

	int countSystemMsgByWhere();

}
