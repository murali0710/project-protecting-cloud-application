package com.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class AES {
	static Cipher cipher;
	public static String encrypt(String plainText, SecretKey secretKey)
			throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		System.out.println("Plain Text--->"+plainText);
		System.out.println("Scretkey AES--->"+secretKey);
		System.out.println("Plain Text byte--->"+plainTextByte);
		cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}
	
	public static String decrypt(String encryptedText, SecretKey secretKey)
			throws Exception {
		cipher = Cipher.getInstance("AES");
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

}
