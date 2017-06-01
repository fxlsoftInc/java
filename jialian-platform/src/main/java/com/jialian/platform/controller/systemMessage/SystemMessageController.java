package com.jialian.platform.controller.systemMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.SystemMessage;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.query.SystemMessageQuery;
import com.jialian.api.domain.vo.SystemMessageVo;
import com.jialian.api.domain.vo.SystemMsgVo;
import com.jialian.api.service.SystemMessageServiceApi;
import com.jialian.api.service.UserServiceApi;
import com.jialian.api.service.Admin.AdminUserServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.sms.SMS;
import com.jialian.common.util.sms.SMSResult;

/**
 * 系统消息相关控制器
 *@Description:
 *@Author: shx  408640463@qq.com
 *@Since:2015年12月19日 下午5:56:39
 *@Version:1.0
 */
@Controller
@RequestMapping("/systemMessage")
public class SystemMessageController {

	@Resource
    private SystemMessageServiceApi systemMessageService;
	
	@Resource
	private AdminUserServiceApi adminUserService;
	
	@Resource
	private UserServiceApi userService;
	/**
	 * 后台--系统消息列表(带分页)
	 * @Param:@param startIndex 起始查询页码
	 * @Param:@param pageSize 需要查询的页数
	 * @Param:@return JsonResult 
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午5:58:39
	 *@Version:1.0
	 */
	@RequestMapping("/selectSystemMessageList.do")
	@ResponseBody
	public JsonResult selectSystemMessageList(@RequestBean Integer startIndex,@RequestBean Integer pageSize){
		JsonResult jsonResult = new JsonResult();
		SystemMessageQuery systemMessageQuery = new SystemMessageQuery();
		if(startIndex == null || startIndex < 0 ){
			startIndex = 0;
		}
		if(pageSize == null || pageSize < 0 ){
			pageSize = 10;
		}
		systemMessageQuery.setStartIndex(startIndex);
		systemMessageQuery.setOnePageCount(pageSize);
		List<SystemMsgVo> systemMessagesList = systemMessageService.selectSystemMessageByWhere(systemMessageQuery);
		int totalItems = systemMessageService.countSystemMsgByWhere();
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setMessage("查询成功");
		jsonResult.addData("systemMessagesList", systemMessagesList);
		jsonResult.setDataObj(totalItems);
		return jsonResult;
	}
	
	
	/**
	 * 系统消息删除
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月19日 下午6:25:08
	 *@Version:1.0
	 */
	@RequestMapping("/deleteSystemMessageById.do")
	@ResponseBody
	public JsonResult deleteSystemMessageById(Integer id,HttpServletRequest request){
	    JsonResult jsonResult = new JsonResult();
		if(id == null || id <= 0){
	    	jsonResult.setSuccess(false);
	    	jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
	    	jsonResult.setMessage("参数错误");
	    }else{
	    	SystemMessage systemMessage = systemMessageService.selectByPrimaryKey(id);
	    	if(systemMessage == null || systemMessage.equals("")){
	    		jsonResult.setSuccess(false);
		    	jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
		    	jsonResult.setMessage("删除失败,该消息不存在或已删除");
	    	}else{
	    		systemMessage.setStatus(0);
	    		systemMessage.setUpdateTime(new Date());
	    		int result = systemMessageService.updateSystemMessageByPrimaryKey(systemMessage);
	    		if(result == 1){
	    			jsonResult.setSuccess(true);
			    	jsonResult.setStateCode(Const.SUCCESS_CODE);
			    	jsonResult.setMessage("删除成功");
	    		}else{
	    			jsonResult.setSuccess(false);
			    	jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			    	jsonResult.setMessage("删除失败,未知错误");
	    		}
	    		
	    	}
	    }	
		return jsonResult;
	}
	
	
	/**
	 * 后台--系统消息添加
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 上午11:21:24
	 *@Version:1.0
	 */
	@RequestMapping("/addSystemMessage.do")
	@ResponseBody
	public JsonResult addSystemMessage(SystemMessage systemMessage,int userId,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		//测试数据
		systemMessage.setMessageContent("添加测试内容1");
		systemMessage.setMessageStatus(1);
		systemMessage.setMessageTitle("添加测试内容1");
		systemMessage.setRemark("添加测试内容1");
		systemMessage.setStatus(1);
		systemMessage.setUserId(userId);
		
		int result = systemMessageService.addSystemMessage(systemMessage);
		if(result == 1){
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("添加成功");
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
			jsonResult.setMessage("添加失败");
		}
		return jsonResult;
	}
	
	/**
	 * 根据id查询系统消息详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 下午1:49:24
	 *@Version:1.0
	 */
	@RequestMapping("/selectSystemMessageById.do")
	@ResponseBody
	public JsonResult selectSystemMessageById(Integer id,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(id == null || id <=0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			SystemMessage systemMessage = systemMessageService.selectByPrimaryKey(id);
			if(systemMessage == null || systemMessage.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
				jsonResult.setMessage("该消息已删除或不存在");
			}else{
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("查询成功");
				jsonResult.addData("systemMessage", systemMessage);
			}
		}
		return jsonResult;
	}
	
	/**
	 * 根据Id编辑系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 上午11:51:24
	 *@Version:1.0
	 */
	@RequestMapping("/editSystemMessageById.do")
	@ResponseBody
	public JsonResult editSystemMessageById(SystemMessage systemMessage, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(systemMessage == null || systemMessage.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
				jsonResult.setMessage("该消息已删除或不存在");
			}else{
				int result = systemMessageService.updateSystemMessageByPrimaryKey(systemMessage);
				if(result == 1){
					jsonResult.setSuccess(true);
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					jsonResult.setMessage("修改成功");
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
					jsonResult.setMessage("修改失败，未知错误");
				}
			}
		return jsonResult;
	}
	
	/**
	 * 发送系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月19日 下午4:00:07
	 *@Version:1.0
	 */
	@RequestMapping("/sendNoticeToUser.do")
	@ResponseBody
	public JsonResult sendNoticeToUser(@RequestBean SystemMessageVo systemMessageVo,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		User user = new User();
		SystemMessage systemMessage = new SystemMessage();
		if(systemMessageVo == null || systemMessageVo.equals("") ){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
			return jsonResult;
		}
		HttpSession session = request.getSession();
		AdminUser adminUser = (AdminUser) session.getAttribute(Const.ADMIN_SESSION_USER);
		if(adminUser == null ){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_LOGIN_CODE);
			jsonResult.setMessage("用户未登录，请重新登录");
			return jsonResult;
		}
		systemMessageVo.setSend_user_id(Integer.parseInt(adminUser.getId().toString()));//存储发送者ID
		if(systemMessageVo.getSend_time_type() == 1){//即刻发送
			systemMessageVo.setSendTime(new Date());
		}
		if(systemMessageVo.getSend_time_type() == 2){//规定时间发送
			if(systemMessageVo.getSendTime() == null || systemMessageVo.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("请选择预约发送的时间");
				return jsonResult;
			}
		}
		if(systemMessageVo.getSendObj() == 2){//单个对象
			if(systemMessageVo.getTelephone() == null || systemMessageVo.getTelephone().equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("电话号码不能为空");
				return jsonResult;
			}
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("telephone", systemMessageVo.getTelephone());
			List<User> userList = userService.selectByWhere(where);
			if(userList  != null && userList.size() > 0){
				user = userList.get(0);//此时可拿到被发送者Id
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("该号码不存在，请重新输入");
				return jsonResult;
			}
		}
		int result = 0;
		String str = "";
		if(systemMessageVo.getSendObj() == 1){
			str= systemMessageVo.getSendObjId();
		}
		systemMessage.setMessageContent(systemMessageVo.getMessageContent());
		systemMessage.setMessageStatus(1);
		systemMessage.setMessageTitle(systemMessageVo.getMessageTitle());
		systemMessage.setSendTimeType(systemMessageVo.getSend_time_type());
		systemMessage.setSendObj(systemMessageVo.getSendObj());
		systemMessage.setSendTime(systemMessageVo.getSendTime());
		systemMessage.setSendUserId(Integer.parseInt(adminUser.getId().toString()));
		systemMessage.setStatus(1);
		systemMessage.setMessageStatus(2);//未读
		if(systemMessageVo.getSendType() > 0 && systemMessageVo.getSendType() == 1){//发送短信
			SMS sms = new SMS();
			if(systemMessageVo.getSend_time_type() == 2){
				sms.setStime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(systemMessageVo.getSendTime()));
			}
			try {
				SMSResult smsResult = sms.sendSMS(systemMessageVo.getMessageContent(),systemMessageVo.getTelephone());
				if(smsResult.success){
					systemMessage.setUserId(Integer.parseInt(user.getId().toString()));
					systemMessage.setSendType(systemMessageVo.getSendType());
					result  = systemMessageService.insertSystemMessage(systemMessage);
					if(result < 0){
						 jsonResult.setSuccess(false);
						 jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
						 jsonResult.setMessage("发送失败");
					}else{
						 jsonResult.setSuccess(true);
						 jsonResult.setStateCode(Const.SUCCESS_CODE);
						 jsonResult.setMessage("发送成功");
					}
				}else{
					jsonResult.setStateCode(Const.ERROR_CODE);
					jsonResult.setSuccess(false);
					jsonResult.setMessage("发送失败，请重新发送");
					return jsonResult;
				}
			} catch (IOException e) {
				jsonResult.setStateCode(Const.ERROR_CODE);
				jsonResult.setSuccess(false);
				jsonResult.setMessage("网络故障，请重新发送");
				return jsonResult;
			}
			
		}else if(systemMessageVo.getSendType() > 0 && systemMessageVo.getSendType() == 2){//发送站内信
			 if(str != null && !str.equals("")){
				 String objId[] = str.split(",");//批量发送对象Id
				 for (int i = 0; i < objId.length; i++) {
					 systemMessage.setUserId(Integer.parseInt(objId[i]));
					 result  = systemMessageService.insertSystemMessage(systemMessage);
				}
			 }
			 systemMessage.setUserId(Integer.parseInt(user.getId().toString()));
			 systemMessage.setSendType(systemMessageVo.getSendType());
			 result  = systemMessageService.insertSystemMessage(systemMessage);
			 if(result < 0){
				 jsonResult.setSuccess(false);
				 jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				 jsonResult.setMessage("发送失败");
				
			 }else{
				 jsonResult.setSuccess(true);
				 jsonResult.setStateCode(Const.SUCCESS_CODE);
				 jsonResult.setMessage("发送成功");
			 }
		}
		 return jsonResult;
	}
	
	/**
	 * 查询前端用户列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月20日 下午2:08:33
	 *@Version:1.0
	 */
	@RequestMapping("/selectUserList.do")
	@ResponseBody
	public JsonResult selectUserList(@RequestBean  int startIndex,@RequestBean int pageSize){
		JsonResult jsonResult = new JsonResult();
		Where where = new Where();
		where.setLimit(startIndex);
		where.setOffset(pageSize);
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("status", 1);
		List<User> userList = userService.selectByWhere(where);
		int totalItems = userService.countByWhere(where);
		jsonResult.setSuccess(true);
		jsonResult.setMessage("查询成功");
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.addData("userList", userList);
		jsonResult.addData("totalItems", totalItems);
		return jsonResult;
	}
}
