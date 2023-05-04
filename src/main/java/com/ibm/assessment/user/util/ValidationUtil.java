package com.ibm.assessment.user.util;

public abstract class ValidationUtil {
	
	public static String validatePassword(String password) {
	    if (password.length() < 8) {
	        return "Password must be at least 8 characters long";
	    }
	    if (!password.matches(".*\\d.*")) {
	        return "Password must contain at least 1 number";
	    }
	    if (!password.matches(".*[A-Z].*")) {
	        return "Password must contain at least 1 capitalized letter";
	    }
	    if (!password.matches(".*[_#$%.].*")) {
	        return "Password must contain at least 1 special character in this set \"_ # $ % .\"";
	    }
	    return null; // Password is valid
	}

}
