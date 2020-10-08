package com.example.quickcash;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTests {

    @Test
    public void validEmailTest(){
        EmailValidator e1 = new EmailValidator("");
        EmailValidator e2 = new EmailValidator("JohnDoe");
        EmailValidator e3 = new EmailValidator("JohnDoe@gmai.com");

        assertFalse(e1.validEmail());
        assertFalse(e2.validEmail());
        assertTrue(e3.validEmail());
    }
}
