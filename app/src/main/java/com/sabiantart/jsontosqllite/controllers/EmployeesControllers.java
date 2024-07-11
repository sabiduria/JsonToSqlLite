package com.sabiantart.jsontosqllite.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.sabiantart.jsontosqllite.database_handler.DatabaseHandler;
import com.sabiantart.jsontosqllite.models.Employees;

public class EmployeesControllers extends DatabaseHandler {

    public EmployeesControllers(@Nullable Context context) {
        super(context);
    }

    public boolean create(Employees users) {

        ContentValues values = new ContentValues();

        values.put("id", users.getId());
        values.put("idrole", users.getIdrole());
        values.put("username", users.getUsername());
        values.put("password", users.getPassword());
        values.put("email", users.getEmail());
        values.put("avatar", users.getAvatar());
        values.put("prenom", users.getPrenom());
        values.put("nom", users.getNom());
        values.put("telephone", users.getTelephone());
        values.put("statut", users.getStatut());
        values.put("dateinscription", users.getDateinscription());
        values.put("iduser", users.getIduser());
        values.put("datemiseajour", users.getDatemiseajour());
        values.put("sexe", users.getSexe());
        values.put("codeuser", users.getCodeuser());
        values.put("rolead1", users.getRolead1());
        values.put("rolead2", users.getRolead2());
        values.put("rolead3", users.getRolead3());
        values.put("section", users.getSection());
        values.put("langue", users.getLangue());
        values.put("synced", users.getSynced());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert(DatabaseHandler.TABLE_USERS, null, values) > 0;
        db.close();

        return createSuccessful;
    }
}
