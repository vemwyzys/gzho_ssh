package com.gzho.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.gzho.dao.BaseDao;
import com.gzho.dao.BaseSessionFactory;

/**
 * 基础数据库操作类
 * 
 * @author CXD
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class BaseDaoImpl<T> extends BaseSessionFactory implements BaseDao<T> {

	@Override
	public Serializable save(T o) throws Exception {
		return getSession().save(o);
	}

	@Override
	public void delete(T o) throws Exception {
		getSession().delete(o);

	}

	@Override
	public void update(T o) throws Exception {
		getSession().update(o);

	}

	@Override
	public void merge(T o) throws Exception {
		getSession().merge(o);

	}

	@Override
	public void saveOrUpdate(T o) throws Exception {
		getSession().saveOrUpdate(o);

	}

	private Query setParam(Query q, Object[] param) {
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				if (param[i] instanceof List) {
					q.setParameterList("param" + i, (Collection<?>) param[i]);
				} else {
					q.setParameter("param" + i, param[i]);
				}
			}
		}
		return q;
	}

	private String toNamedParam(String sql, Object[] param) {
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				int index = sql.indexOf("?");
				String str1 = sql.substring(index + 1);
				String str2 = "";
				if (index > -1) {
					str2 = sql.substring(0, index + 1).replace("?", ":param" + i);
				}
				sql = str2 + str1;
			}
		}
		return sql;
	}

	@Override
	public List find(String hql, Object[] param) throws Exception {
		hql = toNamedParam(hql, param);
		Query q = getSession().createQuery(hql);
		return this.setParam(q, param).setCacheable(true).list();
	}

	@Override
	public List find(String hql, Integer page, Integer rows, Object[] param) throws Exception {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 15;
		}
		hql = toNamedParam(hql, param);
		Query q = getSession().createQuery(hql);
		return this.setParam(q, param).setFirstResult((page - 1) * rows).setMaxResults(rows).setCacheable(true).list();
	}

	@Override
	public T get(Class<T> c, Serializable id) throws Exception {
		return (T) getSession().get(c, id);
	}

	@Override
	public Long count(String hql, Object[] param) throws Exception {
		hql = toNamedParam(hql, param);
		Query q = getSession().createQuery("select count(1) " + hql);
		return (Long) this.setParam(q, param).uniqueResult();
	}

	@Override
	public Integer executeHql(String hql, Object[] param) throws Exception {
		hql = toNamedParam(hql, param);
		Query q = getSession().createQuery(hql);
		return this.setParam(q, param).executeUpdate();
	}

	@Override
	public Object executeHqlRetUnique(String hql, Object[] param) throws Exception {
		hql = toNamedParam(hql, param);
		Query q = getSession().createQuery(hql);
		return this.setParam(q, param).uniqueResult();
	}

	@Override
	public List findSQL(String sql, Object[] param) throws Exception {
		sql = toNamedParam(sql, param);
		SQLQuery q = getSession().createSQLQuery(sql);
		return this.setParam(q, param).list();
	}

	@Override
	public List findSQLToEntity(String sql, Class clazz, Object[] param) throws Exception {
		sql = toNamedParam(sql, param);
		Query q = getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz));
		return this.setParam(q, param).list();
	}

	@Override
	public List findSQL(String sql, Integer page, Integer rows, Object[] param) throws Exception {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 15;
		}
		sql = toNamedParam(sql, param);
		Query q = getSession().createSQLQuery(sql);
		return this.setParam(q, param).setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public Integer executeSQL(String sql, Object[] param) throws Exception {
		sql = toNamedParam(sql, param);
		Query q = getSession().createSQLQuery(sql);
		return this.setParam(q, param).executeUpdate();
	}

	@Override
	public Object executeSQLRetUnique(String sql, Object[] param) throws Exception {
		sql = toNamedParam(sql, param);
		Query q = getSession().createSQLQuery(sql);
		return this.setParam(q, param).uniqueResult();
	}

	@Override
	public List findSQLToEntity(String sql, Class clazz, Integer page, Integer rows, Object[] param) throws Exception {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 15;
		}
		sql = toNamedParam(sql, param);
		Query q = getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz));
		return this.setParam(q, param).setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public void clearSession() throws Exception {
		getSession().flush();
		getSession().clear();
	}

	@Override
	public void executeBatch(final List<String> sqls) throws Exception {
		getSession().doWork(new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
				Statement stmt = conn.createStatement();
				for (int i = 0; i < sqls.size(); i++) {
					stmt.addBatch(sqls.get(i));
					if ((i + 1) % 5000 == 0) {
						stmt.executeBatch();
						conn.commit();
					}
				}
				stmt.executeBatch();
				conn.commit();
			}
		});
	}

}
