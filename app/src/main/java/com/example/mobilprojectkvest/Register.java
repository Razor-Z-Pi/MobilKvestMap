package com.example.mobilprojectkvest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private String Login;
    private String Password;
    private String Repeat;

    private EditText LoginText;
    private EditText PasswordText;
    private EditText PasswordTextRepeat;

    private DataBase databaseSource;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        LoginText = findViewById(R.id.LoginText);
        PasswordText = findViewById(R.id.PasswordText);
        PasswordTextRepeat = findViewById(R.id.PasswordTextRepeat);

        databaseSource = new DataBase(getApplicationContext());
        db = databaseSource.getWritableDatabase();
    }

    public void onRegisterData(View view) {
        Login = LoginText.getText().toString();
        Password = PasswordText.getText().toString();
        Repeat = PasswordTextRepeat.getText().toString();
        if (Login.equals("") || Password.equals("") || Repeat.equals("")) {
            Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            if (Repeat.equals(PasswordText.getText().toString())) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("login",Login);
                contentValues.put("password", Password);
                db.update(DataBase.TABLE_AUTH, contentValues, null, null);
                db.insert(DataBase.TABLE_AUTH, null, contentValues);
                Home();
            } else {
                Toast.makeText(this, "Пароль не совпадает!!!", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception exception) {
            Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void Home() {
        db.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onHome(View view) {
        Home();
    }


}