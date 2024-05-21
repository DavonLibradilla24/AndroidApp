package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonComputeAverage, buttonContacts, buttonOpenBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonComputeAverage = findViewById(R.id.buttonComputeAverage);
        buttonContacts = findViewById(R.id.buttonContacts);
        buttonOpenBrowser = findViewById(R.id.buttonOpenBrowser);  // Find the new button by its ID

        buttonComputeAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ComputeAverageActivity.class);
                startActivity(intent);
            }
        });

        buttonContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                startActivity(intent);
            }
        });

        buttonOpenBrowser.setOnClickListener(new View.OnClickListener() {  // Set OnClickListener for the new button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpenBrowserActivity.class);
                startActivity(intent);
            }
        });
    }
}
