package cn.liyan.mvcp.dao;

import java.sql.Connection;
import java.util.List;

import cn.liyan.mvcp.model.User;

/**
 * 接口规则指定，定义方法；UserDao定义与users数据表有关系的操作方法
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 保存，增加
	 * @param user
	 * @return
	 */
	public int save(User user);
	/**
	 * 根据编号删除用户
	 * @param id
	 * @return
	 */
	public int deleteUserById(int id);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public int updateUserById(User user);
	/**
	 * 根据用户编号，获取一条用户数据，封装成类User的一个对象
	 * @param id
	 * @return
	 */
	public User get(int id);
	/**
	 * 
	 * 支持事务
	 */
	public User get(Connection conn,int id);
	
	
	/**
	 * 获取所有用户信息，返回list集合
	 * @return
	 */
	public List<User> getListAll();
	/**
	 * 查询指定用户的名字的条数
	 * @param name
	 * @return
	 */
	public long getCountByName(String name);
	/*
	 * 
	 * Dao层里实现模糊查询的方法
	 * 
	 */
	public List<User> query(String username, String address, String phoneNo);
	/**
	 * 实现根据用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUserByUP(String username, String password);
	
}
