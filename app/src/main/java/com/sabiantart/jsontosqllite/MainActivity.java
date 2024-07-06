package com.sabiantart.jsontosqllite;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sabiantart.jsontosqllite.models.Formules;
import com.sabiantart.jsontosqllite.models.FormulesResponse;
import com.sabiantart.jsontosqllite.models.JsonUtils;
import com.sabiantart.jsontosqllite.models.Users;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        JsonUtils jsonUtils = new JsonUtils(this, "my_data.json");
        jsonUtils.readJsonFromAsset("formules");

        FormulesResponse usersData = jsonUtils.parseJsonToModel("users");
        FormulesResponse formulesData = jsonUtils.parseJsonToModel("formules");

        if (usersData != null) {
            List<Users> users = usersData.users;
            for (Users user : users) {
                Log.d("User Data", "User Code: " + user.getCode() + ", Name: " + user.getName());
            }
        }

        if (formulesData != null) {
            List<Formules> formules = formulesData.formules;
            for (Formules formule : formules) {
                Log.d("Formule Data", "Formule : " + formule.getFormule() + ", URL: " + formule.getUrl());
            }
        }

    }


}