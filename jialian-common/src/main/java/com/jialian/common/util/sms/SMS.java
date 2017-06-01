package com.jialian.common.util.sms;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class SMS implements Serializable{
	
	private static final long serialVersionUID = 3462876184683672638L;

	//http://web.wasun.cn/asmx/smsservice.aspx?name=15121186235&pwd=8525ACF9A1AE904CED6B4297C558&content=testmsg&mobile=18671658593&stime=2016-01-15%20%2015:55:23&sign=testsign&type=pt&extno=1

	private String hostUrl = "http://web.wasun.cn/asmx/smsservice.aspx?";
	
	private String name = "15121186235";
	
	private String pwd = "8525ACF9A1AE904CED6B4297C558";
	
	private String content = "";
	
	private List<String> mobile = new ArrayList<String>();
	
	private String stime;
	
	private String sign = "嘉联e家";
	
	private String type = "pt";
	
	private String extno = "";

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getMobile() {
		return mobile;
	}

	public void addMobile (String mobile){
		this.mobile.add(mobile);
	}
	
	public void setMobile(List<String> mobile) {
		this.mobile = mobile;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtno() {
		return extno;
	}

	public void setExtno(String extno) {
		this.extno = extno;
	}
	
	public String getFullPath()  throws IOException{
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer(hostUrl);
		// 向StringBuffer追加用户名
		sb.append("name=" + name);
		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=" + pwd);
		// 向StringBuffer追加手机号码
		sb.append("&mobile=");
		for(int i = 0; i < mobile.size(); i ++){
			if(i != 0){
				sb.append(",");
			}
			sb.append(mobile.get(i));
			if(i != mobile.size() - 1){
				sb.append(",");
			}
		}
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content="+URLEncoder.encode(content,"UTF-8"));

		//追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");
		//加签名
		sb.append("&sign="+URLEncoder.encode(sign,"UTF-8"));
		//type为固定值pt  extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	public SMSResult sendSMS(String c, String m) throws IOException{
		if(c != null){
			this.content = c;
			this.mobile.add(m);
		}
		return sendSMS();
	}
	public SMSResult sendSMS() throws IOException{
		
		System.out.println("短信发送时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		URL url = new URL(getFullPath());

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		// 发送
		InputStream is =url.openStream();
		//转换返回值
		String returnStr = convertStreamToString(is);
		
		String[] code = returnStr.split(",");
		
		SMSResult result = new SMSResult();
		
		if("0".equals(code[0])){
			result.setSuccess(true);
			result.setMessage("发送成功");
		}else{
			result.setSuccess(false);
			result.setMessage("发送失败");
		}
		result.setCode(code[0]);
		result.setResult(returnStr);
		System.out.println(returnStr);
		return result;
	}
	
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	public String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
	
	public static void main(String[] args) {
		SMS sms = new SMS();
		sms.addMobile("18671658593");
		sms.addMobile("15121186235");
		sms.addMobile("18202856355");
		sms.addMobile("13761927368");
		sms.addMobile("13817134139");
		sms.setContent("您的验证码为123456");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			SMSResult result = new SMSResult();
			result = sms.sendSMS();
			System.out.println("result:" + JSON.toJSONString(result));
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
}
