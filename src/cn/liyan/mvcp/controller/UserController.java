package cn.liyan.mvcp.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.liyan.mvcp.model.User;
import cn.liyan.mvcp.service.FactoryService;
import cn.liyan.mvcp.service.UserService;
import cn.liyan.mvcp.utils.CookieUtils;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = { "*.udo" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = FactoryService.getUserService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
//		System.out.println(request.getServletPath());
//		System.out.println(request.getServerName());
//		System.out.println(request.getServerPort());
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(1, servletPath.length() - 4);
		System.out.println("调用的方法:" + servletPath);
		try {
			Method method = this.getClass().getDeclaredMethod(servletPath, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newName = request.getParameter("username");
		System.out.println("新名字：" + newName);
		long count = userService.getCountByName(newName);
		System.out.println("名称为：\"" + newName + "\"的条数为：" + count + "，新名字的长度为：" + newName.length());
		boolean blankSpace = FactoryService.isBlankSpace(newName);
		if (count > 0 || "".equals(newName) || blankSpace) {
			request.setAttribute("note", "名称不能为空或者空字符串以及注册过的名字,请换一个名字!");
			request.getRequestDispatcher("/jsp/add.jsp").forward(request, response);
			return;
		}
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setAddress(request.getParameter("address"));
		user.setPhoneNo(request.getParameter("phoneNo"));
		user.setRegDate(new Date());

		int rows = userService.save(user);
		if (rows > 0) {
			response.sendRedirect(request.getContextPath() + "/jsp/portal.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
		}
	}

	/*
	 * 实现首页的模糊查询
	 * 
	 * 
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("查询方法");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String phoneNo = request.getParameter("phoneNo");
//		 通过以下方式避免sql注入，即通过正则表达式替换掉特殊字符
//		username = username==null?"":username.replaceAll("[","");
//		address = address==null?"":address.replaceAll("['","");
//		phoneNo = phoneNo==null?"":phoneNo.replaceAll("['","");

		List<User> list = userService.query(username, address, phoneNo);
//		System.out.println("list:" + list);
		request.setAttribute("userList", list);// 这里只是把结果集放进request属性空间
		request.getRequestDispatcher("/jsp/portal.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int rows = userService.deleteUserById(id);
		if (rows > 0) {
			response.sendRedirect(request.getContextPath() + "/jsp/portal.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.get(id);
//		System.out.println(user);
		request.setAttribute("user", user);

//		System.out.println(request.getContextPath());
		request.getRequestDispatcher("/jsp/edit.jsp").forward(request, response);
	}

	private void updatedo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.get(id);
		String oldName = user.getUsername();
		System.out.println("oldName:" + oldName);
		String newName = request.getParameter("username");
		System.out.println("新名字：" + newName);
		long count = userService.getCountByName(newName);
		System.out.println("名称为：\"" + newName + "\"的条数为：" + count + "，新名字的长度为：" + newName.length());
		if (newName.length() == 0) {
			request.setAttribute("note", "名称不能为空或者空字符串,请换一个名字!");
			request.getRequestDispatcher("/update.udo?id=" + id).forward(request, response);
			return;
		}

		if (!oldName.equals(newName) && count > 0) {
			request.setAttribute("note", "名字已经被使用,请换一个名字!");
			request.getRequestDispatcher("/update.udo?id=" + id).forward(request, response);
			return;
		}
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setAddress(request.getParameter("address"));
		user.setPhoneNo(request.getParameter("phoneNo"));

		int rows = userService.updateUserById(user);
		request.getRequestDispatcher("/jsp/portal.jsp").forward(request, response);
		System.out.println("影响的行数：" + rows);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("login----");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String expiredays = request.getParameter("expiredays");
		String md5Encrypt = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {//这里面的加上非空判断还是很有必要的，不然在进行cookies.length时已经出现NullPointExcetpion
			System.out.println("非首次登录时遍历cookies：");
			for (Cookie cookie : cookies) {
				System.out.println("cookie名称：" + cookie.getName() + ",值：" + cookie.getValue());
			}
		}
		boolean login = false;// 是否登录的标记，false表示未登录
		String account = null;// 登录账号
		String ssid = null;// 这是一个标记，通过cookie拿到的一个判断用户该不该成功登录的标志

		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userKey")) {
					account = cookie.getValue();
				}

				if (cookie.getName().equals("ssid")) {
					ssid = cookie.getValue();
				}
			}
		}
		if (account != null && ssid != null) {
			md5Encrypt = CookieUtils.md5Encrypt(username);
			login = ssid.equals(md5Encrypt);
		}
		if (!login) {// login是false取反true，表示用户还没登录
//			用户第一次过来
			User user = userService.login(username, password);// 通过访问数据库，来判断用户名和密码是否都正确
			// userService.login()如果有用户返回User，不等于则返回null
			System.out.println("user:" + user);
			if (user != null) {// 表示登录成功
				expiredays = expiredays == null ? "" : expiredays;
				switch (expiredays) {
				case "7":
//					创建一个cookie对象，设置cookie对象的实时有效期为7天
					CookieUtils.createCookie(username, request, response, 7 * 24 * 60 * 60);
					break;
				case "30":
//					创建一个cookie对象，设置cookie对象的实时有效期为30天
					CookieUtils.createCookie(username, request, response, 30 * 24 * 60 * 60);
					break;
				case "100":
//					创建一个cookie对象，设置cookie对象的实时有效期为永久,Integer.MAX
					CookieUtils.createCookie(username, request, response, Integer.MAX_VALUE);
					break;

				default:
//					创建一个cookie对象，设置cookie对象的实时有效期为 -1，表示关闭浏览器cookie则失效
					CookieUtils.createCookie(username, request, response, -1);
					break;
				}
//				System.out.println("---接下来设置session---"+request.getSession());
				HttpSession session = request.getSession();
				session.setAttribute("user", CookieUtils.md5Encrypt(username));
//				进行登录时打印sessionid
//				System.out.println("登录时候的sessionID："+session.getId()+"，属性user："+session.getAttribute("user"));
//				登录成功，准许用户进入到portal.jsp页面
//				request.getRequestDispatcher("/jsp/portal.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/jsp/portal.jsp");
				//设置session
				
			} else {
				request.setAttribute("note", "用户名或密码错误");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/jsp/portal.jsp").forward(request, response);
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("login----");
//		删除cookie&session
		Cookie[] cookies = request.getCookies();
		if(cookies != null & cookies.length>0) {
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("userKey")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			if(cookie.getName().equals("ssid")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		}
		
		HttpSession session = request.getSession();
		System.out.println("用户session:"+session.getAttribute("user"));
		if(session != null) {
//			session.setAttribute("u", "liyan");
			System.out.println("新的?:"+session.isNew());
//			System.out.println(session.getValueNames());
			Enumeration<String> attributeNames = session.getAttributeNames();
			while(attributeNames.hasMoreElements()) {
				System.out.println("------");
				System.out.println("session:"+attributeNames.nextElement());
			}
			session.removeAttribute("user");
		}
		
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}
