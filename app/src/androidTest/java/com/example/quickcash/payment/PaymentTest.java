package com.example.quickcash.payment;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcash.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class PaymentTest {
    ActivityScenario<Payment> activityScenario;

    @Test
    public void testPayment() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), Payment.class);
        intent.putExtra("Amount", "50.50");
        activityScenario = ActivityScenario.launch(intent);

        onView(withId(R.id.paymentCostText))
                .check(matches(withText("$50.50CAD")));
    }
}