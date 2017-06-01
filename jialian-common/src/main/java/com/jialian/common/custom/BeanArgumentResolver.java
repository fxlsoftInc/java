package com.jialian.common.custom;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;



//import com.o2o.comm.util.XSSFilterUtil;

public class BeanArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		// content-type不是json的不处理
		if (request.getContentType() == null || !request.getContentType().contains("application/json")) {
			return null;
		}
		// 判断是否有数据
		JSONObject json = (JSONObject) request.getAttribute("data");
		if(json == null){
			return null;
		}
		String paramName = parameter.getParameterName();
		Class<?> paramType = parameter.getParameterType();
		// 利用fastjson转换为对应的类型
		System.out.println(json.getString(paramName));
		if (JSONObjectWrapper.class.isAssignableFrom(parameter.getParameterType())) {
			return new JSONObjectWrapper(json);
		} else {
			Type type = parameter.getGenericParameterType();
			if(String.class.isAssignableFrom(paramType)){
				return json.getString(paramName);
			}
			if(type instanceof ParameterizedType && ArrayList.class.isAssignableFrom(paramType)) {
				return JSON.parseArray(json.getString(paramName), (Class<?>)((ParameterizedType)type).getActualTypeArguments()[0]);
			}
			return JSON.parseObject(json.getString(paramName), paramType, Feature.IgnoreNotMatch, Feature.SortFeidFastMatch, Feature.AllowArbitraryCommas);
		}
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(RequestBean.class)) {
		    return true;
		}
		return false;
	}

}
