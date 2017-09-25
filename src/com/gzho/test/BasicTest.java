package com.gzho.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 
 *
 * @ClassName:     BasicTest.java
 * @Description:   service测试的父类
 * @author         wss
 * @date           2017年9月21日 上午9:42:37
 */

@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
//继承的AbstractJUnit4SpringContextTests 提供了数据库自动回滚,其类上已经有了@RunWith，所以就不需要在类上写 "@RunWith(SpringJUnit4ClassRunner.class)"
public class BasicTest extends AbstractTransactionalJUnit4SpringContextTests{

}
