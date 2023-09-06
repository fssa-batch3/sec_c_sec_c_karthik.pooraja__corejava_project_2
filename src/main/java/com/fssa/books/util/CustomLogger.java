package com.fssa.books.util;

import com.fssa.books.model.Book;

public class CustomLogger {
	private CustomLogger() {
		super();
	}

	private static boolean isDebugEnabled = true;

	public static void info(Object e) {
		System.out.println(e);
	}

	public static void debug(String message) {
		if (isDebugEnabled) {
			System.out.println("[DEBUG] " + message);
		}
	}

	public static void setDebugEnabled(boolean enabled) {
		isDebugEnabled = enabled;
	}
}
