package com.example.mobilprojectkvest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class History extends AppCompatActivity {

    private ListView listDB;
    private Button btnClose;
    private Button btnDelete;
    private TextView textView;
    private Button btnShow;
    private EditText editText;

    private DataBase databaseSource;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        editText = findViewById(R.id.IdDelete);

        btnClose = findViewById(R.id.historyClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnCloseHistory();
            }
        });

        btnShow = findViewById(R.id.historyPush2);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // открываем подключение
                    db = databaseSource.getReadableDatabase();
                    //получаем данные из бд в виде курсора
                    cursor = db.rawQuery("SELECT * FROM " + DataBase.TABLE_KVEST + ";", null);

                    if (cursor.moveToFirst()) {
                        String[] from = new String[] {"_id", "namePoint", "X", "Y"};
                        // создаем адаптер, передаем в него курсор
                        userAdapter = new SimpleCursorAdapter(History.this, android.R.layout.two_line_list_item,
                                cursor, from, new int[]{ android.R.id.text1, android.R.id.text2 }, 0);
                        listDB.setAdapter(userAdapter);
                    }
                } catch (Exception exception) {
                    Toast.makeText(History.this, exception.toString(), Toast.LENGTH_SHORT).show();
                }
                textView.setText("" + cursor.getCount());
            }
        });

        btnDelete = findViewById(R.id.DeleteButton);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.close();
                db = databaseSource.getWritableDatabase();
                Toast.makeText(History.this, "Должно что-то удалиться", Toast.LENGTH_SHORT).show();
                db.delete(DataBase.TABLE_KVEST, "_id = ?", new String[]{String.valueOf(editText.getText())});
            }
        });

        listDB = findViewById(R.id.list);
        textView = findViewById(R.id.ShowDataTX);

        databaseSource = new DataBase(getApplicationContext());
    }


    public void OnCloseHistory() {
        Intent intent = new Intent(this, MenuPageActivity.class);
        startActivity(intent);
    }

}