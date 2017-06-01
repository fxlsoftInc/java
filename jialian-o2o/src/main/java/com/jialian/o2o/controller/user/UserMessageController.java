package com.jialian.o2o.controller.user;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.jialian.common.constants.CommonConstants;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.StringUtils;
import com.jialian.common.util.UuidUtil;
import com.jialian.common.util.sms.SMS;
import com.jialian.common.util.sms.SMSResult;


/**
 * 
 *@Description:o2o用户个人信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since: 2015年12月10日 上午11:02:04
 *@Version:1.0
 */
@Controller
@RequestMapping("/webUser")
public class UserMessageController {
	
	@Resource
	private UserServiceApi userService;
	
	@Resource
	private RedisTemplate<String, ?> redisTemplate;
	
	/**
	 * @Description: 添加用户
	 * @param: @param user 用户信息
	 * @param: @param codeStr 验证码
	 * @param: @param request
	 * @param: @return   
	 * @return: JsonResult   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
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
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(user.getTelephone());
		if(!("RegisterCode" + codeStr).equals(code)){
			jsonResult.setStateCode(Const.ERROR_CODE_O2O);
			jsonResult.setSuccess(false);
			jsonResult.setMessage("验证码错误");
			return jsonResult;
		}
		ServiceResult<User> result=userService.addUser(user);
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		jsonResult.setSuccess(result.isOk());
		return jsonResult;
	}
	
	/**
	 * @Description:注册时用的验证码
	 * @Param:@param request
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月15日下午6:07:47
	 * @Version:1.0
	 */
	@RequestMapping("/getMobileCodeByRegister.do")
	@ResponseBody
	public JsonResult getMobileCodeByRegister(@RequestBean String telephone, HttpServletRequest request){
		JsonResult result = new JsonResult();
		if(telephone == null){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("手机号不能为空");
			return result;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(telephone);
		if(!m.matches()){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("手机号格式不正确");
			return result;
		}
		String code = "";
		for(int i=0; i<6; i++){
			code += randomChar();
		}
		SMS sms = new SMS();
		try {
			SMSResult smsResult = sms.sendSMS("您的验证码是" + code, telephone);
			if(smsResult.success){
				HttpSession session = request.getSession();
				session.setAttribute(telephone, "RegisterCode" + code);
				result.setStateCode(Const.SUCCESS_CODE);
				result.setSuccess(true);
				result.setMessage("发送成功");
				return result;
			}else{
				result.setStateCode(Const.ERROR_CODE);
				result.setSuccess(false);
				result.setMessage("发送失败，请重新发送");
				return result;
			}
		} catch (IOException e) {
			result.setStateCode(Const.ERROR_CODE);
			result.setSuccess(false);
			result.setMessage("网络故障，请重新发送");
			return result;
		}
	}
	
	/**
	 * @Description: 获取验证码
	 * @param: @param telephone 手机号
	 * @param: @param request
	 * @param: @return   
	 * @return: JsonResult   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
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
		String code = "";
		for(int i=0; i<6; i++){
			code += randomChar();
		}
		SMS sms = new SMS();
		try {
			SMSResult smsResult = sms.sendSMS("您的验证码是" + code, telephone);
			if(!smsResult.success){
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
		jsonResult.setMessage("发送成功");
		return jsonResult;
	}
	
	/**
	 * @Description: 用户登录
	 * @param: @param telephone
	 * @param: @param password
	 * @param: @param request
	 * @param: @return   
	 * @return: JsonResult   
	 * @author 卢光磊  lgl1012cto@foxmail.com
	 * @throws 
	 * @date 2015年5月5日 下午5:17:48
	 */
	@RequestMapping("/loginUser.do")
	@ResponseBody
	public JsonResult loginUser(@RequestBean String telephone,@RequestBean String password,HttpServletRequest request){
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
	* @Description: 检验用户手机验证码
	* @param @param telephone 手机号
	* @param @param codeStr 验证码
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 上午11:11:59
	 */
	@RequestMapping("/checkUserMobileCode.do")
	@ResponseBody
	public JsonResult checkUserMobileCode(@RequestBean String telephone, @RequestBean String codeStr){
		JsonResult result = new JsonResult();
		if(telephone == null || codeStr == null || telephone.equals("") || codeStr.equals("")){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_PARAM_CODE);
			result.setMessage("参数不能为空");
			return result;
		}
		@SuppressWarnings("unchecked")
		ValueOperations<String, String> valueOps = (ValueOperations<String, String>) redisTemplate.opsForValue();
		if(!codeStr.equals(valueOps.get("code"+telephone))){
			result.setSuccess(false);
			result.setStateCode(Const.ERROR_CODE_O2O);
			result.setMessage("验证码错误");
			return result;
		}
		valueOps.set("state"+telephone, "1");
		redisTemplate.expire("state"+telephone, 30, TimeUnit.MINUTES);
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("验证成功");
		return result;
	}
	
	/**
	* @Description: 手机验证码检验(只供内部方法调用,根据需要可封装为公共方法)
	* @param @param telephone 手机号
	* @param @param codeStr 验证码
	* @param @return
	* @return boolean
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 上午10:59:50
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
	* @Description: 找回密码
	* @param @param telephone 手机号
	* @param @param newPassWord 新密码
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2015年12月15日 上午11:56:58
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
	
	private char randomChar(){
		Random r = new Random();
		String s = "0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
}
