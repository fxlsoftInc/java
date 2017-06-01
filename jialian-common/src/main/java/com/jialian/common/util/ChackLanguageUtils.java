package com.jialian.common.util;

/**
* @ClassName: ChackLanguageUtils 
* @Description: 格式话语言
* @author 刘德宜 wudihaike@vip.qq.com
* @date 2015年9月18日 下午6:04:21
 */
public class ChackLanguageUtils {
	
	public static final String ZH = "zh";
	public static final String ZH_TW = "zh_TW";
	public static final String JP = "jp";
	public static final String EN = "en";
	public static final String KR = "kr";
	public static final String THA = "tha";
	public static final String SIGN = "sign";
	
	public static String language(String langugage){
		switch(langugage){
		   case JP: return "日语";
		   case ZH: return "中文";
		   case EN: return "英文";
		   case KR: return "韩语";
		   case THA: return "泰语";
		   case SIGN: return "手语";
		}
		return langugage;
	}
	
	public static String language2(String langugage){
		switch(langugage){
		   case JP: return "日本語";
		   case ZH: return "中国語";
		   case EN: return "英語";
		   case KR: return "韓国語";
		   case THA: return "タイ語";
		   case SIGN: return "手話";
		}
		return langugage;
	}
}