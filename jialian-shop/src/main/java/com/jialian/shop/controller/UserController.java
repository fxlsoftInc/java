package com.jialian.shop.controller;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.User;
import com.jialian.api.service.UserServiceApi;
import com.jialian.api.service.ResourceInfo.ResourceInfoServiceApi;
import com.jialian.common.constants.CommonConstants;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.StringUtils;
import com.jialian.common.util.UuidUtil;

/**
 *@Description:用户登陆
 *@Author: zhls  联系方式：zhls1992@qq.com
 *@Since:2016年1月13日 上午11:42:53
 *@Version:1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserServiceApi userService;
	
	@Resource
	private ResourceInfoServiceApi resourceInfoService;
	
	@Resource
	private RedisTemplate<String, ?> redisTemplate;
	
	/**
	 * @Description: 用户登录 
	 * @Param:@param mobile 手机号
	 * @Param:@param password 密码
	 * @Param:@return
	 * @Author: zhls  联系方式：zhls1992@qq.com
	 * @Since:2016年1月13日 上午11:42:53
	 * @Version:1.0
	 */
	@RequestMapping("/userLogin.do")
	@ResponseBody
	public JsonResult userLogin(@RequestBean String telephone, @RequestBean String password,HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		if(StringUtils.isStrNull(telephone) || StringUtils.isStrNull(password)){
			jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
			jsonResult.setMessage("手机号或密码不能为空");
			return jsonResult;
		}
		ServiceResult<User> result=userService.loginUser(telephone, password);
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		if(result.isOk()){
			String appKey = UuidUtil.get32UUID().toUpperCase();
			String appSecret = UuidUtil.get32UUID().toUpperCase();
			jsonResult.setSuccess(true);
			jsonResult.setData(result.getDataMap());
			jsonResult.addData("user", result.getData());
			jsonResult.addData("appKey", appKey);
			jsonResult.addData("appSecret", appSecret);
			HashOperations<String, String, User> hashOps = redisTemplate.opsForHash();
			hashOps.put(appKey, appSecret, result.getData());
			redisTemplate.expire(appKey, 30, TimeUnit.MINUTES);
			//放入缓存
			HttpSession session = request.getSession();
			session.setAttribute(CommonConstants.LOGIN_USER_KEY, result.getData());
		}
		return jsonResult;
	}
	/**
	  * @Title: getUserByKey
	  * @Description: 获取用户登陆信息
	  * @param appKey
	  * @param appSecret
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月13日 上午11:45:35
	 */
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
	/**
	  * @Title: addUser
	  * @Description: 注册用户
	  * @param user
	  * @param codeStr
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月13日 下午2:07:43
	 */
	@RequestMapping("/addUser.do")
	@ResponseBody
	public JsonResult addUser(@RequestBean User user,@RequestBean String codeStr,HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		if(StringUtils.isStrNull(user.getTelephone()) || StringUtils.isStrNull(user.getPassword()) || 
				StringUtils.isStrNull(codeStr)){
			jsonResult.setStateCode(Const.ERROR_NODATA_CODE);
			jsonResult.setMessage("参数为空");
			return jsonResult;
		}
		//验证码检验
		ServiceResult<User> result=userService.addUser(user);
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		jsonResult.setSuccess(result.isOk());
		return jsonResult;
	}
	/**
	  * @Title: getMobileCode
	  * @Description: 获取验证码
	  * @param telephone
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月13日 下午2:08:32
	 */
	@RequestMapping("/getMobileCode.do")
	@ResponseBody
	public JsonResult getMobileCode(@RequestBean String telephone, HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
		if(telephone == null){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("手机号不能为空");
			return jsonResult;
		}
		HttpSession session = request.getSession();
		String tel = (String)session.getAttribute(Const.O2O_SESSION_FINDPWDTELEPHONE_CODE);
		if(!telephone.equals(tel)){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("手机号不存在");
			return jsonResult;
		}
		String code = RandomStringUtils.random(6, "1234567890");;
		@SuppressWarnings("unchecked")
		ValueOperations<String, String> valueOps = (ValueOperations<String, String>) redisTemplate.opsForValue();
		//缓存验证码
		valueOps.set("code"+telephone, code);
		//缓存状态
		valueOps.set("state"+telephone, "0");
		//时间
		redisTemplate.expire("code"+telephone, 30, TimeUnit.MINUTES);
		redisTemplate.expire("state"+telephone, 30, TimeUnit.MINUTES);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setSuccess(true);
		jsonResult.addData("code", code);
		jsonResult.setMessage("发送成功");
		return jsonResult;
	}
	/**
	  * @Title: checkUserCode
	  * @Description: 验证验证码
	  * @param telephone
	  * @param codeStr
	  * @param request
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月13日 下午2:10:30
	 */
	@RequestMapping("/checkUserCode.do")
	@ResponseBody
	private JsonResult checkUserCode(@RequestBean String telephone, @RequestBean String codeStr, HttpServletRequest request){
		JsonResult result = new JsonResult();
		//判断是否为空
		if(telephone == null || codeStr == null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数不能为空");
			return result;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(telephone);
		if(!m.matches()){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("手机号格式不正确");
			return result;
		}
		HttpSession session = request.getSession();
		String sessionCode = (String)session.getAttribute(Const.O2O_SESSION_FINDPWD_CODE);
		if(!codeStr.equals(sessionCode)){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("验证码不正确");
			return result;
		}
		//判断用户是否存在
		User user = userService.selectUserByMobile(telephone);
		if(user == null || user.getStatus() != 1){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("该用户不存在或未启用");
			return result;
		}
		session.setAttribute(Const.O2O_SESSION_FINDPWDTELEPHONE_CODE, telephone);
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("验证成功");
		return result;
	}
	/**
	  * @Title: backPassWord
	  * @Description: 找回密码
	  * @param telephone
	  * @param newPassWord
	  * @return  
	  * @return 返回类型  JsonResult   
	  * @throws
	  * @Author: zhls  联系方式：zhls1992@qq.com
	  * @Since: 2016年1月13日 下午2:10:59
	 */
	@RequestMapping("/fixedPassWord.do")
	@ResponseBody
	public JsonResult backPassWord(@RequestBean String telephone, @RequestBean String newPassWord){
		JsonResult jsonResult=new JsonResult();
		//判断参数
		if(telephone == null || newPassWord == null){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数不能为空");
			return jsonResult;
		}
		@SuppressWarnings("unchecked")
		ValueOperations<String, String> valueOps = (ValueOperations<String, String>) redisTemplate.opsForValue();
		String state = valueOps.get("state"+telephone);
		if(state != null && state.equals("1")){
			ServiceResult<User> result=userService.getBackPassWord(telephone, newPassWord);
			redisTemplate.delete("state"+telephone);
			jsonResult.setStateCode(result.getMsgCode());
			jsonResult.setMessage(result.getComment());
			jsonResult.setSuccess(true);
			return jsonResult;
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_CODE);
			jsonResult.setMessage("修改失败");
			return jsonResult;
		}
		
	}
}
