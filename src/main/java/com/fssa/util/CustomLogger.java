package com.fssa.util;

public class CustomLogger {
	private CustomLogger() {
		super();
	}
	private static boolean isDebugEnabled = true;

	public static void info(String message) {
		System.out.println(message);
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
