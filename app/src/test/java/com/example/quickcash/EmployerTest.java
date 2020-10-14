package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployerTest {
    static String employerUsername = "Aaron";
    static String employerPassword = "pass";
    Employer employer;

    @Before
    public void setupUnit() {
        employer = new Employer();
        employer.login(employerUsername, employerPassword);
    }

    @Test
    public void testType() {
        assertEquals("employee", employer.getUserType(), "Wrong user type returned. " +
                "Expected: employee, returned: " + employer.getUserType());
    }

    @Test
    public void testUsername() {
        assertEquals(employerUsername, employer.getUsername(), "Wrong username returned. " +
                "Expected: " + employerUsername + ", returned: " + employer.getUsername());
    }

    @Test
    public void testLogin() {
        assertTrue(employer.isLoggedIn());
    }

    @Test
    public void testWrongInfo() {
        Employer employerTest = new Employer();
        employerTest.login("Partick","wordpass");
        assertFalse(employerTest.isLoggedIn());
    }

}