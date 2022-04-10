package com.example.mobilprojectkvest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KvestTestValue extends AppCompatActivity {

    private EditText DescValue;
    private EditText testValue;

    private DataBase databaseSource;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kvest_test_value);

        DescValue = findViewById(R.id.ValueDesc);
        testValue = findViewById(R.id.Question);

        databaseSource = new DataBase(getApplicationContext());
        db = databaseSource.getWritableDatabase();
    }

    public void onClickCreateTest(View view) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("description", DescValue.getText().toString());
        contentValues.put("valueTest", testValue.getText().toString());
        db.update(DataBase.TABLE_TEST, contentValues, null, null);
        db.insert(DataBase.TABLE_TEST, null, contentValues);
        Home();
    }

    private void Home() {
        db.close();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}