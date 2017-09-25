package com.gzho.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gzho.dao.BaseDao;
import com.gzho.service.BaseService;
import com.td.util.page.Page;

@Service
@Transactional
@SuppressWarnings("all")
public class BaseServiceImpl<T> implements BaseService<T> {

	@Resource
	protected BaseDao<T> dao;

	private Class clazz = null;

	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	@Override
	public Serializable save(T o) throws Exception {
		return dao.save(o);
	}

	@Override
	public void saveOrUpdate(T o) throws Exception {
		dao.saveOrUpdate(o);
	}

	@Override
	public void update(T o) throws Exception {
		dao.update(o);
	}

	@Override
	public void merge(T o) throws Exception {
		dao.merge(o);
	}

	@Override
	public void delete(T o) throws Exception {
		dao.delete(o);
	}

	@Override
	public T findById(Serializable id) throws Exception {
		return (T) dao.get(clazz, id);
	}

	@Override
	public List find(String[] fields, String prop, String symbol, Object param, String[] order) throws Exception {
		return this.find(fields, prop, symbol, param, null, null, order);
	}

	@Override
	public List find(String[] fields, String prop, String symbol, Object param, Integer page, Integer rows, String[] order) throws Exception {
		return this.find(fields, new String[] { "and" }, prop == null ? new String[] {} : new String[] { prop },
				symbol == null ? new String[] {} : new String[] { symbol }, param == null ? new Object[] {} : new Object[] { param }, page, rows,
				order);
	}

	@Override
	public List find(String[] fields, String[] relates, String[] props, String[] symbols, Object[] params, String[] order) throws Exception {
		return this.find(fields, relates, props, symbols, params, null, null, order);
	}

	@Override
	public List find(String[] fields, String[] relates, String[] props, String[] symbols, Object[] params, Integer page, Integer rows, String[] order)
			throws Exception {
		StringBuilder hql = null;
		if (fields != null && fields.length > 0) {
			hql = new StringBuilder("select new ");
			hql.append(clazz.getName() + "(");
			for (int i = 0; i < fields.length - 1; i++) {
				hql.append("t." + fields[i] + ",");
			}
			hql.append("t." + fields[fields.length - 1] + ") from ");
		} else {
			hql = new StringBuilder("from ");
		}
		hql.append(clazz.getSimpleName());
		hql.append(" t where 1=1 ");
		if (props != null && props.length > 0) {
			for (int i = 0; i < props.length; i++) {
				hql.append(relates[i]).append(" t.").append(props[i]).append(" ").append(symbols[i]);
				if (symbols[i].startsWith("is")) {
					hql.append(" null ");
				} else {
					hql.append(" :param"+i+" ");
				}
			}
		}
		if (order != null && order.length > 0) {
			hql.append(" order by ");
			for (int i = 0; i < order.length; i++) {
				hql.append(order[i]);
				if (i < order.length - 1) {
					hql.append(",");
				}
			}
		}
		if (page != null && rows != null) {
			return dao.find(hql.toString(), page, rows, params);
		} else {
			
			
			
			return dao.find(hql.toString(), params);
		}
	}

	@Override
	public List findByPage(Integer page, Integer rows, String order, String sort) throws Exception {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(clazz.getSimpleName());
		hql.append(" t where 1=1 ");
		if (!(StringUtils.isEmpty(order) || StringUtils.isEmpty(sort))) {
			hql.append(" order by");
			String[] ord = order.split(",");
			String[] sor = sort.split(",");
			for (int i = 0; i < ord.length; i++) {
				hql.append(" t." + sor[i] + " " + ord[i] + ",");
			}
		}
		return dao.find(hql.substring(0, hql.length() - 1), page, rows, null);
	}

	@Override
	public Long count(String prop, String symbol, Object param) throws Exception {
		return this.count(new String[] { "and" }, prop == null ? new String[] {} : new String[] { prop },
				symbol == null ? new String[] {} : new String[] { symbol }, param == null ? new Object[] {} : new Object[] { param });
	}

	@Override
	public Long count(String[] relates, String[] props, String[] symbols, Object[] params) throws Exception {
		StringBuilder hql = new StringBuilder("from ");
		hql.append(clazz.getSimpleName());
		hql.append(" t where 1=1 ");
		if (props != null && props.length > 0) {
			for (int i = 0; i < props.length; i++) {
				hql.append(relates[i]).append(" t.").append(props[i]).append(" ").append(symbols[i]);
				if (symbols[i].startsWith("is")) {
					hql.append(" null ");
				} else {
					hql.append(" :param"+i+" ");
				}
			}
		}
		return dao.count(hql.toString(), params);
	}

	@Override
	public List findAll() throws Exception {
		return this.find(null, null, null, null, null);
	}

	@Override
	public Long countAll() throws Exception {
		return this.count(null, null, null);
	}

	@Override
	public Integer deleteTableByName(String tablename) throws Exception {
		return this.deleteTableByNames(new String[] { tablename });
	}

	@Override
	public Integer deleteTableByNames(String[] tablenames) throws Exception {
		if (tablenames != null && tablenames.length > 0) {
			for (int i = 0; i < tablenames.length; i++) {
				dao.executeHql("delete from " + tablenames[i], null);
			}
		}
		return tablenames.length;
	}

	@Override
	public Integer deleteAll() throws Exception {
		return dao.executeHql("delete from " + clazz.getName(), null);
	}
	
	@Override
	public Map<String, Object> findPagination(Page page, List<Map<String, Object>> filterRules,String inAppend,String order) throws Exception {
		String hql = "from "+clazz.getSimpleName()+" t where 1=1";
		for (Map<String, Object> fr : filterRules) {
			hql += " and " + fr.get("field") + " " + fr.get("op") + " " + fr.get("value");
		}
		if(!StringUtils.isEmpty(inAppend)){
			hql+=inAppend;
		}
		if(!StringUtils.isEmpty(order)){
			hql+=" order by "+order;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List list = dao.find(hql, page.getPage(), page.getRows(), null);
		map.put("total", dao.count(hql, null));
		map.put("rows", list);
		return map;
	}
	
	@Override
	public List findSQL(String sql, Object[] param) throws Exception {
		System.out.println("sql"+sql);
		return dao.findSQL(sql, param);
	}

	@Override
	public Object countSQL(String sql, Object[] param) throws Exception {
		return dao.executeSQLRetUnique(sql, param);
	}

	@Override
	public List findHQL(String hql, Object[] param) throws Exception {
		
		return dao.find(hql, param);
	}

	@Override
	public Object countHQL(String hql, Object[] param) throws Exception {
		return dao.count(hql, param);
	}
	
}
