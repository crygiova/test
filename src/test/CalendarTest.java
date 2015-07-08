package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {

		private static final String DATE_FORMAT_MM_dd_yyyy_HH_mm_ss = "MM.dd.yyyy:HH.mm.ss";
		 
		public static String addNMinutesToTime(Calendar date) {
			SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_MM_dd_yyyy_HH_mm_ss);
			int minutesToAdd = 5;
			System.out.println("Initial Time: " + df.format(date.getTime()));
			Calendar startTime = date;
			startTime.add(date.MINUTE, minutesToAdd);
			String dateStr = df.format(startTime.getTime());
			System.out.println("After Time : " + dateStr + "\n");
			return dateStr;
		}
	}

