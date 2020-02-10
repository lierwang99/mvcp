package cn.liyan.mvcp.utils;

import java.security.MessageDigest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	//这个加上用户名就是加密算法的参数，且加密算法是不可逆的
	private static final String KEY = ":cookie@baidu.com!";
	/**
	 * 指令浏览器创建cookie文件用的方法
	 * @param username ：cookiex名
	 * @param request 
	 * @param response ：调用addCookie方法
	 * @param secoonds ：Cookie有效期，单位是秒
	 */
	public static void createCookie(String username,HttpServletRequest request,HttpServletResponse response,int seconds) {
		Cookie userCookie = new Cookie("userKey",username);
		Cookie ssidCookie = new Cookie("ssid",md5Encrypt(username));
		userCookie.setMaxAge(seconds);
		ssidCookie.setMaxAge(seconds);
		response.addCookie(userCookie);
		response.addCookie(ssidCookie);
	}
	/**
	 * 这个方法就是加密方法，把铭文加密
	 * @param key ：等待被加密的铭文
	 * @return  digest 消化  汇编
	 */
	public static String md5Encrypt(String key) {
		key = key==null?"":key+KEY;
		
		char[] md5Digist = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};//字典
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");// md5 sha1
			byte[] by = key.getBytes();
			md.update(by);//把铭文放到加密类MessageDigest的对象实例中，更新数据
			byte[] digest = md.digest();//这里是进行加密
			int len = digest.length;
			char[] ch = new char[len*2];
			int start = 0;
			for(int x=0;x<len;x++) {
				byte b = digest[x];
				ch[start++] = md5Digist[b >>> 4 & 0xf];
				ch[start++] = md5Digist[b  & 0xf];
			}
			String string = new String(ch);
			System.out.println(string);
			return string;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
