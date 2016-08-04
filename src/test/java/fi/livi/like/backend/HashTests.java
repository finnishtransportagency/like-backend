package fi.livi.like.backend;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.junit.Test;

public class HashTests {

    //Example code for creating SHA-1 hashed string from a string
    //http://stackoverflow.com/questions/4895523/java-string-to-sha1
    
    @Test
    public void HashSDA1() {
        String pin = "1234";
        String imei = "123456789012345";
        String str = pin + imei;
        System.out.println(encryptString(str));
        System.out.println(encryptString(pin));
        System.out.println(encryptString(pin).length());
    }
    
    private static String encryptString(String anyString)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(anyString.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }    
}
