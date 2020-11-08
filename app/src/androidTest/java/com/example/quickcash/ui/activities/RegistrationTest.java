package com.example.quickcash.ui.activities;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcash.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.After;
import org.junit.AfterClass;
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

public class RegistrationTest {
    // Credentials that already exist
    static String defaultEmail = "default@quickcash.com";
    static String defaultUsername = "default";
    static String defaultPassword = "defaultPass123";

    // New user credentials
    static String newEmail = "newuser@quickcash.com";
    static String newUsername = "newuser";
    static String newPassword = "newPass123";

    private IdlingResource activityIdlingResource;


    // Setup

    @Rule
    public ActivityScenarioRule<Registration> activityScenarioRule =
            new ActivityScenarioRule<>(Registration.class);

    @Before
    public void setup() {
        // Get idling resources
        activityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<Registration>() {
            @Override
            public void perform(Registration activity) {
                activityIdlingResource = activity.getIdleResource();
                IdlingRegistry.getInstance().register(activityIdlingResource);
            }
        });
    }

    @After
    public void removeIdle() {
        if (activityIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(activityIdlingResource);
        }
    }

    @AfterClass
    public static void teardown() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null && user.getEmail().equals(newEmail)) {
            user.delete();
        }
    }

    // Tests

    @Test
    public void testNewCredentials() {
        enterEmail(newEmail);
        enterUsername(newUsername);
        enterPassword(newPassword);
        enterConfirmPassword(newPassword);

        onView(withId(R.id.registrationRegisterButton))
                .perform(click());

        onView(withId(R.id.registrationStatusMessage))
                .check(matches(withText("Registration success")));

    }

    @Test
    public void testOldCredentials() {
        enterEmail(defaultEmail);
        enterUsername(defaultUsername);
        enterPassword(defaultPassword);
        enterConfirmPassword(defaultPassword);

        onView(withId(R.id.registrationRegisterButton))
                .perform(click());

        onView(withId(R.id.registrationStatusMessage))
                .check(matches(withText("Registration failed")));
    }

    @Test
    public void testInvalidPasswordConfirm() {
        enterEmail(newEmail);
        enterUsername(newUsername);
        enterPassword(newPassword);
        enterConfirmPassword(newPassword + "1");

        onView(withId(R.id.registrationRegisterButton))
                .perform(click());

        onView(withId(R.id.registrationStatusMessage))
                .check(matches(withText("Passwords don't match")));
    }

    @Test
    public void testInvalidEmail() {
        enterEmail(newUsername);
        enterUsername(newUsername);
        enterPassword(newPassword);
        enterConfirmPassword(newPassword);

        onView(withId(R.id.registrationRegisterButton))
                .perform(click());

        onView(withId(R.id.registrationStatusMessage))
                .check(matches(withText("Invalid email")));
    }

    @Test
    public void testInvalidUsername() {
        enterEmail(newEmail);
        enterUsername(newUsername + "[]");
        enterPassword(newPassword);
        enterConfirmPassword(newPassword);

        onView(withId(R.id.registrationRegisterButton))
                .perform(click());

        onView(withId(R.id.registrationStatusMessage))
                .check(matches(withText("Invalid username")));
    }

    @Test
    public void testInvalidPassword() {
        enterEmail(newEmail);
        enterUsername(newUsername);
        enterPassword("pat");
        enterConfirmPassword(newPassword);

        onView(withId(R.id.registrationRegisterButton))
                .perform(click());

        onView(withId(R.id.registrationStatusMessage))
                .check(matches(withText("Invalid password")));
    }


    // Helper methods
    private void enterUsername(String username) {
        onView(withId(R.id.registrationUsernameText))
                .perform(click())
                .perform(typeText(username));
        closeSoftKeyboard();
    }

    private void enterEmail(String email) {
        onView(withId(R.id.registrationEmailText))
                .perform(click())
                .perform(typeText(email));
        closeSoftKeyboard();
    }

    private void enterPassword(String password) {
        onView(withId(R.id.registrationPasswordText))
                .perform(click())
                .perform(typeText(password));
        closeSoftKeyboard();
    }

    private void enterConfirmPassword(String password) {
        onView(withId(R.id.registrationConfirmPasswordText))
                .perform(click())
                .perform(typeText(password));
        closeSoftKeyboard();
    }
}