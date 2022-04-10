package com.example.mobilprojectkvest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDateProfile extends AppCompatActivity {

    private EditText UpdateLoginProfile;
    private EditText UpdatePasswordProfile;

    private String Login;
    private String Password;

    private SQLiteDatabase db;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_date_profile);

        UpdateLoginProfile = findViewById(R.id.LoginUpdate);
        UpdatePasswordProfile = findViewById(R.id.PasswordUpdate);

        Button btn = findViewById(R.id.UpdateDataBTN);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnUpdateDataPut();
            }
        });

        dataBase = new DataBase(getApplicationContext());
        db = dataBase.getWritableDatabase();
    }

    public void OnUpdateDataPut() {
        try {
            Bundle arguments = getIntent().getExtras();

            Login = "Maksim";
            Password = "123";
            Toast.makeText(this, Login + " " + Password, Toast.LENGTH_SHORT).show();

            if (Login.equals(UpdateLoginProfile.getText().toString()) && Password.equals(UpdatePasswordProfile.getText().toString())) {
                Toast.makeText(this, "Вы не изменили данные", Toast.LENGTH_SHORT).show();
                return;
            }
            ContentValues contentValues = new ContentValues();

            contentValues.put("login", UpdateLoginProfile.getText().toString());
            contentValues.put("password", UpdatePasswordProfile.getText().toString());
            int updateCount = db.update(DataBase.TABLE_AUTH, contentValues, "id = ?", new String[] {arguments.get("idUser").toString()});
            Log.d("huy", "updated rows count = " + updateCount);
            Intent intent = new Intent(UpdateDateProfile.this, MenuPageActivity.class);
            dataBase.close();
            startActivity(intent);
        } catch (Exception exception) {
            Log.e("Index", "Получено исключение", exception);
        }
    }

    public void OnCloseProfile(View view) {
        Intent intent = new Intent(UpdateDateProfile.this, MenuPageActivity.class);
        startActivity(intent);
    }
}