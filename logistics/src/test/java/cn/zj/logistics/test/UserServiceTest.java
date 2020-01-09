package cn.zj.logistics.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zj.logistics.pojo.User;
import cn.zj.logistics.pojo.UserExample;
import cn.zj.logistics.pojo.UserExample.Criteria;
import cn.zj.logistics.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void testSelectByExample() {
		
		/*
		 * 
		 * 
		 */
		
		/*开始分页
		 * int pageNum;  当前页，页码 默认 1
		 * int pageSize; 每页条数 
		 * PageHelper.startPage(pageNum, pageSize)
		 */
		int pageNum = 5;
		int pageSize = 20;
		PageHelper.startPage(pageNum, pageSize);
		
		//条件查询对象
		UserExample example = new UserExample();
		
		//创建条件限制对象
		Criteria criteria = example.createCriteria();
		
		//String keyword = "y";
		
		//设置查询条件
		//criteria.andUsernameLike("%"+keyword+"%");

		List<User> users = userService.selectByExample(example );
		for (User user : users) {
			System.out.println(user);
		}
		
		/*
		 * 
		 * 创建分页插件的分页信息对象PageInfo
		 * 
		 * PageInfo 对象就封装所有的分页信息
		 * 如：
		 * 当前页的结果集
		 * 总条数
		 * 上一页页码
		 * 下一页页码
		 * ....
		 * 
		 * 
		 * 
		 */
		
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		System.out.println(pageInfo);
	}

	@Test
	public void testDeleteByPrimaryKey() {
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}


	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

}
