package com.gzho.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gzho.entity.Category;
import com.gzho.entity.Person;
import com.gzho.service.CategoryService;

/**
 * 
 *
 * @ClassName: TestSSH_service.java
 * @Description: Service测试
 * @author wss
 * @date 2017年9月21日 上午9:56:19
 */
public class TestSSH_service extends BasicTest {

	@Autowired
	private Person person;

	@Autowired
	private CategoryService categoryService;

	@Test
	public void TestPojo() {

		System.out.println("1");
		person.setId(10);
		System.out.println(person.getId() + "成功");
	}

	@Test
	public void TestService_findById() {

		System.out.println(categoryService);
		try {

			Category c = this.categoryService.findById(1);
			System.out.println("找到id为1的类别category，输出其id和名称：" + c.getId() + "......" + c.getName());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	@Test
	public void TestService() {

		System.out.println(categoryService);
		try {

			Category c = this.categoryService.findById(1);
			System.out.println("找到id为1的类别category，输出其id和名称：" + c.getId() + "......" + c.getName());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
