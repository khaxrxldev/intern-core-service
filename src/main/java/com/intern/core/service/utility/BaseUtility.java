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
    
    public static String getGrade(Float totalMark) {
    	if (totalMark != null) {
    		if (totalMark >= 0 && totalMark <= 29) {
				return "F";
			} else if (totalMark >= 30 && totalMark <= 39) {
				return "E";
			} else if (totalMark >= 40 && totalMark <= 43) {
				return "D";
			} else if (totalMark >= 44 && totalMark <= 46) {
				return "D+";
			} else if (totalMark >= 47 && totalMark <= 49) {
				return "C-";
			} else if (totalMark >= 50 && totalMark <= 54) {
				return "C";
			} else if (totalMark >= 55 && totalMark <= 59) {
				return "C+";
			} else if (totalMark >= 60 && totalMark <= 64) {
				return "B-";
			} else if (totalMark >= 65 && totalMark <= 69) {
				return "B";
			} else if (totalMark >= 70 && totalMark <= 74) {
				return "B+";
			} else if (totalMark >= 75 && totalMark <= 79) {
				return "A-";
			} else if (totalMark >= 80 && totalMark <= 89) {
				return "A";
			} else if (totalMark >= 90 && totalMark <= 100) {
				return "A+";
			} else {
				return "";
			}
		} else {
	    	return "";
		}
    }
    
    public static String getPointer(Float totalMark) {
    	if (totalMark != null) {
    		if (totalMark >= 0 && totalMark <= 29) {
				return "0.00";
			} else if (totalMark >= 30 && totalMark <= 39) {
				return "0.67";
			} else if (totalMark >= 40 && totalMark <= 43) {
				return "1.00";
			} else if (totalMark >= 44 && totalMark <= 46) {
				return "1.33";
			} else if (totalMark >= 47 && totalMark <= 49) {
				return "1.67";
			} else if (totalMark >= 50 && totalMark <= 54) {
				return "2.00";
			} else if (totalMark >= 55 && totalMark <= 59) {
				return "2.33";
			} else if (totalMark >= 60 && totalMark <= 64) {
				return "2.67";
			} else if (totalMark >= 65 && totalMark <= 69) {
				return "3.00";
			} else if (totalMark >= 70 && totalMark <= 74) {
				return "3.33";
			} else if (totalMark >= 75 && totalMark <= 79) {
				return "3.67";
			} else if (totalMark >= 80 && totalMark <= 89) {
				return "4.00";
			} else if (totalMark >= 90 && totalMark <= 100) {
				return "4.00";
			} else {
				return "";
			}
		} else {
	    	return "";
		}
    }
}
