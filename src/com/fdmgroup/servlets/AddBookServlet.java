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
import com.fdmgroup.util.MyLogger;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/AddBook.jsp").forward(request, response);;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting parameters from form
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = String.valueOf(request.getParameter("isbn"));
		String genre = request.getParameter("genre");
		String pages = String.valueOf(request.getParameter("pages"));
		String publishedYear = request.getParameter("year");
		String summary = request.getParameter("summary");
		
		//checking if form fields are null, only summary field as null is allowed
		if(title.length() !=0 && author.length() != 0 && isbn.length() != 0 && genre.length() != 0 && pages.length() != 0
				&& publishedYear.length() != 0){
			
			Book newBook = new Book(Integer.parseInt(isbn), title, author, genre,Integer.parseInt(pages), publishedYear, summary);
			IBookDao userDao = new BookDaoJpaImpl();
			userDao.create(newBook);
			MyLogger.trace("New book added");
			request.getRequestDispatcher("/WEB-INF/views/AddBook.jsp").forward(request, response);
		}
		else{
			request.setAttribute("emptyfieldmsg", "Please complete all the fields to add a book");
			MyLogger.error("Book add attempted with null fields");
			request.getRequestDispatcher("/WEB-INF/views/AddBook.jsp").forward(request, response);
		}
	}
}

