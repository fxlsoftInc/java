package com.jialian.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jialian.common.constants.CommonConstants;


/**
 * 
 *@Description: 登陆/主页控制器
 *@Author: 刘德宜  wudihaike@vip.qq.com
 *@Since:2015年12月2日 下午4:28:52
 *@Version:1.0
 */
@Controller
public class MainController extends BaseController{

	
	//如果用户已登录，直接跳转到主页面
/*	@RequestMapping(value = "/login.htm", method = RequestMethod.GET, produces = { "text/html;charset=UTF-8" })
	public ModelAndView loginPage(HttpServletRequest request, ModelAndView mav) {
		// 如果用户已登录，直接跳转到主页面
		if (getLoginUserName(request) != null){
			mav.setViewName("redirect:index.htm");
		} else {
			// 转到登录页面
			mav.setViewName("login");
		}
		return mav;
	}*/
	
	
	//系统用户登陆
	/*@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView login(ModelAndView mav,HttpServletRequest request,
			@RequestParam(required = true) String account, @RequestParam(required = true) String pwd) {
		SystemUser sysUser = new SystemUser();
		sysUser.setAccount(account);
		sysUser.setPassword(StringUtils.MD5(pwd));
		// 判断非空
		if (!StringUtils.isStrNull(account)) {
			ServiceResult<SystemUser> serviceResult = systemUserService.checkLogin(sysUser);
			// 判断密码是否正确
			if (!serviceResult.isOk()) {
				mav.addObject("account", account);
				mav.addObject("loginstatus", serviceResult.getComment());
				mav.setViewName("login");
				return mav;
			}
			HttpSession session = request.getSession();
			session.setAttribute(CommonConstants.LOGIN_USER_KEY, serviceResult.getData());
			mav.setViewName("redirect:index.htm");
		} else {
			mav.addObject("account", account);
			mav.addObject("loginstatus", "非法输入!");
			mav.setViewName("login");
			return mav;
		}
		return mav;
	}
	*/
	
	//主页
	@RequestMapping("index.htm")
	public ModelAndView indexShow(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	
	//菜单
	@RequestMapping("left.htm")
	public ModelAndView leftShow(ModelAndView mav) {
		mav.setViewName("left");
		return mav;
	}
	
	
	//退出
	@RequestMapping("/logout.htm")
	public ModelAndView logout(HttpServletRequest request,ModelAndView mav) {
		HttpSession session = request.getSession();
		session.removeAttribute(CommonConstants.LOGIN_USER_KEY);
		mav.setViewName("login");
		return mav;
	}
	
}