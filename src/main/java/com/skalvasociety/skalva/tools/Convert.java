package com.skalvasociety.skalva.tools;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Convert {

	public Date stringToDate(String sDate) {
		if(sDate == null || sDate.equals("")){
			return null;
		}
		SimpleDateFormat formatter;
		// Pour certaines date, seul l'année est donnée
		if(sDate.length() == 4){
			formatter = new SimpleDateFormat("yyyy");
		}else{
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}		 
	     Date date = null; 
		 try {
			date = formatter.parse(sDate);
		} catch (java.text.ParseException e) {		
			e.printStackTrace();
		}	
	    return date;
	}
}
