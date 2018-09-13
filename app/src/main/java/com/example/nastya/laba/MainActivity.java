package com.example.nastya.laba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText firstName, lastName, email, phone, password, confirmPassword;
    private String name, last_name, emailAddress, phoneNumber, pass, confirm;
    public Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn();
    }

    public void signIn() {
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    public void submit() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Submit has failed", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validate() {
        boolean valid = true;
        if (name.isEmpty() || name.length() > 32) {
            firstName.setError(getString(R.string.FirstName));
            valid = false;
        }
        if (last_name.isEmpty() || last_name.length() > 32) {
            lastName.setError(getString(R.string.LastName));
            valid = false;
        }

        if (emailAddress.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            email.setError(getString(R.string.Email));
            valid = false;
        }
        if (phoneNumber.isEmpty() || Patterns.PHONE.matcher(phoneNumber).matches()) {
            phone.setError(getString(R.string.Phone));
            valid = false;
        }
        if (pass.isEmpty()) {
            password.setError(getString(R.string.Password));
            valid = false;
        }
        if (confirm.isEmpty()) {
            confirmPassword.setError(getString(R.string.Confirm));
            valid = false;
        }
        return valid;
    }

    private void initialize() {
        name = firstName.getText().toString().trim();
        last_name = lastName.getText().toString().trim();
        emailAddress = email.getText().toString().trim();
        phoneNumber = phone.getText().toString().trim();
        pass = password.getText().toString().trim();
        confirm = confirmPassword.getText().toString().trim();
    }
}