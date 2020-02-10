package cn.liyan.mvcp.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//c3p0
	
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
/**
 * 
 * 获取mysql的数据库链接对象conn
 * @return
 */
	public static Connection getConnection() {
		Connection conn = null ;
		try {
			conn = dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 
	 * 关闭数据库
	 * @param conn
	 */
	public static void closeConn(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void  rollbackTransaction(Connection conn) {
		if(conn != null) {
			try {
				conn.rollback();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
