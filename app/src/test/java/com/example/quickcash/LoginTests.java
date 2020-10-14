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

    //tests that password is of appropriate length
    @Test
    public void passwordLongEnoughTest() {
        PasswordValidator v1 = new PasswordValidator("Password");
        PasswordValidator v2 = new PasswordValidator("Test");
        PasswordValidator v3 = new PasswordValidator("CSCI3130");

        assertTrue(v1.isLongEnough());
        assertFalse(v2.isLongEnough());
        assertTrue(v3.isLongEnough());
    }


    //tests that password has both upper and lower case characters
    @Test
    public void caseCheckTest(){
        PasswordValidator v1 = new PasswordValidator("test");
        PasswordValidator v2 = new PasswordValidator("Test");
        PasswordValidator v3 = new PasswordValidator("TEST");
        PasswordValidator v4 = new PasswordValidator("");

        assertFalse(v1.caseCheck());
        assertTrue(v2.caseCheck());
        assertFalse(v3.caseCheck());
        assertFalse(v4.caseCheck());
    }

    //tests that password contains a special character
    @Test
    public void specialCheckTest(){
        PasswordValidator v1 = new PasswordValidator("test");
        PasswordValidator v2 = new PasswordValidator("Test!");
        PasswordValidator v3 = new PasswordValidator("TEST#");
        PasswordValidator v4 = new PasswordValidator("@$!_");

        assertTrue(v1.specialCheck());
        assertTrue(v2.specialCheck());
        assertFalse(v3.specialCheck());
        assertTrue(v4.specialCheck());
    }
}
