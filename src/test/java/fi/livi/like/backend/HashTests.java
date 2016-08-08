package fi.livi.like.backend;

import org.junit.Test;

import fi.livi.like.backend.data.HashUtils;

//This is not actually unit test. Just printing the results for strings to find out what are the results.
public class HashTests {

    @Test
    public void HashSDA1() {
        String pin = "1234";
        String imei = "123456789012345";
        String str = pin + imei;
        System.out.println(HashUtils.encryptString(str));
        System.out.println(HashUtils.encryptString(pin));
        System.out.println(HashUtils.encryptString(pin).length());
    }
}
