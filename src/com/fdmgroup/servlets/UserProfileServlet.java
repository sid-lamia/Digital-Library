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

/**
 * Servlet implementation class UserProfile
 */
@WebServlet("/userprofile")
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User currentUser = (User) request.getSession().getAttribute("loggedInUser"); //get current user
		request.setAttribute("loggedInUser", currentUser);
		
		IBookDao bookDao = new BookDaoJpaImpl();
		
		List<Reservation> borrowedBooks = bookDao.findByUser(currentUser); //find books reserved by current user
		
		int listSize = borrowedBooks.size(); //find how many books
		double totalFee = 0.0;
		int overdueCount = 0;
		for (Reservation book : borrowedBooks) { //for each book reserved
			Reservation updatedBook = bookDao.updateOverdueStatus(book); //find if it is overdue
			totalFee = totalFee + updatedBook.getOverdueFee();
			if(updatedBook.isOverdueStatus()){
				overdueCount++;
			}
		}
		request.setAttribute("listSize", listSize);
		request.setAttribute("totalFee", totalFee);
		request.setAttribute("overdueCount", overdueCount);
		request.getRequestDispatcher("/WEB-INF/views/UserProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
