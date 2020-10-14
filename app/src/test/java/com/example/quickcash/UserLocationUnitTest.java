package com.example.quickcash;

import org.junit.Test;
import static org.junit.Assert.*;



public class UserLocationUnitTest {
    // need to enter your own Location
    final double YOUR_LATITUDE = 0;
    final double YOUR_LONGITUDE = 0;
    final String YOUR_ADDRESS = "South Park Street, Halifax, NS, Canada";


    @Test
    public void returnsCollectLocation () {
        UserLocation test = new UserLocation();
        assertEquals(test.getLatitude(), YOUR_LATITUDE, 0.001);
        assertEquals(test.getLongitude(), YOUR_LONGITUDE, 0.002);
    }

    @Test
    public void returnCollectAddress() {
        UserLocation test = new UserLocation();
        assertEquals(test.getAddress(), YOUR_ADDRESS);
    }
}