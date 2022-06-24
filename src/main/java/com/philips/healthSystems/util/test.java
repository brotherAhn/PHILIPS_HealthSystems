package com.philips.healthSystems.util;

import java.security.NoSuchAlgorithmException;

public class test {
	public static void main(String[] args) {
		SHA256 sh = new SHA256();
		String pw = "philips1234";
		try {
			pw = sh.password_SHA(pw);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(pw);
	}
}
