package com.jialian.common.constants;

/**
 * 一般常量
 *
 */
public interface CommonConstants {
	
	//当前是否是debug环境
	public static final String DEBUG = "DEBUG";
	
	public static final String LOGIN_USER_KEY = "login_user";
	
	/** 尾数处理：舍厘见分进角 */
	public static final String MANTISSA_PROCESS_1 = "1";
	/** 尾数处理：四舍五入到分 */
	public static final String MANTISSA_PROCESS_2 = "2";
	/** 尾数处理：四舍五入到角 */
	public static final String MANTISSA_PROCESS_3 = "3";
	/** 尾数处理：四舍五入到圆 */
	public static final String MANTISSA_PROCESS_4 = "4";
	/** 尾数处理：小数位数 */
	public static final String AMOUNT_DECIMAL = "2";
}