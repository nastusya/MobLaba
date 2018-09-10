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
    private Button submitButton;
    private String name, last_name, emailAddress, phoneNumber, pass, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn();
    }

    public void signIn() {
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        submitButton = (Button) findViewById(R.id.submit_button);
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
        } else {
            onSubmitSuccess();
        }
    }

    public void onSubmitSuccess() {
    }

    public boolean validate() {
        boolean valid = true;
        if (name.isEmpty() || name.length() > 32) {
            firstName.setError("Please enter valid Name");
            valid = false;
        }
        if (last_name.isEmpty() || last_name.length() > 32) {
            lastName.setError("Please enter valid Last Name");
            valid = false;
        }

        if (emailAddress.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            email.setError("Please enter valid Email Address");
            valid = false;
        }
        if (phoneNumber.isEmpty() || Patterns.PHONE.matcher(phoneNumber).matches()) {
            phone.setError("Please enter valid Phone");
            valid = false;
        }
        if (pass.isEmpty()) {
            password.setError("Please enter valid Password");
            valid = false;
        }
        if (confirm.isEmpty()) {
            confirmPassword.setError("Please check your Password one more");
            valid = false;
        }
        return valid;
    }

    public void initialize() {
        name = firstName.getText().toString().trim();
        last_name = lastName.getText().toString().trim();
        emailAddress = email.getText().toString().trim();
        phoneNumber = phone.getText().toString().trim();
        pass = password.getText().toString().trim();
        confirm = confirmPassword.getText().toString().trim();

    }
}