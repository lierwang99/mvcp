package cn.liyan.mvcp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import cn.liyan.mvcp.utils.JdbcUtils;

class JdbcUtilsTest {

	@Test
	void testGetConnection() {
		Connection connection = JdbcUtils.getConnection();
		System.out.println(connection);
	}

}
