package com.example.mobilprojectkvest;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kvestDB.db"; // Навзвание БД
    private static final int SCHEMA = 3; // Версия БД
    static final String TABLE_AUTH = "Authorization";
    static final String TABLE_USER = "User";
    static final String TABLE_KVEST = "Kvest";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Создание таблицы авторизации
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AUTH + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT," +
                "password TEXT);");
        // Создание таблицы справочника пользователя
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nickname TEXT," +
                "id_kvest INTEGER," +
                "id_auth INTEGER," +
                "FOREIGN KEY (id_kvest) REFERENCES "+ TABLE_KVEST +"(id)," +
                "FOREIGN KEY (id_auth) REFERENCES "+ TABLE_AUTH +"(id));");
        // Создание таблицы с квестами
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_KVEST + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "namePoint TEXT," +
                "X REAL," +
                "Y REAL);");

        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_AUTH + " (login, password) VALUES ('admin', '123');" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTH);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_KVEST);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }
}
