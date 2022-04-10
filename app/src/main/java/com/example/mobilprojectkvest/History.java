package com.example.mobilprojectkvest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class History extends AppCompatActivity {

    private ListView listDB;
    private Button btnClose;

    private DataBase databaseSource;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnClose = findViewById(R.id.historyClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnCloseHistory();
            }
        });

        listDB = findViewById(R.id.list);

        databaseSource = new DataBase(getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        // открываем подключение
        db = databaseSource.getReadableDatabase();
        //получаем данные из бд в виде курсора
        cursor = db.rawQuery("SELECT * FROM " + DataBase.TABLE_KVEST + ";", null);

        String text = "";


        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Точек нету", Toast.LENGTH_SHORT).show();
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            text += cursor.getString(0) + "\n" + cursor.getString(1) + "\n" + cursor.getString(2) + "\n" + cursor.getString(3) + "\n" + cursor.getString(4) + "\n";
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        String[] array = new String[] {};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_2, array);

        listDB.setAdapter(adapter);
    }

    public void OnCloseHistory() {
        db.close();
        cursor.close();
        Intent intent = new Intent(this, MenuPageActivity.class);
        startActivity(intent);
    }

}