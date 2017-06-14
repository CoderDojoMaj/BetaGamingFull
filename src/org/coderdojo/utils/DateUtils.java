package org.coderdojo.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
	
	static public Date fromString(String s){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		java.util.Date dateStr = null;
		try {
			dateStr = formatter.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
		return dateDB;
	}
	
	static public String toString(Date d){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		String dateStr = null;
		dateStr = formatter.format(d);
		System.out.println("SQL DATE TO STRING --> " + dateStr);
		return dateStr;
	}
	
	static public String toString(java.util.Date d){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
		String dateStr = null;
		dateStr = formatter.format(d);
		System.out.println("UTIL DATE TO STRING --> " + dateStr);
		return dateStr;
	}

}
