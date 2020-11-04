package com.example.quickcash;

import com.example.quickcash.validators.EmailValidator;

import org.junit.Test;
import static org.junit.Assert.*;

public class JobTest {

    //testing for Hiring class that extends Job
    @Test
    public void validHiringJobTest(){
        Hiring h1 = new Hiring("Chef", "Truro", 15.20);
        Hiring h2 = new Hiring("Software Engineer", "Halifax", 20.56);
        Hiring h3 = new Hiring("Carpenter", "Montreal", 25.00);

        assertEquals("Chef", h1.getJobTitle());
        assertEquals("Software Engineer", h2.getJobTitle());
        assertEquals("Carpenter", h3.getJobTitle());

        assertEquals("Truro", h1.getJobLocation());
        assertEquals("Halifax", h2.getJobLocation());
        assertEquals("Montreal", h3.getJobLocation());

        assertEquals(15.20, h1.getJobWage(), 0);
        assertEquals(20.56, h2.getJobWage(), 0);
        assertEquals(25.00, h3.getJobWage(), 0);
    }

    //testing for LookingForWork class that extends Job
    @Test
    public void validLookingForHireJobTest(){
        LookingForWork l1 = new LookingForWork("Labor", "Antigonish", 12.55);
        LookingForWork l2 = new LookingForWork("Volunteer", "Sydney", 0.00);
        LookingForWork l3 = new LookingForWork("Web Designer", "Toronto", 21.00);

        assertEquals("Labor", l1.getJobTitle());
        assertEquals("Volunteer", l2.getJobTitle());
        assertEquals("Web Designer", l3.getJobTitle());

        assertEquals("Antigonish", l1.getJobLocation());
        assertEquals("Sydney", l2.getJobLocation());
        assertEquals("Toronto", l3.getJobLocation());

        assertEquals(12.55, l1.getJobWage(), 0);
        assertEquals(0.00, l2.getJobWage(), 0);
        assertEquals(21.00, l3.getJobWage(), 0);
    }

}
