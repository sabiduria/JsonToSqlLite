package com.sabiantart.jsontosqllite.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sabiantart.jsontosqllite.helper.MyDatabaseHelper;

import java.util.List;

public class DatabaseUtils {

    public static void insertDataIntoDatabase(Context context, List<MyData> dataList) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (MyData data : dataList) {
            ContentValues values = new ContentValues();
            values.put("id", data.id);
            values.put("name", data.name);
            db.insert("my_data", null, values);
        }

        db.close();
    }
}