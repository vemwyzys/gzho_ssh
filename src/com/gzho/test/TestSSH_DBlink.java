package com.gzho.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gzho.entity.Category;
import com.gzho.service.CategoryService;


/**
 *
 * @ClassName:     TestSSH.java
 * @Description:   
 * @author         wss
 * @date           2017年9月20日 上午9:58:49
 */
public class TestSSH_DBlink {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	private ApplicationContext ctx = null;
	
	@Test//测试数据库链接
	public void tetsDataSource() throws SQLException{
		
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("数据源:"+ctx);
		
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println("打开数据连接："+dataSource.getConnection().toString());
		
		SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
		System.out.println("sessionFactory:"+sessionFactory); 
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		tx.commit();
		session.close();
		System.out.println("session已经关闭");
		
		
	}
}
