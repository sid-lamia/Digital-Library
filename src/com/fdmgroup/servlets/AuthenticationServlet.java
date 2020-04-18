package com.fdmgroup.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.UserDaoJpaImpl;
import com.fdmgroup.model.User;
import com.fdmgroup.util.MyLogger;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/authenticate")
public class AuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/Dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("email");
		String password = request.getParameter("password");
		
		IUserDao userDao = new UserDaoJpaImpl();
		User foundUser = userDao.findByUsername(userName);
		
		if(foundUser != null && foundUser.getPassword().equals(password)){
			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", foundUser);
			MyLogger.trace("User login");
			request.getRequestDispatcher("/WEB-INF/views/Dashboard.jsp").forward(request, response);
		}
		else {
			request.setAttribute("errorMsg", "Email or password is incorrect");
			MyLogger.trace("incorrect email or password entered");
			request.getRequestDispatcher("/WEB-INF/views/Home.jsp").forward(request,response);
		}
	}

}
