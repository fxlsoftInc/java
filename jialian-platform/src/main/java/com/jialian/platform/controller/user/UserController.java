package com.jialian.platform.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.query.UserQuery;
import com.jialian.api.service.UserServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.MD5;
import com.jialian.common.util.StringUtils;

/**
 *@Description: 用户信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月18日 上午11:52:01
 *@Version:1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	UserServiceApi userServiceApi;

	/**
	* @Description: 用户信息列表(分页)
	* @param @param query 用户查询封装类
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月18日 上午11:56:11
	 */
	@RequestMapping("/userMesList.do")
	@ResponseBody
	public JsonResult userMesList(@RequestBean UserQuery query){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<Page<User>> result=userServiceApi.getUserMesList(query);
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		if(result.isOk()){
			jsonResult.setSuccess(true);
			jsonResult.setDataObj(result.getData());
		}
		return jsonResult;
	}
	
	/**
	* @Description: 用户信息获取
	* @param @param id
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月15日 下午5:08:11
	 */
	@RequestMapping("/getUserMes.do")
	@ResponseBody
	JsonResult getUserMes(@RequestBean Long id){
		JsonResult jsonResult=new JsonResult();
		ServiceResult<User> result=userServiceApi.getUserMesById(id);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		jsonResult.addData("user", result.getData());
		return jsonResult;
	}
	
	
	/**
	* @Description: 修改用户信息（修改、删除）
	* @param @param user
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月18日 下午2:10:40
	 */
	@RequestMapping("/updateUserMes.do")
	@ResponseBody
	public JsonResult updateUserMes(@RequestBean User user,@RequestBean String oldPassWord){
		JsonResult jsonResult=new JsonResult();
		//MD5加密
		if(!StringUtils.isStrNull(user.getPassword())){
			user.setPassword(MD5.md5(user.getPassword()));
		}
		ServiceResult<User> result=userServiceApi.updateUser(user,oldPassWord);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
}
