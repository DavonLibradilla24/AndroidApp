package com.example.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText editTextName, editTextPhone, editTextEmail, editTextAge;
    private Button buttonSubmitContact, buttonBackFromAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSubmitContact = findViewById(R.id.buttonSubmitContact);
        buttonBackFromAdd = findViewById(R.id.buttonBackFromAdd)    ;

        buttonSubmitContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();
                String email = editTextEmail.getText().toString();
                String ageString = editTextAge.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || ageString.isEmpty()) {
                    Toast.makeText(AddContactActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageString);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddContactActivity.this, "Please enter a valid age", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (age < 0 || age > 120) {
                    Toast.makeText(AddContactActivity.this, "Please enter an age between 0 and 120", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("phone", phone);
                intent.putExtra("email", email);
                intent.putExtra("age", age);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        buttonBackFromAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
