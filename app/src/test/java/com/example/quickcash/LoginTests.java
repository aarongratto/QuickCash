package com.example.quickcash;

import com.example.quickcash.validators.EmailValidator;
import com.example.quickcash.validators.PasswordValidator;
import com.example.quickcash.validators.UsernameValidator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTests {

    //tests that user's email is in valid form
    @Test
    public void validEmailTest(){
        EmailValidator e1 = new EmailValidator("");
        EmailValidator e2 = new EmailValidator("JohnDoe");
        EmailValidator e3 = new EmailValidator("JohnDoe@gmail.com");

        assertFalse("Invalid Email. Reason: blank email", e1.validEmail());
        assertFalse("Invalid Email. Reason: not an email address", e2.validEmail());
        assertTrue("Valid Email.", e3.validEmail());
    }

    //tests that username is in valid form
    @Test
    public void validUsernameTest(){
        UsernameValidator u1 = new UsernameValidator("");
        UsernameValidator u2 = new UsernameValidator("_@$!");
        UsernameValidator u3 = new UsernameValidator("JaneDoe123");

        assertFalse("Invalid Username. Reason: blank username", u1.validUser());
        assertFalse("Invalid Username. Reason: cannot contain special characters", u2.validUser());
        assertTrue("Valid Username", u3.validUser());
    }

    //tests that password is of appropriate length
    @Test
    public void passwordLongEnoughTest() {
        PasswordValidator v1 = new PasswordValidator("Password");
        PasswordValidator v2 = new PasswordValidator("Test");
        PasswordValidator v3 = new PasswordValidator("CSCI3130");

        assertTrue("Password is long enough", v1.isLongEnough());
        assertFalse("Password is not long enough", v2.isLongEnough());
        assertTrue("Password is not long enough", v3.isLongEnough());
    }


    //tests that password has both upper and lower case characters
    @Test
    public void caseCheckTest(){
        PasswordValidator v1 = new PasswordValidator("test");
        PasswordValidator v2 = new PasswordValidator("Test");
        PasswordValidator v3 = new PasswordValidator("TEST");
        PasswordValidator v4 = new PasswordValidator("");

        assertFalse("Password missing Uppercase", v1.caseCheck());
        assertTrue("Password passes caseCheck", v2.caseCheck());
        assertFalse("Password missing Lowercase", v3.caseCheck());
        assertFalse("Password cannot be blank", v4.caseCheck());
    }

    //tests that password contains a special character
    @Test
    public void specialCheckTest(){
        PasswordValidator v1 = new PasswordValidator("test");
        PasswordValidator v2 = new PasswordValidator("Test!");
        PasswordValidator v3 = new PasswordValidator("TEST#");
        PasswordValidator v4 = new PasswordValidator("@$!_");

        assertTrue("Password passes specialCheck", v1.specialCheck());
        assertTrue("Password passes specialCheck", v2.specialCheck());
        assertFalse("Not a valid special character", v3.specialCheck());
        assertTrue("Password passes specialCheck", v4.specialCheck());
    }

    //tests that user is already logged in
    @Test
    public void alreadyLoggedInTest(){
        Employer e1 = new Employer();
        Employer e2 = new Employer();
        Employee e3 = new Employee();
        Employee e4 = new Employee();

        e1.login("Aaron", "pass");
        e3.login("Patrick", "password");

        assertTrue("Already logged in", e1.isLoggedIn());
        assertFalse("Not logged in", e2.isLoggedIn());
        assertTrue("Already logged in", e3.isLoggedIn());
        assertFalse("Not logged in", e4.isLoggedIn());
    }

    //tests that user has made too many login attempts (i.e., 3)
    @Test
    public void tooManyLoginsTest(){
        int loginAttempts = 0;

        Employer e1 = new Employer();

        while(loginAttempts < 3){
            e1.login("", ""); //pass bad login credentials
            if (e1.isLoggedIn()){
                break;
            }
            loginAttempts++;
        }

        assertEquals("Max login attempts reached", 3, loginAttempts);
    }
}
