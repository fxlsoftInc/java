package com.jialian.common.util;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18NUtils {
	
	public static String message(String o,String locale){
        String message = "";
        if(locale.equals("jp")){
        	Locale jp = Locale.JAPAN;
        	ResourceBundle myResourcesJP = ResourceBundle.getBundle("msg.message",jp);
        	message = myResourcesJP.getString(o);
        }else if(locale.equals("cn")){
        	Locale cn = Locale.CHINA;
        	ResourceBundle myResourcesCN = ResourceBundle.getBundle("msg.message",cn);
        	message= myResourcesCN.getString(o);
        }
		return message;
	}
}