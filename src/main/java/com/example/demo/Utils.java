package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {
	public static String today() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
	
	public static String tmr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return sdf.format(calendar.getTime());
	}
	
}
