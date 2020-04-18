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
 * Servlet implementation class AdvancedSearchServlet
 */
@WebServlet("/advancedSearch")
public class AdvancedSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvancedSearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/AdvancedSearch.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get search criteria from hidden input in form
		String searchCriteria = request.getParameter("searchCriteria");
		//selecting appropriate search method
		if (searchCriteria.equals("genre")){
			String genre = request.getParameter("searchTitle");
			IBookDao bookDao = new BookDaoJpaImpl();
			List<Book> searchResult = bookDao.findByGenre(genre);
			request.setAttribute("searchResult", searchResult);
			request.getRequestDispatcher("/WEB-INF/views/AdvSearchResult.jsp").forward(request, response);;
		}
		
		else if (searchCriteria.equals("author")){
			String author = request.getParameter("searchTitle");
			IBookDao bookDao = new BookDaoJpaImpl();
			List<Book> searchResult = bookDao.findByAuthor(author);
			request.setAttribute("searchResult", searchResult);
			request.getRequestDispatcher("/WEB-INF/views/AdvSearchResult.jsp").forward(request, response);;
		}
		else if (searchCriteria.equals("keyword")){
			String keyword = request.getParameter("searchTitle");
			IBookDao bookDao = new BookDaoJpaImpl();
			List<Book> searchResult = bookDao.findByKeyword(keyword);
			request.setAttribute("searchResult", searchResult);
			request.getRequestDispatcher("/WEB-INF/views/AdvSearchResult.jsp").forward(request, response);;
		}
		//if no option selected, then search by title
		else {
			String searchTitle = request.getParameter("searchTitle");
			IBookDao bookDao = new BookDaoJpaImpl();
			List<Book> searchResult = bookDao.findByTitle(searchTitle);
			request.setAttribute("searchResult", searchResult);
			request.getRequestDispatcher("/WEB-INF/views/AdvSearchResult.jsp").forward(request, response);;
		}
	}
		

}
