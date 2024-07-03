package com.sabiantart.jsontosqllite.models;

import android.content.Context;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static String readJsonFromInternalStorage(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.openFileInput(fileName));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            // Handle exceptions as needed
            return null;
        }
        return stringBuilder.toString();
    }

    public static List<MyData> parseJson(String jsonString) {
        Gson gson = new Gson();
        try {
            MyData[] myDataArray = gson.fromJson(jsonString, MyData[].class);
            return Arrays.asList(myDataArray);
        } catch (Exception e) {
            // Handle exceptions as needed
            return null;
        }
    }
}