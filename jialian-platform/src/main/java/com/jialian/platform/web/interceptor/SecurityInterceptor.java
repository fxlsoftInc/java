package com.jialian.platform.web.interceptor;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.jialian.api.domain.basic.Const;


/**
 * ClassName: SecurityInterceptor
 * @Description: 判断用户权限，未登录用户跳转到登录页面
 * @author 刘德宜
 * @date 2015年5月9日上午9:48:20
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	private List<String> excludedUrls;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		//请求参数
		//String reqParaString = request.getQueryString();
		//请求地址
		String requestUri = request.getRequestURI();
		//参数设定
		if(request.getContentType()!= null && request.getContentType().contains("application/json")){
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			
			char[] buf = new char[1024];
			int rd;
			while ((rd = reader.read(buf)) != -1) {
				sb.append(buf, 0, rd);
			}
			
			JSONObject json = JSON.parseObject(sb.toString(), Feature.IgnoreNotMatch, Feature.SortFeidFastMatch);
			request.setAttribute("data", json);
		}
		if(excludedUrls != null){
			requestUri = request.getRequestURI();
		    for (String url : excludedUrls) {
		      if (requestUri.endsWith(url)) {
		        return true;
		      }
		    }
		}
		if(session.getAttribute(Const.ADMIN_SESSION_USER)==null) {
			JSONObject result = new JSONObject();
			result.put("success", false);
			result.put("stateCode", Const.Invalid_Signature_CODE);
			PrintWriter out = response.getWriter();
			out.write(result.toJSONString());
			out.flush();
			out.close();
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
}