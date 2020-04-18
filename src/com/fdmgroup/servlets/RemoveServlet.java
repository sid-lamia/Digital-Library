package com.fdmgroup.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdmgroup.dao.BookDaoJpaImpl;
import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.model.Book;

/**
 * Servlet implementation class RemoveServlet
 */
@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/UpdateBookSearch.jsp").forward(request, response);	

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String removedBookIsbn = request.getParameter("removebook");
		IBookDao bookDao = new BookDaoJpaImpl();
		Book removedBook = bookDao.findById(Integer.parseInt(removedBookIsbn));
		
		if(removedBook.getAvailability().equals("Available")){
			bookDao.remove(removedBook);
			request.getRequestDispatcher("/WEB-INF/views/UpdateBookSearch.jsp").forward(request, response);
		}
		else{ //cannot remove book if reserved
			request.setAttribute("cannotmsg", "This book is reserved by a user, cannot remove!");
			request.getRequestDispatcher("/WEB-INF/views/UpdateBookSearch.jsp").forward(request, response);

		}
	

	}

}
