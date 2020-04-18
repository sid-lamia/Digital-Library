package com.fdmgroup.app;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateApp {
	public static void main(String[] args) {
		Date date1 = new GregorianCalendar(2019, Calendar.DECEMBER, 10).getTime();
		Date date2 = new Date();	

		System.out.println(date1.getTime());
		System.out.println(date2.getTime());
		
		long diff = (date1.getTime() - date2.getTime());
		int noOfDaysBetween = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println(diff);
		System.out.println(noOfDaysBetween);
	}
}
