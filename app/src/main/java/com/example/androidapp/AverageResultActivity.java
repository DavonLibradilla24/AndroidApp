package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AverageResultActivity extends AppCompatActivity {

    private TextView textViewAverageResult;
    private Button buttonBackFromResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average_result);

        textViewAverageResult = findViewById(R.id.textViewAverageResult);
        buttonBackFromResult = findViewById(R.id.buttonBackFromResult);

        int average = getIntent().getIntExtra("average", 0);
        textViewAverageResult.setText("Average: " + average);

        buttonBackFromResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
