package com.gzho.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.td.util.page.Page;

@SuppressWarnings("all")
public interface BaseService<T> {
	/**
	 * 将瞬态（自由态）的实体加入session缓存，变成持久化对象
	 * 
	 * @param o
	 * @return 保存以后的ID
	 */
	public Serializable save(T o) throws Exception;

	/**
	 * 将我们提供的对象变成一个持久化对象，对象会纳入session的管理，对象的状态会跟数据库同步， 再次查询该对象会直接从session中取
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o) throws Exception;

	/**
	 * 更新一个对象，以下下情况使用 1、数据库记录已存在，更改的属性有变化。
	 * 
	 * 
	 * @param o
	 */
	public void update(T o) throws Exception;

	/**
	 * 将我们提供的对象转变为托管状态的对象，对象不会纳入session的管理，再次查询该对象还是会从数据库中取 更新一个对象，以下下情况使用
	 * 1、数据库记录已存在，更改的属性无变化。
	 * 
	 * @param o
	 */
	public void merge(T o) throws Exception;

	/**
	 * 将持久化的对象变成游离态（托管状态）
	 * 
	 * @param o
	 */
	public void delete(T o) throws Exception;

	/**
	 * 获得一个单个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public T findById(Serializable id) throws Exception;

	/**
	 * 单条件查询,只允许HQL
	 * 
	 * @param fields
	 *            查询的字段名，null则查询所有字段
	 * @param prop
	 *            表字段
	 * @param symbols
	 *            符号：>,<,<>,=,>=,<=,like,is,is not，in
	 * @param params
	 *            字段的值,like举例:"%%"+value+"%%",in 传入一个逗号分隔的字符串
	 * @param order
	 *            iid或iid asc或iid desc或null
	 * @return List
	 */
	public List find(String[] fields, String prop, String symbol, Object param, String[] order) throws Exception;

	/**
	 * 单条件查询,只允许HQL
	 * 
	 * @param fields
	 *            查询的字段名，null则查询所有字段
	 * @param prop
	 *            表字段
	 * @param symbols
	 *            符号：>,<,<>,=,>=,<=,like,is,is not，in
	 * @param params
	 *            字段的值,like举例:"%%"+value+"%%",in 传入一个逗号分隔的字符串
	 * @param page
	 *            查询第几页,从1开始
	 * @param rows
	 *            每页显示几条记录
	 * @param order
	 *            iid或iid asc或iid desc或null
	 * @return List
	 */
	public List find(String[] fields, String prop, String symbol, Object param, Integer page, Integer rows,
			String[] order) throws Exception;

	/**
	 * 多条件查询,只允许HQL
	 * 
	 * @param fields
	 *            查询的字段名，null则查询所有字段
	 * @param related
	 *            and或者or
	 * @param props
	 *            表字段
	 * @param symbols
	 *            符号：>,<,<>,=,>=,<=,like,is,is not，in
	 * @param params
	 *            字段的值,like举例:"%%"+value+"%%",in 传入一个逗号分隔的字符串
	 * @param order
	 *            iid或iid asc或iid desc或null
	 * @return List
	 */

	public List find(String[] fields, String[] relates, String[] props, String[] symbols, Object[] params,
			String[] order) throws Exception;

	/**
	 * 多条件查询分页,只允许HQL
	 * 
	 * @param fields
	 *            查询的字段名，null则查询所有字段
	 * @param relates
	 *            and或者or
	 * @param props
	 *            表字段
	 * @param symbols
	 *            符号：>,<,<>,=,>=,<=,like,is,is not,in
	 * @param params
	 *            字段的值,like举例:"%%"+value+"%%",in 传入一个逗号分隔的字符串
	 * @param order
	 *            iid或iid asc或iid desc或null
	 * @param page
	 *            查询第几页,从1开始
	 * @param rows
	 *            每页显示几条记录
	 * @return List
	 */
	public List find(String[] fields, String[] relates, String[] props, String[] symbols, Object[] params, Integer page,
			Integer rows, String[] order) throws Exception;

	/**
	 * 单条件查询数量,只允许HQL
	 * 
	 * @param prop
	 *            表字段
	 * @param symbols
	 *            符号：>,<,<>,=,>=,<=,like,is,is not，in
	 * @param params
	 *            字段的值,like举例:"%%"+value+"%%",in 传入一个逗号分隔的字符串
	 * @return Long
	 */
	public Long count(String prop, String symbol, Object param) throws Exception;

	/**
	 * 单条件查询数量,只允许HQL
	 * 
	 * @param relates
	 *            and或者or
	 * @param props
	 *            表字段
	 * @param symbols
	 *            符号：>,<,<>,=,>=,<=,like,is,is not，in
	 * @param params
	 *            字段的值,like举例:"%%"+value+"%%",in 传入一个逗号分隔的字符串
	 * @return Long
	 */
	public Long count(String[] relates, String[] props, String[] symbols, Object[] params) throws Exception;

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param order
	 * @param sort
	 * @return
	 */
	public List findByPage(Integer page, Integer rows, String order, String sort) throws Exception;

	/**
	 * 查找所有的数据,只允许HQL
	 * 
	 * @return List
	 */
	public List<T> findAll() throws Exception;

	/**
	 * 查找总数据量,只允许HQL
	 * 
	 * @return Long
	 */
	public Long countAll() throws Exception;

	/**
	 * 删除所有记录
	 * 
	 * @return Long
	 */
	public Integer deleteAll() throws Exception;

	/**
	 * 删除单张表
	 * 
	 * @param tablename表名，HQL
	 * @return 响应行数
	 */
	public Integer deleteTableByName(String tablename) throws Exception;

	/**
	 * 删除多张表
	 * 
	 * @param tablenames表名数组，HQL
	 * @return 响应行数
	 */
	public Integer deleteTableByNames(String[] tablenames) throws Exception;

	public Map<String, Object> findPagination(Page page, List<Map<String, Object>> filterRules, String inAppend,
			String order) throws Exception;

	public List findSQL(String sql, Object[] param) throws Exception;
	
	public Object countSQL(String sql, Object[] param) throws Exception;
	
	public List findHQL(String hql, Object[] param) throws Exception;
	
	public Object countHQL(String hql, Object[] param) throws Exception;
}
