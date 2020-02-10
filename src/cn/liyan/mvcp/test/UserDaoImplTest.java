package cn.liyan.mvcp.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import cn.liyan.mvcp.dao.UserDao;
import cn.liyan.mvcp.dao.UserDaoImpl;
import cn.liyan.mvcp.model.User;
import cn.liyan.mvcp.utils.JdbcUtils;

class UserDaoImplTest {

	UserDao userDao = new UserDaoImpl();
//	@Test
//	void testGetInt() {
//		User user = userDao.get(2);
//		System.out.println(user);
//	}

	@Test
	void testGetConnectionInt() {
		Connection conn = JdbcUtils.getConnection();
		User user = null;
		
		try {
			conn.setAutoCommit(false);
			 user = userDao.get(conn, 2);
			 //这里不能加commit()因为上面的get方法在BaseDao中已经关闭了数据库链接,会出现空指向异常
			// conn.commit(); 
			 
		} catch (Exception e) {
			e.printStackTrace();
//			try {
//				//conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
		}finally{
			JdbcUtils.closeConn(conn);
		}
		System.out.println(user);
	}

}
