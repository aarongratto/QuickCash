package com.example.quickcash.ui.startpage;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcash.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class LoginTest {
    static String defaultEmail = "default@quickcash.com";
    static String defaultPassword = "defaultPass123";

    private IdlingResource idleResource;

    @Rule
    public ActivityScenarioRule<Login> myActivityRule
            = new ActivityScenarioRule<>(Login.class);

    @Before
    public void setup() {
        myActivityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<Login>() {
            @Override
            public void perform(Login activity) {
                idleResource = activity.getIdlingResource();
                IdlingRegistry.getInstance().register(idleResource);
            }
        });
    }

    @After
    public void teardown() {
        if (idleResource != null) {
            IdlingRegistry.getInstance().unregister(idleResource);
        }
    }


    @Test
    public void testLoginSuccess() {
        // type in username and password
        typeEmail(defaultEmail);
        typePassword(defaultPassword);

        onView(withId(R.id.buttonLogin))
                .perform(click());

        onView(withId(R.id.labelStatusMessage))
                .check(matches(withText("Login success, as default@quickcash.com")));
    }

    @Test
    public void testLoginFail() {
        // type in username and password
        typeEmail(defaultEmail);
        typePassword(defaultPassword + "wrong");

        onView(withId(R.id.buttonLogin))
                .perform(click());

        onView(withId(R.id.labelStatusMessage))
                .check(matches(withText("Login failed")));
    }

    public void typeEmail(String emailAddress) {
        onView(withId(R.id.textEmail))
                .perform(click())
                .perform(typeText(emailAddress));
        closeSoftKeyboard();
    }

    public void typePassword(String password) {
        onView(withId(R.id.textPassword))
                .perform(click())
                .perform(typeText(password));
        closeSoftKeyboard();
    }
}