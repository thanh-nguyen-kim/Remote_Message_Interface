package com.rmi.DES;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class DesCipher {
	  Cipher ecipher;

	  Cipher dcipher;

	  public DesCipher(SecretKey key) throws Exception {
	    ecipher = Cipher.getInstance("DES");
	    dcipher = Cipher.getInstance("DES");
	    ecipher.init(Cipher.ENCRYPT_MODE, key);
	    dcipher.init(Cipher.DECRYPT_MODE, key);
	  }

	  public String encrypt(String str) throws Exception {
	    // Encode the string into bytes using utf-8
	    byte[] utf8 = str.getBytes("UTF8");

	    // Encrypt
	    byte[] enc = ecipher.doFinal(utf8);

	    // Encode bytes to base64 to get a string
	    return Base64.getEncoder().encodeToString(enc);
	  }

	  public String decrypt(String str) throws Exception {
	    // Decode base64 to get bytes
	    byte[] dec = Base64.getDecoder().decode(str);

	    byte[] utf8 = dcipher.doFinal(dec);

	    // Decode using utf-8
	    return new String(utf8, "UTF8");
	  }
	}
