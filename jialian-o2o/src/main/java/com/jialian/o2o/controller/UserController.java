package com.jialian.o2o.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.domain.entity.User;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.service.UserServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.UuidUtil;

/**
 * @Description:预约用户相关操作控制器
 * @author:FxLsoft fxlsoft@163.com
 * @Since:2015年12月8日下午2:27:35
 * @Version:1.0
 */
@Controller
@RequestMapping("/web")
public class UserController {

	@Resource
	private UserServiceApi userService;
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private RedisTemplate<String, ?> redisTemplate;
	
	/**
	 * @Description: 预约用户登录 
	 * @Param:@param mobile 手机号
	 * @Param:@param password 密码
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月9日上午10:39:54
	 * @Version:1.0
	 */
	@RequestMapping("/userLogin.do")
	@ResponseBody
	public JsonResult userLogin(@RequestBean String mobile, @RequestBean String password){
		JsonResult result = new JsonResult();
		User user = userService.userLogin(mobile, password);
		if(user == null){
			result.setSuccess(false);
			result.setMessage("登录失败！");
			result.setStateCode(Const.ERROR_PARAM_CODE);
		}else{
			String appKey = UuidUtil.get32UUID().toUpperCase();
			String appSecret = UuidUtil.get32UUID().toUpperCase();
			List<ResourceInfo> resourceInfoList = new ArrayList<ResourceInfo>();
			//查询图片信息
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("object_id", user.getId());
			criteria.andEqualTo("object_type", 1);
			resourceInfoList = resourceInfoService.selectByWhere(where);
			//测试插入
			result.setSuccess(true);
			result.setMessage("登录成功！");
			result.setStateCode(Const.SUCCESS_CODE);
			result.addData("user", user);
			result.addData("appKey", appKey);
			result.addData("appSecret", appSecret);
			result.addData("picture", resourceInfoList == null || resourceInfoList.size() == 0?null : resourceInfoList.get(0));
			HashOperations<String, String, User> hashOps = redisTemplate.opsForHash();
			hashOps.put(appKey, appSecret, user);
			redisTemplate.expire(appKey, 30, TimeUnit.MINUTES);
		}
		return result;
	}
	
	@RequestMapping("getUserByKey.do")
	@ResponseBody
	public JsonResult getUserByKey(String appKey, String appSecret){
		JsonResult result = new JsonResult();
		HashOperations<String, String, User> hashOps = redisTemplate.opsForHash();
		User user = hashOps.get(appKey, appSecret);
		result.addData("user", user);
		if(user == null){
			result.setSuccess(false);
			result.setMessage("验证错误");
			result.setStateCode(Const.ERROR_LOGIN_CODE);
		}else{
			result.setSuccess(true);
			result.setMessage("验证正确");
			result.setStateCode(Const.SUCCESS_CODE);
		}
		return result;
	}
}
