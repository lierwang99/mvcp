package cn.liyan.mvcp.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShopController
 */
@WebServlet(description = "sssss", urlPatterns = { "*.pdo" })
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(1, servletPath.length() - 4);
		System.out.println("Shop调用的方法:" + servletPath);
		try {
			Method method = this.getClass().getDeclaredMethod(servletPath, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void shopping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("pname");
		System.out.println(name);
		request.setAttribute("pname",name);
		request.getRequestDispatcher("/jsp/productDetails.jsp").forward(request, response);
	}
	
	private void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("pname");
		HttpSession session = request.getSession();
		List<String> products = (ArrayList<String>)session.getAttribute("car");
		if(products == null) {
			products = new ArrayList<String>();
		}
		products.add(name);
		session.setAttribute("car",products);
		response.setCharacterEncoding("UTF-8");

//		response.getWriter().write("添加成功");
		response.sendRedirect(request.getContextPath()+"/jsp/shoppingCar.jsp");
	}

}
