package com.efficientproject.util;

import java.util.Scanner;

public class IntegerChecker {

	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}
	public static boolean isInteger(String s, int radix) {
	    Scanner sc = new Scanner(s.trim());
	    if(!sc.hasNextInt(radix)) return false;
	    sc.nextInt(radix);
	    return !sc.hasNext();
	}

}
