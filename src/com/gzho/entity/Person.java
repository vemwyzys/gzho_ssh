package com.gzho.entity;

import org.springframework.stereotype.Component;

/**
 *
 * @ClassName:
 * @Description:(测试用)
 * @author wss
 * @date 2017年9月21日 上午10:44:34
 */
@Component
public class Person {

	private int id;
	private String name;

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
