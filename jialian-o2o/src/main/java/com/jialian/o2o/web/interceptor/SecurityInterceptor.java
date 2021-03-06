package com.jialian.o2o.web.interceptor;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;


/**
 * ClassName: SecurityInterceptor
 * @Description: 判断用户权限，未登录用户跳转到登录页面
 * @author 刘德宜
 * @date 2015年5月9日上午9:48:20
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	@SuppressWarnings("unused")
	private List<String> excludedUrls;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		//请求参数
//		String reqParaString = request.getQueryString();
//		//请求地址
//		String requestUri = request.getRequestURI();
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
		return true;
//		if(excludedUrls != null){
//			requestUri = request.getRequestURI();
//		    for (String url : excludedUrls) {
//		      if (requestUri.endsWith(url)) {
//		        return true;
//		      }
//		    }
//		}
//		if(session.getAttribute(CommonConstants.LOGIN_USER_KEY)==null) {
//			//当前url
//		    StringBuffer beforeUrl = request.getRequestURL();
//		    //参数
//            if(!StringUtils.isStrNull(reqParaString)){
//            	beforeUrl.append("?").append(reqParaString);
//            }
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//    		response.setHeader("P3P","CP='CP=CAO PSA OUR'");
//    		PrintWriter out = null;
//    		try {
//    			out = response.getWriter();
//	    		JsonResult json = new JsonResult();
//	    		json.setMessage("0001");
//	    		json.setComment("请登录");
//	    		out.append(JSON.toJSONString(json));
//    		} catch (IOException e) {
//    				e.printStackTrace();
//    		} finally {
//    			if (out != null)out.close();
//    		}
//			return false;
//		}
//		return super.preHandle(request, response, handler);
	}
	
	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
}