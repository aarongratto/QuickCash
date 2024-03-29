package com.example.quickcash.payment;

/**
 * Author: Patrick Strongman (modified from Payment.java code by Nihir Parashkumar Shah from the
 * payment integration tutorial on Brightspace)
 * Date: 2020-12-04
 *
 * Code for the PayPal payment gateway page.
 */

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickcash.config.Config;
import com.example.quickcash.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class Payment extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE = 333;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    TextView paymentCostText;
    Button paymentPaymentButton;

    String paymentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        paymentAmount = getIntent().getStringExtra("Amount");

        paymentCostText = (TextView) findViewById(R.id.paymentCostText);
        paymentCostText.setText("$" + paymentAmount + "CAD");
        paymentPaymentButton = (Button) findViewById(R.id.paymentPaymentButton);
        paymentPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });
    }

    private void processPayment() {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)),
                "CAD", "Purchased Goods", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(
                        PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try{
                        String paymentDetails = confirmation.toJSONObject().toString();
                        Log.d("Details", paymentDetails);
                        startActivity(new Intent(this, PaymentReceipt.class)
                        .putExtra("PaymentDetails", paymentDetails)
                        .putExtra("Amount", paymentAmount));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected  void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}