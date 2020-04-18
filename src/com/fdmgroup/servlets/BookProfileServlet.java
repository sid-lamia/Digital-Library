package com.fdmgroup.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.dao.BookDaoJpaImpl;
import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.model.Book;

/**
 * Servlet implementation class BookProfileServlet
 */
@WebServlet("/bookprofile")
public class BookProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/BookProfile.jsp").forward(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("book");
		IBookDao bookDao = new BookDaoJpaImpl();
		Book book = bookDao.findById(Integer.parseInt(isbn));
		request.setAttribute("book", book);
		request.getRequestDispatcher("/WEB-INF/views/BookProfile.jsp").forward(request, response);

	}

}
