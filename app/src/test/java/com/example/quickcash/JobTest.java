package com.example.quickcash;

import com.example.quickcash.validators.EmailValidator;

import org.junit.Test;
import static org.junit.Assert.*;

public class JobTest {

    //testing for Hiring class that extends Job
    @Test
    public void validHiringJobTest(){
        //creating three Hiring job objects
        Hiring hiringObject1 = new Hiring("Chef", "Truro", 15.20);
        Hiring hiringObject2 = new Hiring("Software Engineer", "Halifax", 20.56);
        Hiring hiringObject3 = new Hiring("Carpenter", "Montreal", 25.00);

        //assert that the object can return the jobTitle correctly
        assertEquals("Chef", hiringObject1.getJobTitle());
        assertEquals("Software Engineer", hiringObject2.getJobTitle());
        assertEquals("Carpenter", hiringObject3.getJobTitle());

        //assert that the object can return the jobLocation correctly
        assertEquals("Truro", hiringObject1.getJobLocation());
        assertEquals("Halifax", hiringObject2.getJobLocation());
        assertEquals("Montreal", hiringObject3.getJobLocation());

        //assert that the object can return the jobWage correctly
        assertEquals(15.20, hiringObject1.getJobWage(), 0);
        assertEquals(20.56, hiringObject2.getJobWage(), 0);
        assertEquals(25.00, hiringObject3.getJobWage(), 0);

        //assert that the object can return the jobType correctly
        assertEquals("Hiring", hiringObject1.getJobType());
        assertEquals("Hiring", hiringObject2.getJobType());
        assertEquals("Hiring", hiringObject3.getJobType());
    }

    //testing for LookingForWork class that extends Job
    @Test
    public void validLookingForHireJobTest(){
        //creating three LookingForWork job objects
        LookingForWork lookingWorkObject1 = new LookingForWork("Labor", "Antigonish", 12.55);
        LookingForWork lookingWorkObject2 = new LookingForWork("Volunteer", "Sydney", 0.00);
        LookingForWork lookingWorkObject3 = new LookingForWork("Web Designer", "Toronto", 21.00);

        //assert that the object can return the jobTitle correctly
        assertEquals("Labor", lookingWorkObject1.getJobTitle());
        assertEquals("Volunteer", lookingWorkObject2.getJobTitle());
        assertEquals("Web Designer", lookingWorkObject3.getJobTitle());

        //assert that the object can return the jobLocation correctly
        assertEquals("Antigonish", lookingWorkObject1.getJobLocation());
        assertEquals("Sydney", lookingWorkObject2.getJobLocation());
        assertEquals("Toronto", lookingWorkObject3.getJobLocation());

        //assert that the object can return the jobWage correctly
        assertEquals(12.55, lookingWorkObject1.getJobWage(), 0);
        assertEquals(0.00, lookingWorkObject2.getJobWage(), 0);
        assertEquals(21.00, lookingWorkObject3.getJobWage(), 0);

        //assert that the object can return the jobType correctly
        assertEquals("Looking for work", lookingWorkObject1.getJobType());
        assertEquals("Looking for work", lookingWorkObject2.getJobType());
        assertEquals("Looking for work", lookingWorkObject3.getJobType());
    }

}
