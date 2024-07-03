package com.sabiantart.jsontosqllite;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sabiantart.jsontosqllite.models.DatabaseUtils;
import com.sabiantart.jsontosqllite.models.JsonUtils;
import com.sabiantart.jsontosqllite.models.MyData;

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

        String jsonString = JsonUtils.readJsonFromInternalStorage(this, "my_data.json");
        List<MyData> dataList = JsonUtils.parseJson(jsonString);
        if (dataList != null) {
            DatabaseUtils.insertDataIntoDatabase(this, dataList);
        }
    }
}