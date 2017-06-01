package com.jialian.platform.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jialian.api.domain.basic.Const;
import com.jialian.api.domain.basic.JsonResult;
import com.jialian.api.domain.basic.Page;
import com.jialian.api.domain.basic.ServiceResult;
import com.jialian.api.domain.entity.AdminPermission;
import com.jialian.api.domain.entity.AdminRole;
import com.jialian.api.domain.entity.AdminUser;
import com.jialian.api.domain.entity.OperateLog;
import com.jialian.api.domain.entity.Where;
import com.jialian.api.domain.entity.Where.Criteria;
import com.jialian.api.domain.para.LogPara;
import com.jialian.api.domain.query.AdminRoleQuery;
import com.jialian.api.domain.query.AdminUserQuery;
import com.jialian.api.domain.vo.AdminUserVo;
import com.jialian.api.service.Admin.AdminUserServiceApi;
import com.jialian.api.service.Log.LogServiceApi;
import com.jialian.common.custom.RequestBean;
import com.jialian.common.util.MD5;
import com.jialian.common.util.StringUtils;
import com.jialian.common.util.sms.SMS;
import com.jialian.common.util.sms.SMSResult;
import com.jialian.platform.controller.BaseController;

/**
 * 
 * @Description:后台管理员相关操作控制器
 * @Param:
 * @author:FxLsoft fxlsoft@163.com
 * @Since:2015年12月9日下午6:45:08
 * @Version:1.0
 */
@Controller
@RequestMapping("/adminUser")
public class AdminUserController extends BaseController{
	
	@Resource
	private AdminUserServiceApi adminUserService;
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource
	private LogServiceApi logService;
	/**
	 * @Description:后台管理员登录
	 * @Param:@param userName 用户名
	 * @Param:@param password 用户密码
	 * @Param:@return JsonResult
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2015年12月9日下午6:56:04
	 * @Version:1.0
	 */
	@RequestMapping("/adminLogin.do")
	@ResponseBody
	public JsonResult userLogin(HttpServletRequest request, String userName, String password, String secCode){
		JsonResult result = new JsonResult();
		//对参数进行校验
		if(secCode == null){
			result.setSuccess(false);
			result.setMessage("验证码不能为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
		if(userName == null){
			result.setSuccess(false);
			result.setMessage("用户名不能为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
		if(password == null){
			result.setSuccess(false);
			result.setMessage("用户密码不能为空");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
		HttpSession session = request.getSession();
		//检查验证码是否正确
		if(!secCode.equals(session.getAttribute(Const.ADMIN_SESSION_SECURITY_CODE))){
			result.setSuccess(false);
			result.setMessage("验证码错误");
			result.setStateCode(Const.ERROR_PARAM_CODE);
			return result;
		}
		//装配条件
		Where where = new Where();
		Criteria criteria = where.createCriteria();
		criteria.andEqualTo("username", userName.trim()).andEqualTo("password", MD5.md5(password));
		//请求结果
		List<AdminUser> adminUserList = adminUserService.selectByWhere(where);
		if(adminUserList == null || adminUserList.size() == 0){
			result.setSuccess(false);
			result.setMessage("用户名或密码错误");
			result.setStateCode(Const.ERROR_LOGIN_CODE);
			return result;
		}else{
			AdminUser adminUser = adminUserList.get(0);
			//判断用户状态
			if(adminUser.getAdminStatus() == 0){//删除状态
				result.setSuccess(false);
				result.setMessage("该用户已被删除");
				result.setStateCode(Const.ERROR_USER_DELETED_CODE);
				return result;
			}
			if(adminUser.getAdminStatus() == 1){//启用状态
				List<Long> menuList = new ArrayList<Long>();
				Set<Long> parentList = new HashSet<Long>();
				//获取相应的权限  之前是userId 改为 roleId
				List<AdminPermission> permissionList = adminUserService.selectPermissionByUserId(adminUser.getRoleId());
				if(permissionList == null || permissionList.size() == 0){
					result.setSuccess(false);
					result.setMessage("该用户没有权限");
					result.setStateCode(Const.ERROR_PERMISSION_CODE);
					return result;
				}else{
					//设置session
					session.setAttribute(Const.ADMIN_SESSION_USER, adminUser);
					session.setAttribute(Const.ADMIN_SESSION_menuList, permissionList);
					//控制数据的输出
					for(AdminPermission p:permissionList){
						menuList.add(p.getId());
						parentList.add(p.getParentId());
					}
					adminUser.setLogdate(new Date());
					adminUser.setLognum(adminUser.getLognum() + 1);
					adminUserService.updateByPrimaryKeySelective(adminUser);
					adminUser.setPassword(null);
					result.setSuccess(true);
					result.setMessage("登录成功！");
					result.setStateCode(Const.SUCCESS_CODE);
					result.addData("adminUser", adminUser);
					result.addData("menuList", menuList);
					result.addData("parentList", parentList);
					logService.logByAdmin(adminUser.getUsername(), adminUser.getId(), getIpAddr(), "登陆", adminUser.getUsername() + "登陆了系统");
					return result;
				}
			}
			if(adminUser.getAdminStatus() == 2){//禁用状态
				result.setSuccess(false);
				result.setMessage("该用户被禁用");
				result.setStateCode(Const.ERROR_USER_FORBIDDEN_CODE);
				return result;
			}
			result.setSuccess(false);
			result.setMessage("该用户状态错误，请联系管理员");
			result.setStateCode(Const.ERROR_USER_STATUS_CODE);
			return result;
		}
	}
	
	
	/**
	 * 管理员列表
	 *@Description:
	 *@Param: startIndex 起始查询页码
	 *@Param: pageSize 需要查询的页数
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 下午2:50:54
	 *@Version:1.0
	 */
	@RequestMapping("/selectAdminUserList.do")
	@ResponseBody
	public JsonResult selectAdminUserList(HttpServletRequest request,
			@RequestParam(value = "page",required = false, defaultValue = "0") int startIndex,
			@RequestParam(value = "row", required = false, defaultValue = "10") int pageSize){
		JsonResult jsonResult = new JsonResult();
		Where where = new Where();
		where.setLimit(startIndex);
		where.setOffset(pageSize);
		Criteria criteria = where.createCriteria();
		criteria.andNotEqualTo("admin_status", (short)0);
		List<AdminUser> adminUserList = adminUserService.selectByWhere(where);
		int totalItems = adminUserService.countByWhere(where);
		jsonResult.setSuccess(true);
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.setMessage("查询成功");
		jsonResult.addData("adminUserList", adminUserList);
		jsonResult.addData("totalItems", totalItems);
		return jsonResult;
	}
	
	/**
	* @Description: 用户列表
	* @param @param query
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月13日 下午2:29:02
	 */
	@RequestMapping("/getAdminUserList.do")
	@ResponseBody
	public JsonResult getAdminUserList(@RequestBean AdminUserQuery query){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<Page<AdminUserVo>> result=adminUserService.getAdminUserListByQuery(query);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setDataObj(result.getData());
		return jsonResult;
	}
	
	
	/**
	 *  根据id，查看管理员详细信息
	 *@Description:
	 *@param:id  管理员id
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 下午3:43:07
	 *@Version:1.0
	 */
	@RequestMapping("/selectAdminUserById.do")
	@ResponseBody
	public JsonResult selectAdminUserById(Long id, HttpServletRequest request ){
		JsonResult jsonResult = new JsonResult();
		if(id == null || id <= 0){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			AdminUser adminUser = adminUserService.selectByPrimaryKey(id);
			if(adminUser == null || adminUser.equals("")){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_RECORD_DELETED);
				jsonResult.setMessage("该管理员已被删除或不存在");
			}else{
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("查询成功");
				jsonResult.addData("adminUser", adminUser);
			}
		}
		return jsonResult;
	}
	
	
	/**
	* @Description: 获取管理员信息
	* @param @param id
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月13日 下午5:35:28
	 */
	@RequestMapping("/getAdminUser.do")
	@ResponseBody
	public JsonResult getAdminUser(@RequestBean Long id){
		JsonResult jsonResult = new JsonResult();
		ServiceResult<AdminUserVo> result=adminUserService.getAdminUserById(id);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setComment(result.getComment());
		jsonResult.addData("adminUser", result.getData());
		return jsonResult;
	}

	
	/**
	 * 添加新的管理员
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 下午4:46:10
	 *@Version:1.0
	 */
	@RequestMapping("/addAdminUser.do")
	@ResponseBody
	public JsonResult addAdminUser(@RequestBean AdminUser adminUser,AdminRole adminRole,HttpServletRequest request ){
		JsonResult jsonResult = new JsonResult();
		if(adminUser == null || adminUser.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			//roleType:1 超级管理员 2,客服人员
			int result = adminUserService.addAdminUser(adminUser,adminRole);
			if(result == 1){
				AdminUser user = getAdminUser(getRequest());
				logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "系统管理", "添加新的后台管理员");
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("添加成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("添加失败");
			}
		}
		return jsonResult;
	}
	
	/**
	* @Description: 添加后台用户(针对页面新增的接口)
	* @param @param adminUser
	* @param @param request
	* @param @return
	* @return JsonResult
	* @throws     
	* @author 卢光磊  lgl1012cto@foxmail.com 
	* @date: 2016年1月12日 下午6:40:34
	 */
	@RequestMapping("/addAdminUser_new.do")
	@ResponseBody
	public JsonResult addAdminUser_new(@RequestBean AdminUser adminUser,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(StringUtils.isStrNull(adminUser.getUsername()) 
				|| StringUtils.isStrNull(adminUser.getTelephone())
				|| StringUtils.isStrNull(adminUser.getPassword())){
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
			return jsonResult;
		}
		adminUser.setAdminStatus((short) 1);
		//MD5加密
		adminUser.setPassword(MD5.md5(adminUser.getPassword()));
		//添加
		ServiceResult<Object> result=adminUserService.addAdminUser_new(adminUser);
		jsonResult.setSuccess(result.isOk());
		jsonResult.setStateCode(result.getMsgCode());
		jsonResult.setMessage(result.getComment());
		return jsonResult;
	}
	
	/**
	 * 修改管理员信息
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 下午5:27:03
	 *@Version:1.0
	 */
	@RequestMapping("/editAdminUserById.do")
	@ResponseBody
	public JsonResult editAdminUserById(@RequestBean AdminUser adminUser,HttpServletRequest request ){
		JsonResult jsonResult = new JsonResult();
		if(adminUser == null || adminUser.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			if(!StringUtils.isStrNull(adminUser.getPassword())){
				adminUser.setPassword(MD5.md5(adminUser.getPassword()));
			}
			int result = adminUserService.updateByPrimaryKeySelective(adminUser);
			if(result == 1){
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("修改成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("修改失败");
			}
		}
		return jsonResult;
	}
	
	/**
	 * 删除管理员
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2015年12月21日 下午6:00:29
	 *@Version:1.0
	 */
	@RequestMapping("/deleteAdminUserById.do")
	@ResponseBody
	public JsonResult deleteAdminUserById(Long id,HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(id == null || id <= 0 ){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setMessage("参数错误");
		}else{
			int result = adminUserService.deleteAdminUserById(id);
			if(result == 1){
				AdminUser user = getAdminUser(getRequest());
				logService.logByAdmin(user.getUsername(), user.getId(), getIpAddr(), "系统管理", "删除管理员");
				jsonResult.setSuccess(true);
				jsonResult.setStateCode(Const.SUCCESS_CODE);
				jsonResult.setMessage("删除成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_UNKNOWN_CODE);
				jsonResult.setMessage("删除失败");
			}
		}
		return jsonResult;
	}
	
	
	/**
	 * 系统设置，账户列表
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月4日 下午3:02:28
	 *@Version:1.0
	 */
	@RequestMapping("/selectAdminRoleList.do")
	@ResponseBody
	public JsonResult selectAdminRoleList(AdminRoleQuery query){
		JsonResult jsonResult = new JsonResult();
		//测试数据，正式需删除
		query.setCurrentPage(0);
		query.setOnePageCount(8);
		//query.setRoleName("客");测试模糊查询数据
		ServiceResult<List<AdminUserVo>> result = adminUserService.selectAdminRoleList(query);
		jsonResult.setSuccess(true);
		jsonResult.setMessage("查询成功");
		jsonResult.setStateCode(Const.SUCCESS_CODE);
		jsonResult.addData("adminUserList", result.getData());
		return jsonResult;
	}
	
	/**
	 * 后台--忘记密码
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月4日 下午4:39:10
	 *@Version:1.0
	 */
	@RequestMapping("/forgetPsd.do")
	@ResponseBody
	public JsonResult forgetPsd(HttpServletRequest request, String userName, String telephone, String smsCode, String newPsd, String repeatPsd){
		JsonResult jsonResult = new JsonResult();
		if(userName == null || userName.equals("")){
			jsonResult.setMessage("账号不能为空");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setSuccess(false);
		}else if(telephone == null || telephone.equals("")){
			jsonResult.setMessage("电话号码不能为空");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setSuccess(false);
		}else if(smsCode == null || smsCode.equals("")){
			jsonResult.setMessage("验证码不能为空");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setSuccess(false);
		}else if(newPsd == null || newPsd.equals("")){
			jsonResult.setMessage("新密码不能为空");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setSuccess(false);
		}else if(repeatPsd == null || repeatPsd.equals("")){
			jsonResult.setMessage("确认密码不能为空");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setSuccess(false);
		}else if(!repeatPsd.equals(newPsd)){
			jsonResult.setMessage("新密码和确认密码不同");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
			jsonResult.setSuccess(false);
		}
		//验证验证码
		ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
		if(!smsCode.equals(valueOps.get("code"+telephone))){
			jsonResult.setSuccess(false);
			jsonResult.setStateCode(Const.ERROR_CODE);
			jsonResult.setMessage("验证码错误");
		}
		else{
			Where where = new Where();
			Criteria criteria = where.createCriteria();
			criteria.andEqualTo("username", userName);
			List<AdminUser> adminUserList = adminUserService.selectByWhere(where);
			if(adminUserList == null || adminUserList.size() <= 0){
				jsonResult.setSuccess(false);
				jsonResult.setMessage("用户不存在");
				jsonResult.setStateCode(Const.ERROR_CODE);
			}else{
				AdminUser adminUser = adminUserList.get(0);
				adminUser.setUsername(userName);
				adminUser.setTelephone(telephone);
				adminUser.setPassword(MD5.md5(newPsd));
				int result = adminUserService.updateByPrimaryKeySelective(adminUser);
				if(result == 1){
					jsonResult.setSuccess(true);
					jsonResult.setMessage("修改成功");
					jsonResult.setStateCode(Const.SUCCESS_CODE);
					adminUser.setPassword(null);//为了安全，置空密码
					jsonResult.addData("adminUser", adminUser);
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setMessage("修改失败");
					jsonResult.setStateCode(Const.ERROR_CODE);
				}
			}
		}
		return jsonResult;
	}
	
	/**
	 * 获取验证码
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月4日 下午6:42:47
	 *@Version:1.0
	 */
	@RequestMapping("/sendSmsCode.do")
	@ResponseBody
	public JsonResult sendSmsCode(String telephone, HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		if(telephone == null || telephone.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setMessage("电话号码不能为空");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
		}else{
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(telephone);
			if(!m.matches()){
				jsonResult.setSuccess(false);
				jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
				jsonResult.setMessage("手机号格式不正确");
				return jsonResult;
			}
			String code = "";
			for (int i = 0; i < 4; i++) {
				code +=  randomChar();
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
			ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
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
			System.out.println(" 验证码："+code);
		}
		return jsonResult;
	}
	
	//获取随机验证码
	private char randomChar(){
		Random r = new Random();
		String s = "0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
	
	
	/**
	 * 通过角色类型查询角色名称
	 *@Description:
	 *@Author: shx  408640463@qq.com
	 *@Since:2016年1月18日 上午11:23:17
	 *@Version:1.0
	 */
	@RequestMapping("/selectRoleNameByRoleType.do")
	@ResponseBody
	public JsonResult selectRoleNameByRoleType(@RequestBean String roleType){
		JsonResult jsonResult = new JsonResult();
		if(roleType == null || roleType.equals("")){
			jsonResult.setSuccess(false);
			jsonResult.setMessage("参数错误");
			jsonResult.setStateCode(Const.ERROR_PARAM_CODE);
		}else{
			List<AdminUserVo> adminRoleList = adminUserService.selectRoleNameByRoleType(roleType);
			jsonResult.setSuccess(true);
			jsonResult.setStateCode(Const.SUCCESS_CODE);
			jsonResult.setMessage("查询成功");
			jsonResult.addData("adminRoleList", adminRoleList);
		}
		return jsonResult;
	}
	
	/**
	 * @Description:查询操作日志
	 * @Param:@param startIndex
	 * @Param:@param pageSize
	 * @Param:@param startTime
	 * @Param:@param endTime
	 * @Param:@param userName
	 * @Param:@return
	 * @Author: FxLsoft fxlsoft@163.com
	 * @Since:2016年1月28日下午4:52:07
	 * @Version:1.0
	 */
	@RequestMapping("/selectOperateLogByWhere.do")
	@ResponseBody
	public JsonResult selectOperateLogByWhere(@RequestParam(value = "startIndex", required = false, defaultValue = "0") int startIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize, @RequestBean LogPara para){
		JsonResult result = new JsonResult();
		Where where = new Where();
		where.setOffset(pageSize);
		where.setLimit(startIndex);
		if(para != null){
			Criteria criteria = where.createCriteria();
			if(para.getStartTime() != null){
				criteria.andGreaterThanOrEqualTo("create_time", para.getStartTime());
			}
			if(para.getEndTime() != null){
				criteria.andLessThanOrEqualTo("create_time", para.getEndTime());
			}
			if(para.getUserName() != null){
				criteria.andLike("operate_name", "%" + para.getUserName() + "%");
			}
		}
		List<OperateLog> logList = logService.selectByWhere(where);
		int totalItems = logService.countByWhere(where);
		result.setSuccess(true);
		result.setStateCode(Const.SUCCESS_CODE);
		result.setMessage("查询成功！");
		result.addData("logList", logList);
		result.addData("totalItems", totalItems);
		return result;
	}
}
