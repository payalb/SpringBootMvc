package com.util;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		Date date = (Date) getValue();
		return date.toString();
	}

	@Override
	public void setAsText(String text) throws java.lang.IllegalArgumentException {
		SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
		try {
			setValue(new Date(format.parse(text).getTime()));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Unable to format the date.");
		}
	}
	
}
