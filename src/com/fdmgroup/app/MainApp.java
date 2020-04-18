package com.fdmgroup.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import com.fdmgroup.controller.AdvancedSearchController;
import com.fdmgroup.controller.AuthenticationController;
import com.fdmgroup.controller.HomeController;
import com.fdmgroup.controller.ProfileController;
import com.fdmgroup.controller.RegistrationController;
import com.fdmgroup.controller.ReserveController;
import com.fdmgroup.controller.SearchController;
import com.fdmgroup.controller.UpdateDatabaseController;
import com.fdmgroup.dao.BookDaoJpaImpl;
import com.fdmgroup.dao.IBookDao;
import com.fdmgroup.dao.IUserDao;
import com.fdmgroup.dao.UserDaoJpaImpl;
import com.fdmgroup.view.AdvancedSearchView;
import com.fdmgroup.view.DashboardView;
import com.fdmgroup.view.HomeView;
import com.fdmgroup.view.ProfileView;
import com.fdmgroup.view.RegistrationView;
import com.fdmgroup.view.SearchView;
import com.fdmgroup.view.UpdateDatabaseView;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainApp {
	private static Logger logger = LogManager.getLogger("ProjectLogger"); 

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		IUserDao userDao = new UserDaoJpaImpl();
		IBookDao bookDao = new BookDaoJpaImpl(); 
		
		//test
		//Views
		HomeView hv = new HomeView(scanner);
		DashboardView dv = new DashboardView(scanner);
		RegistrationView rv = new RegistrationView(scanner);
		SearchView sv = new SearchView(scanner); 
		AdvancedSearchView asv = new AdvancedSearchView(scanner);
		UpdateDatabaseView udv = new UpdateDatabaseView(scanner);
		ProfileView pv = new ProfileView(bookDao);
		logger.trace("tracing test"); 

		//Controllers
		HomeController hc = new HomeController();
		AuthenticationController ac = new AuthenticationController();
		RegistrationController rc = new RegistrationController(); 
		SearchController sc = new SearchController(); 
		AdvancedSearchController asc = new AdvancedSearchController(); 
		ReserveController resCont = new ReserveController();
		UpdateDatabaseController udc = new UpdateDatabaseController();
		ProfileController pc = new ProfileController();
		
		hc.setHomeView(hv);
		ac.setDashboardView(dv);
		ac.setHomeView(hv);
		ac.setUserDao(userDao);
		rc.setRegistrationView(rv);
		rc.setHomeView(hv);
		rc.setUserDao(userDao);
		sc.setSearchView(sv);
		sc.setBookDao(bookDao);
		asc.setBookDao(bookDao);
		asc.setAsv(asv);
		resCont.setBookDao(bookDao);
		udc.setBookDao(bookDao);
		udc.setUdv(udv);
		udc.setUserDao(userDao);
		pc.setPv(pv);
		
		hv.setAuthenticationController(ac);
		hv.setHomeController(hc);
		hv.setRegistrationController(rc);
		dv.setAuthenticationController(ac);
		dv.setSearchController(sc);
		dv.setAsc(asc);
		dv.setUdc(udc);
		dv.setPc(pc);
		rv.setHomeController(hc);
		rv.setRegistrationController(rc);
		sv.setSearchController(sc);
		sv.setRescont(resCont);
		asv.setResCont(resCont);
		asv.setAsc(asc);
		udv.setUdc(udc);
		
			hc.showHome();

		
		scanner.close();
	}
}