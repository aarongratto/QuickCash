package com.example.quickcash.payment;

/**
 * Author: Patrick Strongman (modified from the code written by Nihir Pareshkumar Shah for the
 * PaymentStatus.java code from the payment integration tutorial on Brightspace)
 * Date: 2020-12-04
 *
 * Used to get the status of a PayPal transaction and display the results in a receipt.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.R;
import com.example.quickcash.ui.activities.MainPage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PaymentReceipt extends AppCompatActivity {
    Intent intent;

    TextView receiptIDText;
    TextView receiptAmountText;
    TextView receiptStatusText;

    PaymentModel data;

    Button receiptMainPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_receipt);

        initUIElements();
        initReceiptInfo();

        showDetails(intent.getStringExtra("Amount"));
    }

    private void initUIElements() {
        receiptIDText = findViewById(R.id.receiptIDText);
        receiptAmountText = findViewById(R.id.receiptAmountText);
        receiptStatusText = findViewById(R.id.receiptStatusText);

        receiptMainPageButton = findViewById(R.id.receiptMainPageButton);
        receiptMainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMain();
            }
        });
    }

    private void startMain() {
        Intent mainIntent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(mainIntent);
    }

    private void initReceiptInfo() {
        intent = getIntent();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        data = gson.fromJson(intent.getStringExtra("PaymentDetails"), PaymentModel.class);
    }

    private void showDetails(String paymentAmount) {
        receiptIDText.setText("Payment ID: " + data.getResponse().getId());
        receiptStatusText.setText("Status: " + data.getResponse().getState());
        receiptAmountText.setText("Amount transferred: $" + paymentAmount + "CAD");
    }
}