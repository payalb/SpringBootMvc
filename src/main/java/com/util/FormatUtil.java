package com.util;

public class FormatUtil {

	public static boolean strIsInteger(String str) {
		try { 
	        Integer.parseInt(str); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
}
