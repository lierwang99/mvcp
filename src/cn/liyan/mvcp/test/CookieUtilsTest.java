package cn.liyan.mvcp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cn.liyan.mvcp.utils.CookieUtils;

class CookieUtilsTest {

	@Test
	void testMd5Encrypt() {
		String name = "admin";
		CookieUtils.md5Encrypt(name);
		CookieUtils.md5Encrypt(name);
		CookieUtils.md5Encrypt(name);
		CookieUtils.md5Encrypt(name);
				
	}

}
