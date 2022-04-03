package com.example.mobilprojectkvest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    private EditText loginTxt;
    private EditText passwordTxt;
    private Button loginButton;
    private Button RegisterButton;

    private DataBase databaseSource;
    private SQLiteDatabase db;
    private Cursor Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginTxt = findViewById(R.id.loginTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginButton = findViewById(R.id.loginBtn);
        RegisterButton = findViewById(R.id.RegisterBTN);

        View.OnClickListener loginClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Open DB
                db = databaseSource.getReadableDatabase();
                OnLoginClick(db);
            }
        };

        View.OnClickListener RegisterClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                OnRegister();
            }

        };

        loginButton.setOnClickListener(loginClickListener);
        RegisterButton.setOnClickListener(RegisterClickListener);

        databaseSource = new DataBase(getApplicationContext());
    }

    private void OnLoginClick(SQLiteDatabase db)
    {
        login = loginTxt.getText().toString();
        password = passwordTxt.getText().toString();

        // Получаем данные
        Auth = db.rawQuery("SELECT * FROM " + databaseSource.TABLE_AUTH + ";", null);

        try {
            if (login.equals("") || password.equals("")) {
                Toast toast = Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (Auth.moveToFirst()) {
                    for (int i = 0; i < Auth.getCount(); i++) {
                        String loginAuth = Auth.getString(1);
                        String passwordAuth = Auth.getString(2);
                        if (login.equals(loginAuth) && password.equals(passwordAuth)) {
                            Toast toast1 = Toast.makeText(this, "Auth succeded", Toast.LENGTH_SHORT);
                            toast1.show();
                            return;
                        }
                    }
                    Toast Warning = Toast.makeText(this, "Такого пользователя нет", Toast.LENGTH_SHORT);
                    Warning.show();
                    Auth.close();
                    db.close();
                    return;
                } else {
                    Toast Warning = Toast.makeText(this, "Что-то не так", Toast.LENGTH_SHORT);
                    Warning.show();
                    return;
                }
            }
        } catch (Exception exception) {
            Toast error = Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT);
            error.show();
        }
    }

    private void OnRegister()
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}