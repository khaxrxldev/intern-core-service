package com.intern.core.service.utility;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtility {

	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		if (dateToConvert != null) {
		    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		} else {
			return null;
		}
	}
	
	public static Timestamp asTimeStamp(LocalDateTime localDateTime) {
		if (localDateTime != null) {
			return Timestamp.valueOf(localDateTime);
		} else {
			return null;
		}
	}
}
