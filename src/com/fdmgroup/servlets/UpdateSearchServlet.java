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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updatesearch")
public class UpdateSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/UpdateBookSearch.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this method is invoked in the update database page when an isbn search is made
		String isbn = request.getParameter("isbn");
		int isbnInt;
		if (isbn.equals("")){ //if the search field is empty
			isbnInt = 0; 
		}
		else{
			isbnInt = Integer.parseInt(isbn); //change isbn from string to int
		}
		IBookDao bookDao = new BookDaoJpaImpl();
		List<Book> foundBookList = new ArrayList<>();
		Book foundBook =  bookDao.findById(isbnInt);
		if(foundBook != null && foundBook.getAvailability().equals("Not Available")){ //if book is reserved get reservation details
			Reservation res = foundBook.getReserveStatus();
			Reservation updatedRes = bookDao.updateOverdueStatus(res);
			foundBook.setReserveStatus(updatedRes);
		}
		foundBookList.add(foundBook);
		if(foundBookList.get(0) == null){
			request.setAttribute("nosuchbookmsg", "There are no books in the database with that ISBN");
			request.setAttribute("foundBook", foundBookList);
		}
		else{
			request.setAttribute("foundBook", foundBookList);
		}
		request.getRequestDispatcher("/WEB-INF/views/UpdateBook.jsp").forward(request, response);
	}

}
