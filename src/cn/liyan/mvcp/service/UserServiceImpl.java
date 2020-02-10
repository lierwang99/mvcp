package cn.liyan.mvcp.service;

import java.sql.Connection;
import java.util.List;

import cn.liyan.mvcp.dao.FactoryDao;
import cn.liyan.mvcp.dao.UserDao;
import cn.liyan.mvcp.model.User;
import cn.liyan.mvcp.utils.JdbcUtils;

public class UserServiceImpl implements UserService {
	UserDao userDao = FactoryDao.getUserDao(); 
	@Override
	public int save(User user) {
		return userDao.save(user);
	}

	@Override
	public int deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}

	@Override
	public int updateUserById(User user) {
		return userDao.updateUserById(user);
	}

	@Override
	public User get(int id) {
		return userDao.get(id);
	}

	@Override
	public User getTransaction( int id) {
		Connection conn = null;
		User user = null ;
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);
			userDao.get(conn, id);
			conn.commit();	
		}catch(Exception e) {
			JdbcUtils.rollbackTransaction(conn);
		}
		return user;
	}

	@Override
	public List<User> getListAll() {
		List<User> listAll = userDao.getListAll();
		return listAll;
	}

	@Override
	public long getCountByName(String name) {
		long countByName = userDao.getCountByName(name);
		return countByName;
	}

	@Override
	public List<User> query(String username, String address, String phoneNo) {
		return userDao.query(username, address,phoneNo);
	}

	@Override
	public User login(String username, String password) {
		return userDao.getUserByUP(username,password);
	}
	


}
