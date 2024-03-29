package com.renhenet.util;

public class PasswordUtils {

	public static final int LOWEST_PASS_RATE = 0;

	public static final int COMMON_PASS_RATE = 40;

	public static final int HEIGHT_PASS_RATE = 60;

	public static double getPasswdSecurityRate(String password) {
		return getPasswdSecurityRate(password, null);
	}

	public static double getPasswdSecurityRate(String password, String loginId) {
		double points = 0; // total points
		double j = 0; // counter (points)
		int jj = 0;
		String n = "0123456789"; // numbers
		String a = "abcdefghijklmnopqrstuvwxyz";// alpha letters
		int u = 0; // upper case count
		int l = 0; // lower case count
		String an = a + n + a.toUpperCase(); // alpha numeric

		if (loginId != null) {
			String passwordUpper = password.toUpperCase();
			if (password.indexOf(loginId) >= 0
					|| passwordUpper.indexOf(loginId.toUpperCase()) >= 0) {
				return 0;
			}
		}
		// Length of password - 6 characters recomended
		if (password.length() < 6) {
			return 0;
		}
		points = points + ((password.length()) * 2.5);
		if (points > 20) {
			points = 20;
		}

		// Amount of Numbers - 2 recomended, 3 prefered
		j = 0;
		for (int i = 0; i < password.length(); i++) {
			if (n.indexOf(password.substring(i, i + 1)) != -1) {
				j = j + 6.67;
				jj++;
			}
		}
		if (j > 20) {
			j = 20;
		}
		if (jj == password.length()) {
			j = j - 10;
		}
		points = points + j;

		// Non-Repeating characters - 8 recomended
		j = 0;
		for (int i = 0; i < password.length() - 1; i++) {
			if (password.substring(i, i + 1) != password
					.substring(i + 1, i + 2)) {
				j = j + 2.86;
			}
		}
		if (j > 20) {
			j = 20;
		}
		points = points + j;

		jj = 0;
		// Pairs of mixed-case - 2 recomended
		for (int i = 0; i < password.length(); i++) {
			if (a.indexOf(password.substring(i, i + 1)) != -1) {
				l++;
				jj++;
			}
			if (a.toUpperCase().indexOf(password.substring(i, i + 1)) != -1) {
				u++;
				jj++;
			}
		}
		if (u > l) {
			j = l * 10;
		} else {
			j = u * 10;
		}
		if (j > 20) {
			j = 20;
		}
		if (jj == password.length()) {
			j = j - 10;
		}
		points = points + j;

		// non-alpha-numeric characters - 2 recomended
		j = 0;
		for (int i = 0; i < password.length(); i++) {
			if (j < 20) {
				if (an.indexOf(password.substring(i, i + 1)) == -1) {
					j = j + 10;
				}
			}
		}// for
		if (j > 20) {
			j = 20;
		}
		points = points + j;
		// System.err.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr " + points);
		return (points);
	}
}
