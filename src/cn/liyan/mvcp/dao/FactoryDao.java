package cn.liyan.mvcp.dao;

public class FactoryDao {
	public static  UserDao getUserDao() {
		return new UserDaoImpl();
	}

}
