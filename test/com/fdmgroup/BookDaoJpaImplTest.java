package com.fdmgroup;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.hibernate.cache.internal.TimestampsCacheDisabledImpl;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.controller.AdvancedSearchController;
import com.fdmgroup.controller.CancelReserveController;
import com.fdmgroup.controller.ReserveController;
import com.fdmgroup.controller.SearchController;
import com.fdmgroup.controller.UpdateDatabaseController;
import com.fdmgroup.dao.BookDaoJpaImpl;
import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.model.Book;
import com.fdmgroup.model.User;
import com.fdmgroup.view.AdvancedSearchView;
import com.fdmgroup.view.DashboardView;
import com.fdmgroup.view.SearchView;
import com.fdmgroup.view.UpdateDatabaseView;

public class BookDaoJpaImplTest {
	private SearchController sc; 
	private AdvancedSearchController asc;
	private ReserveController rc;
	private UpdateDatabaseController udc;
	private UpdateDatabaseView udv;
	private BookDaoJpaImpl mockBookDaoImpl;
	private IBookDao mockBookDao;
	private IUserDao mockUserDao;
	private Book mockBook;
	private List<Book> sr;
	private SearchView mockSV;
	private AdvancedSearchView mockAsv;
	private DashboardView mockDV;
	private UpdateDatabaseView mockUdv;
	private User mockUser;
	
	@Before
	public void setUp() throws Exception {
		mockBookDaoImpl = mock(BookDaoJpaImpl.class);
		mockBookDao = mock(IBookDao.class);
		mockSV = mock(SearchView.class);
		mockAsv = mock(AdvancedSearchView.class);
		mockDV = mock(DashboardView.class);
		mockUdv = mock(UpdateDatabaseView.class);
		mockUserDao = mock(IUserDao.class);
		sr = new ArrayList<Book>();
		sc = new SearchController(mockBookDao, sr, mockSV);
		asc = new AdvancedSearchController(mockBookDao, sr , mockAsv);
		rc = new ReserveController();
		rc.setBookDao(mockBookDao);	
		udc = new UpdateDatabaseController(mockUdv, mockBookDao, mockUserDao);
		udv = new UpdateDatabaseView();

	}

	@Test
	public void testAdvacedSearchView(){
		asc.showAdvancedSearch();
		verify(mockAsv, times(1)).advancedSearchOption();
	}

	@Test
	public void testReserve(){
		mockUser = mock(User.class);
		mockBook = mock(Book.class);
		rc.reserveBook(mockBook, mockUser);
		verify(mockBookDao, times(1)).reserve(mockBook, mockUser);
	}
	@Test
	public void testAddBookCall(){
		udc.addBook();
		verify(mockUdv, times(1)).showAddOptions();
	}
	
	@Test
	public void testDeleteBookCall(){
		udc.deleteBook();
		verify(mockUdv, times(1)).showDeleteOptions();
	}	
	@Test
	public void testUpdateBookCall(){
		udc.updateBook();
		verify(mockUdv, times(1)).showUpdateOptions();
	}
	@Test
	public void testDeleteStudentCall(){
		udc.deleteStudent();
		verify(mockUdv, times(1)).showStudentDeleteOptions();
	}

	


}
