package com.example.quickcash;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTests {

    //tests that user's email is in valid form
    @Test
    public void validEmailTest(){
        EmailValidator e1 = new EmailValidator("");
        EmailValidator e2 = new EmailValidator("JohnDoe");
        EmailValidator e3 = new EmailValidator("JohnDoe@gmail.com");

        assertFalse(e1.validEmail());
        assertFalse(e2.validEmail());
        assertTrue(e3.validEmail());
    }

    //tests that username is in valid form
    @Test
    public void validUsernameTest(){
        UsernameValidator u1 = new UsernameValidator("");
        UsernameValidator u2 = new UsernameValidator("_@$!");
        UsernameValidator u3 = new UsernameValidator("JaneDoe123");

        assertFalse(u1.validUser());
        assertFalse(u2.validUser());
        assertTrue(u3.validUser());
    }
}
