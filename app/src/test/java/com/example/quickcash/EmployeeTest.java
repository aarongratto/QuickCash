package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {
    static String employeeUsername = "Patrick";
    static String employeePassword = "password";
    Employee employee;

    @Before
    public void setupUnit() {
        employee = new Employee();
        employee.login(employeeUsername, employeePassword);
    }

    @Test
    public void testType() {
        assertEquals("employee", employee.getUserType(), "Wrong user type returned. " +
                "Expected: employee, returned: " + employee.getUserType());
    }

    @Test
    public void testUsername() {
        assertEquals(employeeUsername, employee.getUsername(), "Wrong username returned. " +
                "Expected: " + employeeUsername + ", returned: " + employee.getUsername());
    }

    @Test
    public void testLogin() {
        assertTrue(employee.isLoggedIn());
    }

    @Test
    public void testWrongInfo() {
        Employee employeeTest = new Employee();
        employeeTest.login("Partick","wordpass");
        assertFalse(employeeTest.isLoggedIn());
    }
}