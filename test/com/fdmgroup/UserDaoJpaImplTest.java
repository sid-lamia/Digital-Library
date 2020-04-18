package com.fdmgroup;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.ProfileController;
import com.fdmgroup.controller.RegistrationController;
import com.fdmgroup.controller.UpdateDatabaseController;
import com.fdmgroup.dao.BookDaoJpaImpl;
import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.UserDaoJpaImpl;
import com.fdmgroup.model.User;
import com.fdmgroup.view.DashboardView;
import com.fdmgroup.view.HomeView;
import com.fdmgroup.view.ProfileView;
import com.fdmgroup.view.UpdateDatabaseView;

public class UserDaoJpaImplTest {
	private ProfileView mockpv;
	private ProfileController pc;
	private User mockUser;
	private User user;
	private AuthenticationController ac;
	private IBookDao bookDao;
	private IUserDao userDao;
	private IUserDao mockUserDao;
	private RegistrationController rc;
	private UpdateDatabaseController udc;
	private UpdateDatabaseView udv;
	private HomeView hv;
	private DashboardView dv;
	
	
	@Before
	public void setUp() throws Exception {
		hv = new HomeView();
		dv = new DashboardView();
		mockpv = mock(ProfileView.class);
		bookDao = new BookDaoJpaImpl();
		pc = new ProfileController(mockpv);
		//user = new User();
		mockUserDao = mock(UserDaoJpaImpl.class);
		rc = new RegistrationController();
		ac = new AuthenticationController(dv, hv, mockUserDao);
		userDao = new UserDaoJpaImpl();
		udv = new UpdateDatabaseView();
		udc = new UpdateDatabaseController(udv, bookDao, userDao);
		//rc.setUserDao(userDao);
	}

	@Test
	public void testDashboardViewCallsShowProfile() {
		pc.showProfile();
		verify(mockpv, times(1)).showProfileView();
	}
	
	@Test 
	public void testFindByUsernameWithStudent(){
		user = userDao.findByUsername("mbates");
		String actualOutput = user.getUsername();
		assertEquals(actualOutput, "mbates");
	}
	

	
}
	

	