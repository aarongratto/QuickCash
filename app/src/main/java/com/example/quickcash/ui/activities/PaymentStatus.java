package com.example.quickcash.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcash.Job.Job;
import com.example.quickcash.JobDatabase;
import com.example.quickcash.R;
import com.example.quickcash.payment.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentStatus extends AppCompatActivity {

    private Button payButton;
    private ListView listInProgress;
    private ListView listCompleted;
    private JobsAdapter adapter;
    private ArrayList<String> stringArray = new ArrayList<>();
    private static List<Job> jobsInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_status_payment);
        getUIElements();
        jobsInProgress = MainPage.getJobsInProgress();
        populateListInProgress();
        adapter.notifyDataSetChanged();
    }

    private void getUIElements() {
        payButton = (Button) findViewById(R.id.jobPayButton);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent payIntent = new Intent(getApplicationContext(), Payment.class);
                payIntent.putExtra("Amount", "30.30");
                startActivity(payIntent);
            }
        });
        listInProgress = findViewById(R.id.listView3);
        listCompleted = findViewById(R.id.listView2);
        adapter = new JobsAdapter();
        //listCompleted.setAdapter(adapter);
    }

    private void populateListInProgress(){
        for (int i = 0; i < jobsInProgress.size(); i++){
            Job thisJob = jobsInProgress.get(i);
            stringArray.add(thisJob.getJobTitle() +" - $" + thisJob.getJobWage() +"\n "
                    +thisJob.getJobType() +" - "  +thisJob.getJobLocation());

        }
        listInProgress.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class JobsAdapter extends BaseAdapter {
        private Context context;

        JobsAdapter() {
        }

        @Override
        public int getCount() { return jobsInProgress.size(); }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_row, null);

                JobsAdapter.ViewHolder viewHolder = new JobsAdapter.ViewHolder();
                viewHolder.tvPlace = rowView
                        .findViewById(R.id.textPlaceName);
                viewHolder.imgStatus = rowView
                        .findViewById(R.id.imageStatus);
                rowView.setTag(viewHolder);
            }

            JobsAdapter.ViewHolder holder = (JobsAdapter.ViewHolder) rowView.getTag();
            if (position < jobsInProgress.size()){
                Job thisJob = jobsInProgress.get(position);
                holder.tvPlace.setText(thisJob.getJobTitle() +" - $" + thisJob.getJobWage() +"\n "
                        +thisJob.getJobType() +" - "  +thisJob.getJobLocation());
            }
            return rowView;
        }

        class ViewHolder {
            TextView tvPlace;
            ImageView imgStatus;
        }
    }
}


