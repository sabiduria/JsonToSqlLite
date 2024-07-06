package com.sabiantart.jsontosqllite;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sabiantart.jsontosqllite.models.Formules;
import com.sabiantart.jsontosqllite.models.FormulesResponse;
import com.sabiantart.jsontosqllite.models.JsonUtils;
import com.sabiantart.jsontosqllite.models.Products;
import com.sabiantart.jsontosqllite.models.Users;
import com.sabiantart.jsontosqllite.utils.FileDownloader;

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
        JsonUtils jsonUtils2 = new JsonUtils(this, "products.json", "");
        jsonUtils.readJsonFromAsset("formules");

        FormulesResponse usersData = jsonUtils.parseJsonToModel("users", "Assets");
        FormulesResponse formulesData = jsonUtils.parseJsonToModel("formules", "Assets");
        FormulesResponse productsData = jsonUtils2.parseJsonToModel("products", "InternalArrayed");

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

        if (productsData != null) {
            List<Products> products = productsData.products;
            for (Products product : products) {
                Log.d("Product Data", "Img Url : " + product.getImgUrl() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", ID: " + product.getId());
            }
        }

        String fileUrl = "https://sabiantart.com/apps/toleka.json";
        String fileName = "products.json";

        // Download the file in a background thread (using AsyncTask, Thread, etc.)
        //new Thread(() -> FileDownloader.downloadFile(MainActivity.this, fileUrl, fileName)).start();
        ProgressBar progressBar = findViewById(R.id.progressBar); // Get your ProgressBar

        FileDownloader.ProgressListener listener = new FileDownloader.ProgressListener() {
            @Override
            public void onProgressUpdate(int progress) {
                progressBar.setProgress(progress);
            }
        };

        new Thread(() -> FileDownloader.downloadFile(MainActivity.this, fileUrl, fileName, listener)).start();
    }


}