package com.gzho.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @ClassName: TestSSH.java
 * @Description:简单测试
 * @author wss
 * @date 2017年9月20日 上午9:58:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml") /* ,"classpath*:/spring-mvc.xml" */
public class TestSSH_Spring {

	@Test //
	public void testDAO() {

		System.out.println("1");
	}

}
