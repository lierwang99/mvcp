package cn.liyan.mvcp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.liyan.mvcp.utils.JdbcUtils;

/**
 * 
 * 这是一个dao基本类，用于被具体的dao类来继承
 * @author Administrator
 *
 * @param <T>针对将要操作的各张数据表映射到java工程里
 */
public class BaseDao<T> {
	//对数据由增删改查
	 QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;
	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		if(genericSuperclass instanceof ParameterizedType) {
			 ParameterizedType pt = (ParameterizedType) genericSuperclass;
			 Type[] actualTypeArguments = pt.getActualTypeArguments();
			 if(actualTypeArguments[0] instanceof Class) {
				 clazz = (Class<T>) actualTypeArguments[0];
			 }
		}
		
	}
	
	
	/**
	 * 查询数据表，取出sql语句的结果集的第一条数据，封装成一个类的对象返回，不支持事务
	 * 用到dbutils工具类
	 * null位置，应该传入BaseDao<T>里面T的真正用的类型的Class 没有T.class这种写法
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object... args) {
		Connection conn = null;
		T entity = null;
		try {
			//拿conn
			conn = JdbcUtils.getConnection();
			entity = queryRunner.query(conn, sql,new BeanHandler<T>(clazz), args);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return entity;
	}
	
	/**
	 * 查询数据表，取出sql语句的结果集的第一条数据，封装成一个类的对象返回，支持事务
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(Connection conn,String sql,Object... args) {
		T entity = null;
		try {
			entity = queryRunner.query(conn, sql,new BeanHandler<T>(clazz), args);
			//这个commit是自己加的，好像没必要加，以为最终finally会对其进行关闭	
			//conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return entity;
	}
	/**
	 * 
	 * 获取多条记录的通用方法
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getList(String sql,Object... args){
//		System.out.println("开始调用BaseDao中的getList方法");
		Connection conn = null;
		//jdk1.7之后后面泛型T可以不写<>
	//	List<T> list = new ArrayList<T>();
		List<T> list = null ;
		try {
			conn = JdbcUtils.getConnection();
			list  = queryRunner.query(conn, sql,new BeanListHandler<>(clazz));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return list;
	}
	/**
	 * 返回影响的行数，通过id进行更新
	 * @param sql
	 * @param args
	 * @return
	 */
	public int update(String sql,Object... args){
		Connection conn = null;
		//jdk1.7之后后面泛型T可以不写<>
	//	List<T> list = new ArrayList<T>();
		int rows = 0 ;
		try {
			conn = JdbcUtils.getConnection();
			rows = queryRunner.update(conn, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return rows;
	}
	/**
	 * 通用的返回sql语句的结果只有一个数值的类型的查询
	 */
	public Object getValue(String sql,Object... args){
		System.out.println("开始查询");
		Connection conn = null;
		//jdk1.7之后后面泛型T可以不写<>
	//	List<T> list = new ArrayList<T>();
		Object obj = null ;
		try {
			conn = JdbcUtils.getConnection();
//			System.out.println("conn:"+conn);
			obj = queryRunner.query(conn,sql,new ScalarHandler<Object>(),args);
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.closeConn(conn);
		}
		return obj;
	}
}
