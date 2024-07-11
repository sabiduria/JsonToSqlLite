package com.sabiantart.jsontosqllite;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sabiantart.jsontosqllite.controllers.EmployeesControllers;
import com.sabiantart.jsontosqllite.models.Cubundle;
import com.sabiantart.jsontosqllite.models.Employees;
import com.sabiantart.jsontosqllite.models.Formules;
import com.sabiantart.jsontosqllite.models.FormulesResponse;
import com.sabiantart.jsontosqllite.utils.JsonUtils;
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

        //downloadJsonFile();
        //getDataFromAssets();
        //getDataFromInternalStorage();
        getDataFromLargeFile();
    }

    public void getDataFromAssets(){
        JsonUtils jsonUtils = new JsonUtils(this, "my_data.json");
        jsonUtils.readJsonFromAsset("formules");
        FormulesResponse usersData = jsonUtils.parseJsonToModel("users", "Assets");
        FormulesResponse formulesData = jsonUtils.parseJsonToModel("formules", "Assets");

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

    public void getDataFromInternalStorage(){
        JsonUtils jsonUtils2 = new JsonUtils(this, "products.json", "");
        FormulesResponse productsData = jsonUtils2.parseJsonToModel("products", "InternalArrayed");

        if (productsData != null) {
            List<Products> products = productsData.products;
            for (Products product : products) {
                Log.d("Product Data", "Img Url : " + product.getImgUrl() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", ID: " + product.getId());
            }
        }
    }

    public void getDataFromLargeFile(){
        JsonUtils jsonUtils2 = new JsonUtils(this, "products.json", "");
        //jsonUtils2.processLargeJson();
        // To get users:
        Log.d("JSON Processing", "Getting users...");
        List<Employees> users = jsonUtils2.processLargeJson(Employees.class);
        Log.d("JSON Processing", users.toString());
        for (Employees employees : users) {
            Log.d("USER ID", String.valueOf(employees.getId()));
            boolean createSuccessful = new EmployeesControllers(this).create(employees);
            if(createSuccessful){
                Toast.makeText(this, "Employee information was saved.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Unable to save employee information.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void downloadJsonFile(){
        String fileUrl = "https://sabiantart.com/apps/toleka.json";
        String fileName = "products.json";

        // Download the file in a background thread (using AsyncTask, Thread, etc.)
        ProgressBar progressBar = findViewById(R.id.progressBar); // Get your ProgressBar
        FileDownloader.ProgressListener listener = progressBar::setProgress;
        new Thread(() -> FileDownloader.downloadFile(MainActivity.this, fileUrl, fileName, listener)).start();
    }
}