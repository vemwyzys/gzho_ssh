package com.gzho.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @ClassName: Category.java
 * @Description:价格表Category_(测试用)
 * @author wss
 * @date 2017年9月20日 上午10:44:34
 */
@Entity
@Table(name = "category_", catalog = "gzho_ssh")
public class Category {

	private int id;
	private String name;

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	// 根据底层数据库自动选择方式，需要底层数据库的设置如MySQL，会使用自增字段，需要将主键设置成auto_increment。
	// @GeneratedValue(strategy = GenerationType.IDENTITY)//自动增长，适用于支持自增字段的数据库
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
