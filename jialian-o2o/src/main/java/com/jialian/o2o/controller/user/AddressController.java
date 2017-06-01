package com.jialian.o2o.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.common.util.AddressUtils;
import com.jialian.common.util.StringUtils;

/**
 *@Description: 地址信息控制
 *@Author: 卢光磊  lgl1012cto@foxmail.com
 *@Since:2015年12月17日 下午3:48:16
 *@Version:1.0
 */
@Controller
@RequestMapping("/address")
public class AddressController {
	
	@RequestMapping("/getCityAddress.do")
	@ResponseBody
	public JsonResult getCityAddress(HttpServletRequest request){
		JsonResult jsonResult=new JsonResult();
	    try {
	    	//获取IP
			String ip = request.getHeader("x-forwarded-for");  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getHeader("WL-Proxy-Client-IP");  
		    }  
		    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		        ip = request.getRemoteAddr();  
		    } 
		    //根据IP获取地址（城市）
			Map<String, Object> data=new HashMap<String, Object>();
			String address=AddressUtils.getAddresses("ip="+ip, "utf-8");
			if(StringUtils.isStrNull(address)){
				jsonResult.setStateCode(Const.ERROR_CHECK_NULL);
				jsonResult.setMessage("无效地址");
				return jsonResult;
			}
			data.put("address", address);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setData(data);
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResult;
	}
}
