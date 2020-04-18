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
import com.fdmgroup.model.Reservation;
import com.fdmgroup.model.User;
import com.fdmgroup.util.MyLogger;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/reserve")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reservedBookIsbn = request.getParameter("reservebook");
		User currentUser = (User) request.getSession().getAttribute("loggedInUser");
		IBookDao bookDao = new BookDaoJpaImpl();
		List<Reservation> userBooks = bookDao.findByUser(currentUser);
		MyLogger.trace("booksize");
		Book updatedBook = bookDao.findById(Integer.parseInt(reservedBookIsbn));
		//a user is not allowed to borrow more that 3 books
		
		if(userBooks.size() <= 2){ 
			bookDao.reserve(updatedBook, currentUser);
			Book updatedBook2 = bookDao.findById(Integer.parseInt(reservedBookIsbn));
			request.setAttribute("book", updatedBook2);
			request.getRequestDispatcher("/WEB-INF/views/BookProfile.jsp").forward(request, response);	
		}
		else{
			request.setAttribute("cannotreserve", "You have already reserved 3 books, please return a book to reserve another one");
			request.setAttribute("book", updatedBook);
			request.getRequestDispatcher("/WEB-INF/views/BookProfile.jsp").forward(request, response);	
		}
	}

}
