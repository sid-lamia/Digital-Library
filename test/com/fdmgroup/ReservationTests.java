package com.fdmgroup;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fdmgroup.model.Reservation;

public class ReservationTests {
	private Reservation reservation;
	
	
	@Before
	public void setUp() throws Exception {
		reservation = new Reservation();
	}

	@Test
	public void testIsOverDueStatusMethodTrueCondition() {
		Date date1 = new Date();
		Date date2 = new GregorianCalendar(2019, Calendar.AUGUST, 11).getTime();
		reservation.setBorrowDate(date1);
		reservation.setReturnDate(date2);
		boolean actualOutput = Reservation.isOverdueStatus(reservation);
		assertTrue(actualOutput);
	}
	@Test
	public void testIsOverDueStatusMethodFalseCondition() {
		Date date1 = new Date();
		Date date2 = new GregorianCalendar(2019, Calendar.AUGUST, 30).getTime();
		reservation.setBorrowDate(date1);
		reservation.setReturnDate(date2);
		boolean actualOutput = Reservation.isOverdueStatus(reservation);
		assertFalse(actualOutput);
	}
	@Test
	public void testIsOverDueStatusMethodSameDateCondition() {
		Date date1 = new Date();
		reservation.setBorrowDate(date1);
		reservation.setReturnDate(date1);
		boolean actualOutput = Reservation.isOverdueStatus(reservation);
		assertFalse(actualOutput);
	}
	@Test
	public void testCalculateOverdueFeeMethodTrueCondition() {
		Date date1 = new Date();
		Date date2 = new GregorianCalendar(2019, Calendar.AUGUST, 11).getTime();
		reservation.setBorrowDate(date1);
		reservation.setReturnDate(date2);
		double actualOutput = Reservation.calculateOverdueFee(reservation);
		assertEquals(4.00, actualOutput, 0.01);
	}
	@Test
	public void testCalculateOverdueFeeMethodFalseCondition() {
		Date date1 = new Date();
		Date date2 = new GregorianCalendar(2019, Calendar.AUGUST, 30).getTime();
		reservation.setBorrowDate(date1);
		reservation.setReturnDate(date2);
		double actualOutput = Reservation.calculateOverdueFee(reservation);
		assertEquals(0.00, actualOutput, 0.01);
	}
	@Test
	public void testCalculateOverdueFeeMethodAnotherMonthFalseCondition() {
		Date date1 = new Date();
		Date date2 = new GregorianCalendar(2019, Calendar.DECEMBER, 30).getTime();
		reservation.setBorrowDate(date1);
		reservation.setReturnDate(date2);
		double actualOutput = Reservation.calculateOverdueFee(reservation);
		assertEquals(0.00, actualOutput, 0.01);
	}
	@Test
	public void testCalculateOverdueFeeMethodAnotherMonthTrueCondition() {
		Date date1 = new Date();
		Date date2 = new GregorianCalendar(2019, Calendar.JUNE, 30).getTime();
		reservation.setBorrowDate(date1);
		reservation.setReturnDate(date2);
		double actualOutput = Reservation.calculateOverdueFee(reservation);
		assertEquals(46.00, actualOutput, 0.01);
	}

}
