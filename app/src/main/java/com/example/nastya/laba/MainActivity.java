package com.example.nastya.laba;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Set <String> errorsSet = new HashSet <>();
    private TextView errorText;
    private EditText inputFirstName, inputLastName, inputEmail, inputPhone, inputPassword,
            inputPasswordReEnter;
    public Button submitButton,viewListButton;
    public String firstName, lastName, phoneNumber, email, password, passwordReEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputFirstName = findViewById(R.id.first_name);
        inputLastName = findViewById(R.id.last_name);
        inputEmail = findViewById(R.id.email);
        inputPhone = findViewById(R.id.phone);
        inputPassword = findViewById(R.id.password);
        inputPasswordReEnter = findViewById(R.id.confirm_password);
        errorText = findViewById(R.id.result);
        submitButton = findViewById(R.id.submit_button);
        viewListButton = findViewById(R.id.view_list_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userValidation();
                cleanUserData(view);
            }
        });
        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClientActivity.class);
                startActivity(intent);
            }
        });
    }

    private void userValidation() {
        firstNameValidation();
        lastNameValidation();
        emailValidation();
        phoneValidation();
        passwordValidation();
        errorText.setText(errorsSet.toString());
    }

    private void firstNameValidation() {
        firstName = inputFirstName.getText().toString();
        if (firstName.isEmpty() || firstName.length() < 3) {
            errorsSet.add(getString(R.string.short_first_name_error));
        } else {
            errorsSet.remove(getString(R.string.short_first_name_error));
        }
    }

    private void lastNameValidation() {
        lastName = inputLastName.getText().toString();
        if (lastName.isEmpty() || lastName.length() < 3) {
            errorsSet.add(getString(R.string.short_last_name_error));
        } else {
            errorsSet.remove(getString(R.string.short_last_name_error));
        }
    }

    private void emailValidation() {
        email = inputEmail.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorsSet.add(getString(R.string.invalid_email_error));
        } else {
            errorsSet.remove(getString(R.string.invalid_email_error));
        }
    }

    private void phoneValidation() {
        phoneNumber = inputPhone.getText().toString();
        if (phoneNumber.isEmpty() || !Patterns.PHONE.matcher(phoneNumber).matches()) {
            errorsSet.add(getString(R.string.invalid_phone_error));
        } else {
            errorsSet.remove(getString(R.string.invalid_phone_error));
        }
    }

    private void passwordValidation() {
        password = inputPassword.getText().toString();
        passwordReEnter = inputPasswordReEnter.getText().toString();
        if (password.isEmpty() || password.length() < 5 || password.length() > 12) {
            errorsSet.add(getString(R.string.short_password_error));
        } else {
            errorsSet.remove(getString(R.string.short_password_error));
        }

        if (passwordReEnter.isEmpty() || !passwordReEnter.equals(password)) {
            errorsSet.add(getString(R.string.not_equals_password_error));
        } else {
            errorsSet.remove(getString(R.string.not_equals_password_error));
        }
    }

    private void cleanUserData(View view) {
        if (errorsSet.isEmpty()) {
            SharedPreferences sharedPref = getSharedPreferences(Const.DATA_KEY,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(Const.FIRST_NAME_KEY, firstName);
            editor.putString(Const.LAST_NAME_KEY, lastName);
            editor.putString(Const.PHONE_NUMBER_KEY, phoneNumber);
            editor.apply();
            inputFirstName.setText("");
            inputLastName.setText("");
            inputPhone.setText("");
            inputEmail.setText("");
            inputPassword.setText("");
            inputPasswordReEnter.setText("");
            Snackbar.make(view, R.string.snackbar_valid_info, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }
}