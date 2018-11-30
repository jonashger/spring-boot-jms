package br.com.jonashger.jms.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class CryptoUtil {

	private static String strkey = "Blowfish";
	private static Base64 base64 = new Base64(true);

	// encrypt using blowfish algorithm
	public static String encrypt(String data) throws Exception {

		SecretKeySpec key = new SecretKeySpec(strkey.getBytes("UTF8"), "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		return base64.encodeToString(cipher.doFinal(data.getBytes("UTF8")));

	}

	// decrypt using blow fish algorithm
	public static String decrypt(String encrypted) throws Exception {
		byte[] encryptedData = Base64.decodeBase64(encrypted);
		SecretKeySpec key = new SecretKeySpec(strkey.getBytes("UTF8"), "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decrypted = cipher.doFinal(encryptedData);
		return new String(decrypted);

	}
}
