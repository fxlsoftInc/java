package com.jialian.api.domain.basic;

/**
 * @Description: 网站全部全局变量
 * @Param:
 * @author:FxLsoft fxlsoft@163.com
 * @Since:2015年12月10日上午10:38:21
 * @Version:1.0
 */
public class Const {
	/*************************************后台全局变量*********************************************/
	public static final String ADMIN_SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String ADMIN_SESSION_USER = "sessionUser";
	public static final String ADMIN_SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String ADMIN_SESSION_menuList = "menuList";			//当前菜单
	public static final String ADMIN_SESSION_allmenuList = "allmenuList";		//全部菜单
	
	
	/*************************************接口返回代码********************************************/
	
	public static final String O2O_SESSION_FINDPWD_CODE = "secCode";
	public static final String O2O_SESSION_FINDPWDTELEPHONE_CODE = "sessionFindPwdTelephone";
	/****************************************pingpp 管理平台****************************************************/
	/**
	 * pingpp 管理平台对应的 API key
	 */
	public static final String apiKey = "sk_test_0CmDSKnTyTa5mDeXLSW1OaX1";
	/**
	 * pingpp 管理平台对应的应用 ID
	 */
	public static final String appId = "app_aj18OCOqbv140Gaj";
	/**
	 * 支付宝pc支付参数 success_url[string] 为支付成功的回调地址，required；
	 */
	public static final String success_url = "http://52.68.42.215:8619/jialian-shop/pay/paySuccess.htm";
	
	public static final String O2O_success_url = "http://52.68.42.215:8619/jialian-o2o/order/paySuccess.htm";
	
	/*public static final String success_url = "http://182.254.152.39:7999/jialian-shop/pay/paySuccess.htm";
	
	public static final String O2O_success_url = "http://182.254.152.39:7999/jialian-o2o/order/paySuccess.htm";*/
	
	/****************************************pingpp 管理平台****************************************************/
	
	/**
	 * 成功状态码
	 */
	public static final String SUCCESS_CODE ="1000";
	
	/**
	 * 错误状态码
	 */
	public static final String ERROR_CODE ="1001";
	
	/**
	 * 参数错误状态码
	 */
	public static final String ERROR_PARAM_CODE ="1002";
	
	/**
	 * 无数据错误状态码
	 */
	public static final String ERROR_NODATA_CODE ="1003";
	
	/**
	 * 未知错误
	 */
	public static final String ERROR_UNKNOWN_CODE = "1004";
	
	/**
	 * 登录验证失败
	 */
	public static final String ERROR_LOGIN_CODE = "1005";
	
	/**
	 * 用户被删除
	 */
	public static final String ERROR_USER_DELETED_CODE = "1006";
	
	/**
	 * 用户被禁用
	 */
	public static final String ERROR_USER_FORBIDDEN_CODE = "1007";
	
	/**
	 * 用户状态错误
	 */
	public static final String ERROR_USER_STATUS_CODE = "1008";
	
	/**
	 * 该记录已删除或不存在
	 */
	public static final String ERROR_RECORD_DELETED = "1009";
	
	/**
	 * 用户已经存在
	 */
	public static final String ERROR_USER_HAVE="1010";
	
	/**
	 * 用户不存在
	 */
	public static final String ERROR_USER_NULL ="1011";
	
	/**
	 * 账户名或密码错误
	 */
	public static final String ERROR_TEL_PWD ="1012";
	
	/**
	 *o2o验证码错误
	 */
	public static final String ERROR_CODE_O2O ="1013";
	
	/**
	 *查询不到数据
	 */
	public static final String ERROR_CHECK_NULL ="1014";
	
	/**
	 *无效地址
	 */
	public static final String ERROR_ADDRESS ="1015";
	
	/**
	 * 无权限访问
	 */
	public static final String ERROR_PERMISSION_CODE ="1016";
	
	/**
	 * 文件为空
	 */
	public static final String ERROR_FILE_IS_NULL = "10010";
	
	/**
	 * 密码错误
	 */
	public static final String ERROR_PASSWORD = "1016";
	/**
	 * 库存不足
	 */
	public static final String UNDERSTOCK_CODE = "1020";
	
	/**
	 * 无效签名
	 */
	public static final String  Invalid_Signature_CODE = "9999";
}
