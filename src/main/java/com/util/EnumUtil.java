package com.util;

import com.dto.Gender;
import com.dto.UserRole;

public class EnumUtil {
	
	public static UserRole stringToUserRole(String str) {
		str = str.trim();
		if (str == null || "".equals(str)) {
			return null;
		}
		UserRole role = null;
		switch (str.toUpperCase()) {
			case "ADMIN":
				role = UserRole.ADMIN;
				break;

			case "USER":
				role = UserRole.USER;
				break;
		}
		return role;
	}
	
	public static Gender stringToGender(String str) {
		str = str.trim();
		if (str == null || "".equals(str)) {
			return null;
		}
		Gender gender = null;
		switch (str.toUpperCase()) {
			case "MALE":
				gender = Gender.MALE;
				break;

			case "FEMALE":
				gender = Gender.FEMALE;
				break;
		}
		return gender;
	} 
}
