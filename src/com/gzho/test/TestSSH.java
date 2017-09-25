package com.gzho.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gzho.entity.Category;
import com.gzho.service.CategoryService;

/**
 *
 * @ClassName: TestSSH.java
 * @Description:
 * @author wss
 * @date 2017年9月20日 上午9:58:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml"/* ,"classpath*:/spring-mvc.xml" */ })
public class TestSSH {

	@Resource
	private CategoryService categoryService;

	@Test // 测试数据查询
	@SuppressWarnings("unchecked") // 告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。
	public void testDAO() {

		System.out.println("1");

		Map<String, Object> map = new HashMap<String, Object>();

		try {

			List<Category> category = this.categoryService.findHQL("from Category_ c where c.id=0	", new String[] {});
			System.out.println("1");

			ArrayList<Category> list = new ArrayList<Category>();

			Category c;
			for (Category cg : category) {
				c = new Category();
				c.setId(cg.getId());
				c.setName(cg.getName());
				;

				list.add(c);

			}

			map.put("list", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("3" + map);

		// return map;
	}

}
