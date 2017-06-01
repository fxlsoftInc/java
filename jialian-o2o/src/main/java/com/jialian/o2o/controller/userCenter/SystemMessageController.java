package com.jialian.o2o.controller.userCenter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.SystemMessage;
import com.jialian.api.domain.query.SystemMessageQuery;
import com.jialian.api.service.SystemMessageServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.StringUtils;

/**
 *   系统消息相关操作控制器
 *@Description: 
 *@Author: shx  408640463@qq.com
 *@Since:2015年12月16日 下午3:20:25
 *@Version:1.0
 */
@Controller
@RequestMapping("/systemMessage")
public class SystemMessageController {
	
	@Resource
	private SystemMessageServiceApi systemMessageService;
	
	/**
	 *  用户系统消息查询
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月16日 下午3:25:16
	 *@Version:1.0
	 */
	@RequestMapping("/selectAllSystemMessage.do")
	@ResponseBody
    public JsonResult selectAllSystemMessage(@RequestBean String mobile,
    		HttpServletRequest request){
    	JsonResult result = new JsonResult();
    	if(StringUtils.isStrNull(mobile)){//
    		result.setSuccess(false);
    		result.setStateCode(Const.ERROR_CODE);
    		result.setMessage("该用户不存在");
    	}else{
    		ServiceResult<List<SystemMessage>> serviceResult = systemMessageService.selectAllSystemMessage(mobile);
    		if(serviceResult.isOk()){//查找成功
    			result.setSuccess(true);
    			result.setStateCode(Const.SUCCESS_CODE);
    			result.addData("systemMessage", serviceResult.getData());
    			result.addData("totalItems", serviceResult.getComment());
    			result.setMessage("查询成功");
    		}else{//查找失败
    			result.setSuccess(false);
        		result.setStateCode(Const.ERROR_UNKNOWN_CODE);
        		result.setMessage("未知错误");
    		}
    	}
    	return result;
    }
	
	
	/**
	 * 系统消息详情
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月17日 下午1:51:27
	 *@Version:1.0
	 */
	@RequestMapping("/selectSystemMessageById.do")
	@ResponseBody
	public JsonResult selectSystemMessageById(@RequestBean Long id){
		JsonResult result = new JsonResult();
		if(id== null || id <= 0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_RECORD_DELETED);
			result.setMessage("该消息不存在或已被删除");
		}else{
			ServiceResult<SystemMessage> serviceResult = systemMessageService.selectByWhere(id);
			if(serviceResult.isOk()){
				if(serviceResult.getData() != null && !serviceResult.getData().equals("")){
					result.setSuccess(true);
					result.setStateCode(serviceResult.getMsgCode());
					result.setMessage(serviceResult.getComment());
					result.addData("systemMessage", serviceResult.getData());
				}else{
					result.setSuccess(true);
					result.setStateCode(serviceResult.getMsgCode());
					result.setMessage(serviceResult.getComment());
				}
			}else{
				result.setSuccess(false);
				result.setStateCode(serviceResult.getMsgCode());
				result.setMessage(serviceResult.getComment());
			}
		}
		return result;
	}
	
	/**
	 * 删除系统消息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月25日 下午2:07:30
	 *@Version:1.0
	 */
	@RequestMapping("/deleteSystemMessageById.do")
	@ResponseBody
	public JsonResult deleteSystemMessageById(@RequestBean Long id,HttpServletRequest request ){
		JsonResult result = new JsonResult();
		if(id == null || id <= 0){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数错误");
		}else{
			ServiceResult<SystemMessage> serviceResult = systemMessageService.deleteSystemMessageById(id);
			if(serviceResult.isOk()){
				result.setSuccess(true);
				result.setStateCode(serviceResult.getMsgCode());
				result.setMessage(serviceResult.getComment());
			}else{
				result.setSuccess(false);
				result.setStateCode(serviceResult.getMsgCode());
				result.setMessage(serviceResult.getComment());
			}
		}
		return result;
	}
	
	
	/**
	 * o2o--系统消息列表(带分页)
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月25日 下午5:15:36
	 *@Version:1.0
	 */
	@RequestMapping("/getSystemMessageList.do")
	@ResponseBody
	public JsonResult getSystemMessageList(@RequestBean SystemMessageQuery query,@RequestBean String mobile, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<SystemMessage>> serviceResult = systemMessageService.getSystemMessageListByQuery(query,mobile);
		if(serviceResult.isOk()){
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(serviceResult.getMsgCode());
			jsonResult.setMessage(serviceResult.getComment());
			jsonResult.addData("page", serviceResult.getData());
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(serviceResult.getMsgCode());
			jsonResult.setMessage(serviceResult.getComment());
		}
		return jsonResult;
	}
	
}
