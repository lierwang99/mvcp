package cn.liyan.mvcp.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import cn.liyan.mvcp.model.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public int save(User user) {
		String sql = "INSERT INTO `user` (`username`,`password`,`phone_no`,`address`,`reg_date`)VALUES(?,?,?,?,?);";
		String username = user.getUsername();
		String password = user.getPassword();
		String phoneNo = user.getPhoneNo();
		String address = user.getAddress();
		Date regDate = user.getRegDate();
		return super.update(sql, username,password,phoneNo,address,regDate);
	}

	@Override
	public int deleteUserById(int id) {
		String sql = "DELETE FROM `user` WHERE `id` = ?;";
		return super.update(sql, id);
	}

	@Override
	public int updateUserById(User user) {
		String sql = "UPDATE `user` SET `username`=?,`password`=?,`phone_no`=?,`address`=? WHERE `id`=?;";
		
		String username = user.getUsername();
		String password = user.getPassword();
		String phoneNo = user.getPhoneNo();
		String address = user.getAddress();
		int id = user.getId();
		return super.update(sql, username,password,phoneNo,address,id);
	}

	@Override
	public User get(int id) {
		String sql = "SELECT `id`,`username`,`password`,`phone_no` phoneNo,`address`,`reg_date` regDate FROM `user` WHERE `id`=? ;";
		return super.get(sql, id);
		 
	}
	
	@Override
	public User get(Connection conn, int id) {
		String sql = "SELECT `id`,`username`,`password`,`phone_no` phoneNo,`address`,`reg_date` regDate FROM `user` WHERE `id`=? ;";
		return super.get(conn, sql, id);
	}

	@Override
	public List<User> getListAll() {
		String sql = "SELECT `id`,`username`,`password`,`phone_no` phoneNo,`address`,`reg_date` regDate FROM `user`;";
		return super.getList(sql);
	}

	@Override
	public long getCountByName(String name) {
		String sql = "SELECT COUNT(`id`) FROM `user` WHERE `username`=?;";
		return (long) super.getValue(sql, name);
	}

	@Override
	public List<User> query(String username, String address, String phoneNo) {
		String sql = "SELECT `id`,`username`,`password`,`phone_no` phoneNo,`address`,`reg_date` regDate FROM `user` WHERE 1=1";
		if(username != null && !"".equals(username)) {
			sql = sql + "  AND username like '%"+username+"%'"; //存在有sql注入的风险
		}
		if(address != null && !"".equals(address)) {
			sql = sql + "  AND address like '%"+address+"%'";
		}
		//下面AND后面的phone_no不可以用别名代替
		if(phoneNo != null && !"".equals(phoneNo)) {
			sql = sql + "  AND phone_no like  '%"+phoneNo+"%'";
		}
//		System.out.println("sql语句："+sql);
		return super.getList(sql);
	}

	@Override
	public User getUserByUP(String username, String password) {
		String sql = "SELECT `id`,`username`,`password`,`phone_no` phoneNo,`address`,`reg_date` regDate FROM `user` WHERE `username`=? AND `password`=?";
		return super.get(sql, username,password);
	}


}
