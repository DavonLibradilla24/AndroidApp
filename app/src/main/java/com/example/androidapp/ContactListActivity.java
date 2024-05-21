package com.example.androidapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private Button buttonAddContact, buttonExportContacts, buttonBackFromDetails;
    private ListView listViewContacts;
    private ArrayList<Contact> contacts;
    private ArrayAdapter<String> contactsAdapter;
    private ArrayList<String> contactNames;

    private TextView textViewContactName, textViewContactPhone, textViewContactEmail, textViewContactAge;
    private ImageView iconCall, iconDelete;

    private static final int REQUEST_WRITE_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        buttonAddContact = findViewById(R.id.buttonAddContact);
        buttonExportContacts = findViewById(R.id.buttonExportContacts);
        listViewContacts = findViewById(R.id.listViewContacts);
        buttonBackFromDetails = findViewById(R.id.buttonBackFromDetails);


        contacts = new ArrayList<>();
        contactNames = new ArrayList<>();
        contactsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        listViewContacts.setAdapter(contactsAdapter);

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, AddContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        buttonExportContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportContacts();
            }
        });

        listViewContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = contacts.get(position);
            showContactDetails(contact);
        });

        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportContacts();
            } else {
                Toast.makeText(this, "Permission denied to write to external storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String email = data.getStringExtra("email");
            int age = data.getIntExtra("age", -1);
            Contact contact = new Contact(name, phone, email, age);
            contacts.add(contact);
            contactNames.add(name);
            contactsAdapter.notifyDataSetChanged();
        }
    }

    private void exportContacts() {
        File path = getExternalFilesDir(null);
        File file = new File(path, "contacts.txt");
        try {
            FileWriter writer = new FileWriter(file);
            for (Contact contact : contacts) {
                writer.write(contact.getName() + ", " + contact.getPhone() + ", " + contact.getEmail() + ", " + contact.getAge() + "\n");
            }
            writer.close();
            Toast.makeText(this, "Contacts exported to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "Failed to export contacts", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void showContactDetails(Contact contact) {
        setContentView(R.layout.activity_contact_details);

        // Reassign the view references for the contact details layout
        textViewContactName = findViewById(R.id.textViewContactName);
        textViewContactPhone = findViewById(R.id.textViewContactPhone);
        textViewContactEmail = findViewById(R.id.textViewContactEmail);
        textViewContactAge = findViewById(R.id.textViewContactAge);
        iconCall = findViewById(R.id.iconCall);
        iconDelete = findViewById(R.id.iconDelete);
        buttonBackFromDetails = findViewById(R.id.buttonBackFromDetails);

        textViewContactName.setText(contact.getName());
        textViewContactPhone.setText(contact.getPhone());
        textViewContactEmail.setText(contact.getEmail());
        textViewContactAge.setText(String.valueOf(contact.getAge()));

        iconCall.setOnClickListener(v -> {
            // Display a toast message when the call icon is clicked
            Toast.makeText(ContactListActivity.this, "Calling...", Toast.LENGTH_SHORT).show();
        });

        iconDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ContactListActivity.this);
            builder.setTitle("Delete Contact");
            builder.setMessage("Are you sure you want to delete this contact?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    contacts.remove(contact);
                    contactNames.remove(contact.getName());
                    contactsAdapter.notifyDataSetChanged();
                    switchToContactListView();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing, user canceled the operation
                }
            });
            builder.show();
        });


        buttonBackFromDetails.setOnClickListener(v -> switchToContactListView());
    }

    private void switchToContactListView() {
        setContentView(R.layout.activity_contact_list);

        // Reassign the view references for the contact list layout
        buttonAddContact = findViewById(R.id.buttonAddContact);
        buttonExportContacts = findViewById(R.id.buttonExportContacts);
        listViewContacts = findViewById(R.id.listViewContacts);

        contactsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactNames);
        listViewContacts.setAdapter(contactsAdapter);

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, AddContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        buttonExportContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportContacts();
            }
        });

        listViewContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = contacts.get(position);
            showContactDetails(contact);
        });

        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }
}
