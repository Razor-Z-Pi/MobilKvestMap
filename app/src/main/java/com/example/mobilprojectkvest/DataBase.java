package com.example.mobilprojectkvest;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kvestDB.db"; // Навзвание БД
    private static final int SCHEMA = 12; // Версия БД
    static final String TABLE_AUTH = "Authorization";
    static final String TABLE_USER = "User";
    static final String TABLE_KVEST = "Kvest";
    static final String TABLE_TEST = "TestKvest";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Создание таблицы авторизации
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AUTH + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT," +
                "Password TEXT);");

        // Создание таблицы справочника пользователя
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nickname TEXT," +
                "id_kvest INTEGER," +
                "id_auth INTEGER," +
                "FOREIGN KEY (id_kvest) REFERENCES "+ TABLE_KVEST +"(_id)," +
                "FOREIGN KEY (id_auth) REFERENCES "+ TABLE_AUTH +"(_id));");

        // Создание таблицы с квестами
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_KVEST + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "namePoint TEXT," +
                "X REAL," +
                "Y REAL," +
                "idTestKvest INTEGER," +
                "FOREIGN KEY (idTestKvest) REFERENCES " + TABLE_TEST +"(_id));");

        //Создание таблицы для тестов
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TEST + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "description TEXT," +
                "valueTest TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTH);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_KVEST);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        onCreate(sqLiteDatabase);
    }
}
