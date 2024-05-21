package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ComputeAverageActivity extends AppCompatActivity {

    private EditText editTextMath, editTextFilipino, editTextScience, editTextMapeh, editTextEnglish;
    private Button buttonSubmitAverage, buttonBackFromCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_average);

        editTextMath = findViewById(R.id.editTextMath);
        editTextFilipino = findViewById(R.id.editTextFilipino);
        editTextScience = findViewById(R.id.editTextScience);
        editTextMapeh = findViewById(R.id.editTextMapeh);
        editTextEnglish = findViewById(R.id.editTextEnglish);
        buttonSubmitAverage = findViewById(R.id.buttonSubmitAverage);
        buttonBackFromCompute = findViewById(R.id.buttonBackFromCompute);

        buttonSubmitAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int math = Integer.parseInt(editTextMath.getText().toString());
                    int filipino = Integer.parseInt(editTextFilipino.getText().toString());
                    int science = Integer.parseInt(editTextScience.getText().toString());
                    int mapeh = Integer.parseInt(editTextMapeh.getText().toString());
                    int english = Integer.parseInt(editTextEnglish.getText().toString());

                    int average = (math + filipino + science + mapeh + english) / 5;

                    Intent intent = new Intent(ComputeAverageActivity.this, AverageResultActivity.class);
                    intent.putExtra("average", average);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(ComputeAverageActivity.this, "Please enter valid grades", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBackFromCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
