package com.sabiantart.jsontosqllite.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sabiantart.jsontosqllite.helper.MyDatabaseHelper;
import com.sabiantart.jsontosqllite.models.MyData;

import java.util.List;

public class DatabaseUtils {

    public static void insertDataIntoDatabase(Context context, List<MyData> dataList) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (MyData data : dataList) {
            ContentValues values = new ContentValues();
            values.put("id", data.formule);
            values.put("name", data.url);
            db.insert("my_data", null, values);
        }

        db.close();
    }
}