package com.jialian.platform.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.common.custom.MyStringTrimmerEditor;
import com.jialian.common.util.DateTimeUtils;
import com.jialian.common.util.StringUtils;
import com.jialian.common.util.UuidUtil;



/**
 *  Crontroller类的基础类，可以 除掉参数中的空格
 * @author 刘德宜
 */
public class BaseController{
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.dateTimeString);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(String.class, new MyStringTrimmerEditor(false));
	}
	
	/**
	 * 校验绑定对象
	 */
	protected BindException validateRequestBean(Validator validator, Object entity)throws Exception {
		Class<? extends Object> target = entity.getClass();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(entity,StringUtils.firstCharToLow(target.getSimpleName()));
		BindException errors = new BindException(binder.getBindingResult());
		validator.validate(entity, errors);
		return errors;
	}

	/**
	 * 初始化输出
	 * @param fileName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected PrintWriter initPrintWriter(String fileName,HttpServletResponse response) throws Exception{
		   response.setContentType("application/octet-stream;charset=GBK");
		   response.setHeader("Content-Disposition","attachment;  filename="+fileName);
		   PrintWriter out = response.getWriter();//放在第一句是会出现乱码 
		   return out;
	}
	
	/**
	 * 输出数据
	 * @param writer
	 * @param data
	 * @param isEnd
	 * @throws Exception
	 */
	protected void writeData(PrintWriter writer ,String data , boolean isEnd) throws Exception{
		writer.write(data);
		writer.flush();
		if(isEnd){
			writer.close();
		}
	}
	
	/**
	 * 根据request取得IP
	 * @param request
	 * @return
	 */
	public String getIpAddr() {
		HttpServletRequest request = getRequest();
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
        
        String[] ips = ip.split(",");
        
        if(ips.length>0){
        	ip=ips[0];
        }
        return ip;
    }
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	/**
	 * 将url中文参数转换为utf-8格式
	 * @param para
	 * @return string
	 */
	public String getParaToString(String para) {
		// 将url参数转换为utf-8格式
		String result = "";
		if (para == null || "".equals(para)) {
			return result;
		}
		byte[] temp;
		try {
			temp = para.getBytes("iso-8859-1");
			result = new String(temp,"utf-8");
		} catch (Exception e) {
		}
		return  result;
	}
	/**
	 * 
	 * @Description:在session里获取user的信息
	 * @Param:@param request
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月28日下午3:35:16
	 * @Version:1.0
	 */
	public AdminUser getAdminUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		AdminUser user = (AdminUser)session.getAttribute(Const.ADMIN_SESSION_USER);
		return user;
	}
	
	/**
	 * @Description:获取管理员的权限信息
	 * @Param:@param request
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月28日下午3:37:21
	 * @Version:1.0
	 */
	public List<AdminPermission> getAdminPermission(HttpServletRequest request){
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<AdminPermission> permissionList = (List<AdminPermission>)session.getAttribute(Const.ADMIN_SESSION_menuList);
		return permissionList;
	}
}