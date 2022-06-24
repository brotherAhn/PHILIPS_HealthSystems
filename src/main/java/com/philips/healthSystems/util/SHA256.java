package com.philips.healthSystems.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public String password_SHA(String pw) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(pw.getBytes());
		String hex = String.format("%064x", new BigInteger(1, md.digest()));
		return hex;
	}
}
