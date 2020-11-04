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


}
