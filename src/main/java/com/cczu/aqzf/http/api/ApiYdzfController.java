package com.cczu.aqzf.http.api;

import com.cczu.aqzf.model.service.AqzfZlzgService;
import com.cczu.sys.comm.controller.BaseController;
import com.cczu.sys.comm.utils.security.Digests;
import com.cczu.sys.comm.utils.security.Encodes;
import com.cczu.sys.system.dao.UserDao;
import com.cczu.sys.system.entity.User;
import com.cczu.sys.system.service.RoleService;
import com.cczu.sys.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 移动执法
 * @author zpc
 * @date 2018/03/22
 */
@Controller
@RequestMapping("api/ydzf")
public class ApiYdzfController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private AqzfZlzgService aqzfZlzgService;
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String account = request.getParameter("account");//账号
			String password = request.getParameter("password");//密码
			User user = userService.getUser(account);
			if (user == null) {
				map.put("status", "1003");//用户名无效
			}else{
				byte[] salt = Encodes.decodeHex(user.getSalt());
				byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
				if (user.getPassword().equals(Encodes.encodeHex(hashPassword))) {
					map.put("status", "1001");//成功
					map.put("userinfo", user);
				}else{
					map.put("status", "1004");//密码错误
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			map.put("status", "1002");//失败
		}
		return map;
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value = "updatePwd")
	@ResponseBody
	public Map<String, Object> updatePwd(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String userid = request.getParameter("userid");
			String oldPassword = request.getParameter("oldPassword");//旧密码
			String newPassword = request.getParameter("newPassword");//新密码
			User user = userService.getUserSqlById(Integer.parseInt(userid));;
			byte[] saltByte = Encodes.decodeHex(user.getSalt()) ;
			if(userService.checkPassword(user, oldPassword)){
				byte[] hashNewPassword = Digests.sha1(newPassword.getBytes(),saltByte, 1024);
				user.setPassword(Encodes.encodeHex(hashNewPassword));
				userDao.save(user);
				map.put("status", "1001");//成功
			}else{
				map.put("status", "1005");//旧密码错误
			}
		} catch (Exception e) {
			map.put("status", "1002");//失败
		}
		return map;
	}
	
	/**
	 * 首页消息提醒list
	 */
	@RequestMapping(value = "syxxlist")
	@ResponseBody
	public List<Map<String, Object>> syxxlist(String xzqy) {
		Map<String,Object> map = new HashMap<>();
		map.put("xzqy",xzqy);
		return aqzfZlzgService.dataGridForH5(map);
	}
}
