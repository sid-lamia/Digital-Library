package com.fdmgroup.servlets;

import java.io.IOException;
import java.util.ArrayList;
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

/**
 * Servlet implementation class Show
 */
@WebServlet("/showallreserved")
public class ShowAllReserved extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllReserved() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IBookDao bookDao = new BookDaoJpaImpl();
		List<Book> foundBooks = bookDao.findAllReserved(); //find all reserved books
		
		for (Book book : foundBooks) { //for every reserved book, check if it is overdue
			int isbn = book.getIsbn();

			if(book != null && book.getAvailability().equals("Not Available")){ //if book is reserved get reservation details
				Reservation res = book.getReserveStatus();
				Reservation updatedRes = bookDao.updateOverdueStatus(res);
				book.setReserveStatus(updatedRes);
			}
		}
		request.setAttribute("foundBook", foundBooks);
		request.getRequestDispatcher("/WEB-INF/views/UpdateBook.jsp").forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
