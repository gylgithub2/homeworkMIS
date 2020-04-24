package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import utils.JDBCUtils;

/**
 * @Decription 为所有的DAOimpl提供带泛型的父类
 */
@SuppressWarnings("unchecked")
public  class BaseDao<T> {
	private Class<T> clazz = null;
	{
		// 获取当前对象 泛型父类类型
		Type tp = this.getClass().getGenericSuperclass();
		// 强转为 带参数类型
		ParameterizedType p = (ParameterizedType) tp;
		// 获取父类的泛型类型
		Type[] t2 = p.getActualTypeArguments();
		// 获取第一个参数,强转
		clazz = (Class<T>) t2[0];
	}
	public int delete(Connection connect,String sql,Object...args) {
		return JDBCUtils.update(connect, sql, args);
	}
	public int update(Connection connect,String sql,Object...args) {
		return JDBCUtils.update(connect, sql, args);
	}
	public int insert(Connection connect,String sql,Object...args) {
		return JDBCUtils.update(connect, sql, args);
	}
	public T login(Connection connect,String sql,Object...args) {
		List<T> list = JDBCUtils.query(connect, sql, clazz, args);
		if(list.size()<=0) {
			return null;
		}
		return list.get(0);
	}
	public T queryOne(Connection connect,String sql,Object...args) {
		List<T> list = JDBCUtils.query(connect, sql, clazz, args); 
		if(list.size()<=0) {
			return null;
		}
		return list.get(0);
	}
	public List<T> queryAll(Connection connect,String sql,Object...args) {
		return JDBCUtils.query(connect, sql, clazz, args);
	}
	public List<T> queryPage(Connection connect,String sql,Object...args) {
		return JDBCUtils.query(connect, sql, clazz, args);
	}
}
