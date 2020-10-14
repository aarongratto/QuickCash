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
        assertEquals("Wrong user type returned. " +
                "Expected: employee, returned: " + employee.getUserType(), "employee", employee.getUserType());
    }

    @Test
    public void testUsername() {
        assertEquals("Wrong username returned. " +
                "Expected: " + employeeUsername + ", returned: " + employee.getUsername(), employeeUsername, employee.getUsername());
    }

    @Test
    public void testLogin() {
        assertTrue(employee.isLoggedIn());
    }

    @Test
    public void testWrongInfo() {
        Employee employeeTest = new Employee();
        employeeTest.login("Partick","wordpass");
        assertFalse("Employee logged in with wrong info", employeeTest.isLoggedIn());
    }
}