package com.poc.CustomerService.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class SecurityConfiguration {
	
	static String key = "uhdgJhE3Hjdb=hjq";
	
	public static String encrypt(String plainText) {
		try {
			// generate random 16-byre initialization vector
			byte initVector[] = new byte[16];
//			(new Random()).nextBytes(initVector);
			IvParameterSpec iv = new IvParameterSpec(initVector);
			
			// prep the key
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			
			// prep the AES cipher
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			
			// Encode the plaintext as array of bytes
			byte[] cipherbytes = cipher.doFinal(plainText.getBytes());
			
			// build the output message initVector + cipherbytes -> base64
			byte[] messagebytes = new byte[initVector.length + cipherbytes.length];
			
			System.arraycopy(initVector, 0, messagebytes, 0, 16);
			System.arraycopy(cipherbytes, 0, messagebytes, 16, cipherbytes.length);
			
			//return the cipherbytes as a Base64-encoded string
			return Base64.getEncoder().encodeToString(messagebytes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(String cipherText) {
		try {
			byte[] cipherbytes = Base64.getDecoder().decode(cipherText);
			
			byte[] initVector = Arrays.copyOfRange(cipherbytes, 0, 16);
			
			byte[] messagebytes = Arrays.copyOfRange(cipherbytes, 16, cipherbytes.length);
			
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			
			// convert the ciphertext to Base-64 encoded String back to bytes and then decrypt
			byte[] byte_array = cipher.doFinal(messagebytes);
			
			return new String(byte_array, StandardCharsets.UTF_8);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
