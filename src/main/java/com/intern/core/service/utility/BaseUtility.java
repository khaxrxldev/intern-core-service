package com.intern.core.service.utility;

import java.util.List;
import java.util.UUID;

public class BaseUtility {
	
	/**
	 * @implNote check if object NULL
	 * @param Object
	 * @return Boolean
	 */
	public static boolean isObjectNull(Object object) {
        if (object != null && getString(object).length() > 0) {
            return false;
        } else {
            return true;
        }
    }
	
	/**
	 * @implNote check if object NOT NULL
	 * @param Object
	 * @return Boolean
	 */
	public static boolean isObjectNotNull(Object object) {
        if (object != null && getString(object).length() > 0) {
            return true;
        } else {
            return false;
        }
    }
	
	/**
	 * @implNote get string from object
	 * @param Object
	 * @return string
	 */
	public static String getString(Object object) {
		String emptyString = "";
		
		if (object != null) {
			return object.toString().trim();
		} else {
			return emptyString;
		}
	}

	/**
	 * @implNote check if string NULL or EMPTY
	 * @param String
	 * @return boolean
	 */
	public static boolean isBlank(String string) {
		if (string != null && !string.trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @implNote check if string NOT NULL or NOT EMPTY
	 * @param String
	 * @return Boolean
	 */
	public static boolean isNotBlank(String string) {
		return !BaseUtility.isBlank(string);
	}
	
	/**
	 * @implNote generate UID for primary key
	 * @return UID string
	 */
	public static String generateId() {
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString().toUpperCase();
	}

	/**
	 * @implNote check if list NULL or EMPTY
	 * @param List
	 * @return Boolean
	 */
    public static Boolean isListNull(List<?> list) {
        if (list != null && getListSize(list) > 0) {
            return false;
        } else {
            return true;
        }
    }

	/**
	 * @implNote get size of list
	 * @param List
	 * @return Integer
	 */
    public static Integer getListSize(List<?> list) {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }
}
