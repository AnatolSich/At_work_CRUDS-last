package crypto;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Cryptor {

    Cipher encipher;
    Cipher decipher;

    public Cryptor(SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        encipher = Cipher.getInstance("AES");
        decipher = Cipher.getInstance("AES");
        encipher.init(Cipher.ENCRYPT_MODE, secretKey);
        decipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    public String encryptor(String in)  {
        byte[] utf8 = new byte[0];
        try {
            utf8 = in.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] enc = new byte[0];
        try {
            enc = encipher.doFinal(utf8);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new sun.misc.BASE64Encoder().encode(enc);
    }

    public String decryptor(String code) throws UnsupportedEncodingException {
        byte[] dec = new byte[0];
        try {
            dec = new sun.misc.BASE64Decoder().decodeBuffer(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] utf8 = new byte[0];
        try {
            utf8 = decipher.doFinal(dec);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(utf8, "UTF8");
    }
}
