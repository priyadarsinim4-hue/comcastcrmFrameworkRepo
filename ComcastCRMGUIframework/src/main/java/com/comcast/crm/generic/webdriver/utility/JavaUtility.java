package com.comcast.crm.generic.webdriver.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random random = new Random();
		int randomnumber= random.nextInt(5000);
		return randomnumber ;
	}
	
	public String getSystemDateYYYYDDMM() {
		
		Date dateobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date ;
	}
	
	public String getRequiredDateYYYYDDMM(int days) {
		
	Date dateobj = new Date();
		SimpleDateFormat srf = new SimpleDateFormat("yyyy-MM-dd");
		srf.format(dateobj);
		Calendar cal = srf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqdate = srf.format(cal.getTime());
		System.out.println(reqdate);
		return  reqdate ;
		 
	}
	
	
}
