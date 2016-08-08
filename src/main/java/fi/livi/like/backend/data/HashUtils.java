package fi.livi.like.backend.data;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Code for creating SHA-1 hashed string from a string
//http://stackoverflow.com/questions/4895523/java-string-to-sha1
public class HashUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HashUtils.class);
    
    public static String encryptString(String anyString)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(anyString.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException nsae)
        {
            LOG.error("Failed to encrypt string.", nsae);
        }
        catch(UnsupportedEncodingException uee)
        {
            LOG.error("Failed to encrypt string.", uee);
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
