package com.jialian.o2o.web;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.jialian.common.util.SpringUtils;


/**
 * 
 *@Description:系统启动加载类。所有需要初始化的数据请放在这里
 *@Author: 刘德宜  wudihaike@vip.qq.com
 *@Since:2015年12月2日 下午4:29:27
 *@Version:1.0
 */
public class SystemContextListener extends ContextLoaderListener {

	@Override
	public void contextInitialized ( ServletContextEvent sce )
	{
		super.contextInitialized(sce);
		SpringUtils.setApplicationContext((WebApplicationContext) sce.getServletContext().
				getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
