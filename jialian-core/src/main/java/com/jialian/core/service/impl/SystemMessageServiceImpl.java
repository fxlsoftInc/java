package com.jialian.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.SystemMessage;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.query.SystemMessageQuery;
import com.jialian.api.domain.vo.SystemMsgVo;
import com.jialian.api.service.SystemMessageServiceApi;
import com.jialian.common.util.StringUtils;
import com.jialian.core.persistence.reader.SystemMessageReaderMapper;
import com.jialian.core.persistence.reader.UserReaderMapper;
import com.jialian.core.persistence.writer.SystemMessageWriterMapper;

@Service("systemMessageService")
public class SystemMessageServiceImpl implements SystemMessageServiceApi{

	@Resource
	private SystemMessageReaderMapper systemMessageReaderMapper;
	
	@Resource
	private UserReaderMapper userReaderMapper;
	
	@Resource
	private SystemMessageWriterMapper systemMessageWriterMapper;
	
	/**
	 * 根据用户id查询该用户的所有系统消息
	 */
	@Override
	public ServiceResult<List<SystemMessage>> selectAllSystemMessage(String mobile) {
		ServiceResult<List<SystemMessage>> serviceResult = new ServiceResult<List<SystemMessage>>();
		if(StringUtils.isStrNull(mobile)){
			serviceResult.setOk(false);
			serviceResult.setMsgCode(Const.ERROR_RECORD_DELETED);
			serviceResult.setComment("该用户已删除或不存在");
		}else{
			Where where = new Where();
			Criteria criteria =where.createCriteria();
			criteria.andEqualTo("telephone", mobile);
			List<User> userList = userReaderMapper.selectByWhere(where);
			if(userList != null && userList.size() > 0){
				User user = userList.get(0);
				long userId = user.getId();
				List<SystemMessage> systemMessagesList = systemMessageReaderMapper.selectAllSystemMessageById(userId);
				Integer totalItems = 0;
				if(systemMessagesList != null && systemMessagesList.size() > 0){
	    			 totalItems = systemMessagesList.size();
	    		}
				serviceResult.setOk(true);
				serviceResult.setMsgCode(Const.SUCCESS_CODE);
				serviceResult.setData(systemMessagesList);
				serviceResult.setComment(totalItems.toString());
			}else{
				serviceResult.setOk(false);
				serviceResult.setMsgCode(Const.ERROR_RECORD_DELETED);
				serviceResult.setComment("该用户已删除或不存在");
			}
		}
		
		return serviceResult;
	}

	/**
	 * 系统消息详情
	 */
	@Override
	public ServiceResult<SystemMessage> selectByWhere(long id) {
		ServiceResult<SystemMessage> serviceResult = new ServiceResult<>();
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("id", id);
		List<SystemMessage> systemMessagesList = systemMessageReaderMapper.selectByWhere(where);
		if(systemMessagesList != null && systemMessagesList.size() > 0){
			SystemMessage systemMessage = systemMessagesList.get(0);
			systemMessage.setMessageStatus(1);//修改为已读
			systemMessage.setUpdateTime(new Date());
			systemMessageWriterMapper.updateByPrimaryKeySelective(systemMessage);
			serviceResult.setData(systemMessage);
			serviceResult.setOk(true);
			serviceResult.setMsgCode(Const.SUCCESS_CODE);
			serviceResult.setComment("查询成功");
		}else{
			serviceResult.setOk(true);
			serviceResult.setMsgCode(Const.SUCCESS_CODE);
			serviceResult.setComment("您目前还没有系统消息哦");
		}
		return serviceResult;
	}

	/**
	 * 后台--系统消息列表
	 */
	@Override
	public List<SystemMsgVo> selectSystemMessageByWhere(SystemMessageQuery systemMessageQuery) {
		return systemMessageReaderMapper.selectSystemMessageByWhere(systemMessageQuery);
	}

	/**
	 * 后台--系统消息列表总数
	 */
	@Override
	public int countByWhere(Where where) {
		return systemMessageReaderMapper.countByWhere(where);
	}

	/**
	 * 后台 -- 根据id查询系统消息
	 */
	@Override
	public SystemMessage selectByPrimaryKey(Integer id) {
		return systemMessageReaderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 后台-- 删除系统消息，修改状态为0
	 */
	@Override
	public int updateSystemMessageByPrimaryKey(SystemMessage systemMessage) {
		return systemMessageWriterMapper.updateByPrimaryKeySelective(systemMessage);
	}

	/**
	 * 后台-- 添加系统消息
	 */
	@Override
	public int addSystemMessage(SystemMessage systemMessage) {
		return systemMessageWriterMapper.insertSelective(systemMessage);
	}

	/**
	 * o2o-- 删除系统消息
	 */
	@Override
	public ServiceResult<SystemMessage> deleteSystemMessageById(Long id) {
		ServiceResult<SystemMessage>  serviceResult= new ServiceResult<SystemMessage>();
		SystemMessage systemMessage = systemMessageReaderMapper.selectByPrimaryKey(Integer.parseInt(id.toString()));
		if(systemMessage == null || systemMessage.equals("")){
			serviceResult.setOk(false);
			serviceResult.setMsgCode(Const.ERROR_RECORD_DELETED);
			serviceResult.setComment("该消息已被删除或不存在");
		}else{
			systemMessage.setStatus(0);
			systemMessage.setUpdateTime(new Date());
			systemMessage.setRemark("已删除");
			systemMessageWriterMapper.updateByPrimaryKeySelective(systemMessage);
			serviceResult.setOk(true);
			serviceResult.setMsgCode(Const.SUCCESS_CODE);
			serviceResult.setComment("删除成功");
		}
		return serviceResult;
	}

	/**
	 * o2o--系统消息列表(带分页)
	 */
	@Override
	public ServiceResult<Page<SystemMessage>> getSystemMessageListByQuery(
			SystemMessageQuery query,String mobile) {
		ServiceResult<Page<SystemMessage>> serviceResult = new ServiceResult<Page<SystemMessage>>();
		User user = userReaderMapper.selectUserByTelephone(mobile);
		if(user == null || user.equals("")){
			serviceResult.setOk(false);
			serviceResult.setMsgCode(Const.ERROR_NODATA_CODE);
			serviceResult.setComment("用户不存在");
		}else{
			long id =user.getId();
			int userId = (int)id;
			query.setUserId(userId);
			int total = systemMessageReaderMapper.getSystemMessageCountByQuery(query);
			Page<SystemMessage> page = new Page<SystemMessage>(query.getCurrentPage(), total, query.getOnePageCount());
			if(total > 0 ){
				query.setStartIndex(page.getStartIndex());
				List<SystemMessage> systemMessageList = systemMessageReaderMapper.getSystemMessageListByQuery(query);
				page.setList(systemMessageList);
				page.setCountRecord(systemMessageList.size());
				serviceResult.setOk(true);
				serviceResult.setMsgCode(Const.SUCCESS_CODE);
				serviceResult.setComment("查询成功");
				serviceResult.setData(page);
			}else{
				serviceResult.setOk(false);
				serviceResult.setMsgCode(Const.ERROR_NODATA_CODE);
				serviceResult.setComment("没有消息啦");
			}
		}
		return serviceResult;
	}

	@Override
	public int insertSystemMessage(SystemMessage systemMessage) {
		return systemMessageWriterMapper.insertSelective(systemMessage);
	}

	@Override
	public int countSystemMsgByWhere() {
		return systemMessageReaderMapper.countSystemMsgByWhere();
	}

}
