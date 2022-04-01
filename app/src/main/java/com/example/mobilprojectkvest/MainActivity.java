package com.example.mobilprojectkvest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String login;
    private String password;

    private EditText loginTxt;
    private EditText passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginTxt = findViewById(R.id.loginTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
    }

    private void OnLoginClick(View view)
    {
        login = loginTxt.getText().toString();
        password = passwordTxt.getText().toString();

        if (login == "" || password == "")
        {
            Toast toast = Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}