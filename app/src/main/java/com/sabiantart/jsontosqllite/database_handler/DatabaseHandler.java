package com.sabiantart.jsontosqllite.database_handler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    protected static final String DATABASE_NAME = "json2sqlite.db";
    public static final String TABLE_USERS = "users";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS +" (id TEXT, idrole TEXT, username TEXT, password TEXT, email TEXT, avatar TEXT, prenom TEXT, nom TEXT, telephone TEXT, statut TEXT, dateinscription TEXT, iduser TEXT, datemiseajour TEXT, sexe TEXT, codeuser TEXT, rolead1 TEXT, rolead2 TEXT, rolead3 TEXT, section TEXT, langue TEXT, synced TEXT);";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
    }
}
