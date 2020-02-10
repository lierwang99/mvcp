package cn.liyan.mvcp.service;

import java.sql.Connection;
import java.util.List;

import cn.liyan.mvcp.model.User;

public interface UserService {
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
	public User getTransaction(int id);
	
	
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
	
	/**
	 * 
	 * @param username
	 * @param address  实现模糊查询的接口
	 * @param phoneNo
	 * @return
	 */
	public List<User> query(String username, String address, String phoneNo);
	/**
	 * 
	 * 这个是判断用户名和密码是否正确
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password);
	
}
