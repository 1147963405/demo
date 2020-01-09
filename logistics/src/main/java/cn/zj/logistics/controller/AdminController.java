package cn.zj.logistics.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zj.logistics.mo.MessageObject;
import cn.zj.logistics.pojo.Role;
import cn.zj.logistics.pojo.RoleExample;
import cn.zj.logistics.pojo.User;
import cn.zj.logistics.pojo.UserExample;
import cn.zj.logistics.pojo.UserExample.Criteria;
import cn.zj.logistics.service.RoleService;
import cn.zj.logistics.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/adminPage")
	public String adminPage() {
		
		return "adminPage";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo<User> list(@RequestParam(defaultValue = "1")Integer pageNum,
								@RequestParam(defaultValue = "10")Integer pageSize,
								String keyword){

		PageHelper.startPage(pageNum, pageSize);
		
		//条件查询对象
		UserExample example = new UserExample();
		
		
		if(StringUtils.isNotBlank(keyword)) {
			
			//创建条件限制对象
			Criteria criteria = example.createCriteria();
			
			//账号和真是姓名模糊查询
			criteria.andUsernameLike("%"+keyword+"%");
			/*
			 * 直接在一个Criteria中设置多条件是AND关系，如果要多条件使用OR，必须创建多个Criteria
			 * 再设置OR
			 */
			
			//criteria.andRealnameLike("%"+keyword+"%");
			
			Criteria criteria2 = example.createCriteria();
			criteria2.andRealnameLike("%"+keyword+"%");
			
			example.or(criteria2);
		}
		
		
		List<User> users = userService.selectByExample(example );
		for (User user : users) {
			System.out.println(user);
		}
		
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		
		return pageInfo;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MessageObject delete(Long userId) {
		
		MessageObject mo = new MessageObject(0, "删除数据失败，请联系管理员");
		
		//执行删除操作
		int row = userService.deleteByPrimaryKey(userId);
		if(row == 1) {
			mo = new MessageObject(1, "删除数据成功");
		}
		
		return mo;
	}
	//编辑功能
	@RequestMapping("/edit")
	public String edit(Model m,Long userId) {
		
		//根据id查询出User对象，以供修改的回显
		if(userId !=null) {
			User user = userService.selectByPrimaryKey(userId);
			m.addAttribute("user", user);
		}
		
		
		
		//查询出所有的角色，以供新增和修改管理员的时候选择
		RoleExample example = new RoleExample();
		List<Role> roles = roleService.selectByExample(example );
		m.addAttribute("roles", roles);
		return "adminEdit";
	}
	
	//检查用户名是否存在
	@RequestMapping("/checkUsername")
	@ResponseBody
	public boolean checkUsername(String username) {
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		List<User> users = userService.selectByExample(example);
		
		return users.size() > 0 ? false : true; 
	}
	
	
	
	
	//新增操作
	@RequestMapping("/insert")
	@ResponseBody
	public MessageObject insert(User user) {
		
		//设置日期
		user.setCreateDate(new Date());
		
		MessageObject mo = new MessageObject(0, "新增数据失败，请联系管理员");
		
		//执行新增操作
		int row = userService.insert(user);
		if(row == 1) {
			mo = new MessageObject(1, "新增数据成功");
		}
		
		return mo;
	}
	
	//修改操作
	@RequestMapping("/update")
	@ResponseBody
	public MessageObject update(User user) {
		
		MessageObject mo = new MessageObject(0, "修改数据失败，请联系管理员");
		
		//执行修改操作
		int row = userService.updateByPrimaryKeySelective(user);
		if(row == 1) {
			mo = new MessageObject(1, "修改数据成功");
		}
		return mo;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		/*
		 * commons-lang3-3.1jar 
		 * Apache组织为Java语言编写的一个增强包，对现有java语言的API进行增强
		 *  3：是版本号
		 * 如：java的String 没有判断是否为空的功能
		 * 实际开发中
		 * 	null ，“” ，带空格的空 “” 程序中都认为空
		 *  commons-lang3-3.1jar 
		 *  就编写一个StringUtils工具对字符串功能进行增强
		 * 
		 */
		
		String str1 = "";
		String str2 = null;
		String str3 = "   ";
		
		System.out.println(StringUtils.isBlank(str1));
		System.out.println(StringUtils.isBlank(str2));
		System.out.println(StringUtils.isBlank(str3));
		System.out.println("---------------------------------");
		System.out.println(StringUtils.isNotBlank(str1));
		System.out.println(StringUtils.isNotBlank(str2));
		System.out.println(StringUtils.isNotBlank(str3));
		System.out.println("---------------------------------");
		System.out.println(StringUtils.isEmpty(str1));
		System.out.println(StringUtils.isEmpty(str2));
		System.out.println(StringUtils.isEmpty(str3));
		System.out.println("---------------------------------");
		System.out.println(StringUtils.isNotEmpty(str1));
		System.out.println(StringUtils.isNotEmpty(str2));
		System.out.println(StringUtils.isNotEmpty(str3));
		
	}
	
	
	
}
