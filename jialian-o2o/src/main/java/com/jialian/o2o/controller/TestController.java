package com.jialian.o2o.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.SystemUser;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.service.TestServiceApi;

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
	
	@RequestMapping("/test.htm")
	@ResponseBody
	public JsonResult selectSystemUser(SystemUser systemUser,
			@RequestParam(value = "page", required = false, defaultValue = "0") int currentPage,
			@RequestParam(value = "rows", required = false, defaultValue = "10") int onePageCount){
		//只搜索当前代理商的企业客户
		JsonResult json = new JsonResult();
		ServiceResult<Page<SystemUser>> serviceResult = testService.selectSystemUser(systemUser, currentPage, onePageCount);
		json.setSuccess(true);
		json.addData("systemUser",serviceResult.getData());
		return json; 
	}
}