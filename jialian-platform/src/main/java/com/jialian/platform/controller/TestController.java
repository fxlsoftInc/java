package com.jialian.platform.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jialian.api.component.IdWorkerServiceApi;
import com.jialian.api.domain.SystemUser;
import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.ResourceInfo;
import com.jialian.api.service.TestServiceApi;
import com.jialian.common.custom.RequestBean;

/**
 *@Description:测试控制器
 *@Author: 刘德宜  wudihaike@vip.qq.com
 *@Since:2015年12月2日 下午6:24:42
 *@Version:1.0
 */
@Controller
public class TestController {
	
	@Resource
	private TestServiceApi testService;
	
	@Resource
	private IdWorkerServiceApi idWoker;
	
	@RequestMapping("test.htm")
	public ModelAndView selectSystemUser(ModelAndView mav,
			@RequestBean("systemUserQuery") SystemUser systemUser,
			@RequestParam(value = "page", required = false, defaultValue = "0") int currentPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") int onePageCount){
		//只搜索当前代理商的企业客户
		ServiceResult<Page<SystemUser>> serviceResult = testService.selectSystemUser(systemUser, currentPage, onePageCount);
		mav.addObject("serviceResult",serviceResult);
		mav.addObject("systemUserQuery",systemUser);
		mav.setViewName("test");
		return mav; 
	}
	

	@RequestMapping("getWorkerId.do")
	@ResponseBody
	public JsonResult getWorkerId(){
		JsonResult result = new JsonResult();
		result.setStateCode(Const.SUCCESS_CODE);
		result.setSuccess(true);
		result.addData("workerId", idWoker.nextId());
		return result;
	}
	
	@RequestMapping("/updateResourceInfo.do")
	@ResponseBody
	public JsonResult updateResourceInfo(@RequestBean ArrayList<ResourceInfo> resourceInfoList){
		JsonResult result = new JsonResult();
		result.setDataObj(resourceInfoList);
		return result;
	}
}