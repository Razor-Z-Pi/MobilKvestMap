package com.example.mobilprojectkvest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String login;
    private String password;
    private String testLogin;
    private String testPassword;

    private EditText loginTxt;
    private EditText passwordTxt;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testLogin = "foo";
        testPassword = "foo";
        loginTxt = findViewById(R.id.loginTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginButton = findViewById(R.id.loginBtn);

        View.OnClickListener loginClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnLoginClick();
            }
        };

        loginButton.setOnClickListener(loginClickListener);

    }

    private void OnLoginClick()
    {
        login = loginTxt.getText().toString();
        password = passwordTxt.getText().toString();

        if (login.equals("") || password.equals(""))
        {
            Toast toast = Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {

            //надо чтоб данные из базы сравнивались :( Java
            if (login.equals(testLogin) && password.equals(testPassword))
            {
                //Intent intent = new Intent(this, MenuPage.class);
                //startActivity(intent);
                Toast toast1 = Toast.makeText(this, "Auth succeded", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }
    }
}