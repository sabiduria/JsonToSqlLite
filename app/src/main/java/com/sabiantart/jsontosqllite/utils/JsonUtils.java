package com.sabiantart.jsontosqllite.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sabiantart.jsontosqllite.models.Cubundle;
import com.sabiantart.jsontosqllite.models.FormulesResponse;
import com.sabiantart.jsontosqllite.models.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class JsonUtils {

    public static String filename;
    public static String filename2;
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public JsonUtils(Context _context, String _filename){
        filename = _filename;
        context = _context;
    }

    public JsonUtils(Context _context, String _filename, String _other){
        filename2 = _filename;
        context = _context;
    }

    public static String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Log.e(String.valueOf(Level.WARNING), ex.getMessage(), ex);
            return null;
        }
        return json;
    }

    public void readJsonFromAsset(String ArrayName) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArray = obj.getJSONArray(ArrayName);
            Log.d("Array Data", m_jArray.toString());
            ArrayList<HashMap<String, String>> formList = new ArrayList<>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArray.length(); i++) {
                JSONObject jo_inside = m_jArray.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("formule")+" "+jo_inside.getString("url"));

                String formula_value = jo_inside.getString("formule");
                String url_value = jo_inside.getString("url");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<>();
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);

                formList.add(m_li);
            }
        } catch (JSONException ex) {
            Log.e(String.valueOf(Level.WARNING), ex.getMessage(), ex);
        }
    }

    public FormulesResponse parseJsonToModel(String ArrayName, String source) {
        try {
            String jsonString;
            if (source.equals("Assets")){
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                JSONArray m_jArray = obj.getJSONArray(ArrayName);
                jsonString = String.format("{\"%s\":%s}", ArrayName, m_jArray);
                Log.d("Array Data", jsonString);

            } else if (source.equals("InternalSimple")){
                jsonString = String.format("{\"%s\":%s}", ArrayName, readJsonFromInternalStorage());
                Log.d("Internal Array Data", jsonString);

            }else {
                JSONObject obj = new JSONObject(readJsonFromInternalStorage());
                JSONArray m_jArray = obj.getJSONArray(ArrayName);
                jsonString = String.format("{\"%s\":%s}", ArrayName, m_jArray);
                Log.d("Internal Array Data", jsonString);
            }
            Gson gson = new Gson();

            return gson.fromJson(jsonString, FormulesResponse.class);
        } catch (Exception ex) {
            Log.e(String.valueOf(Level.WARNING), ex.getMessage(), ex);
            return null;
        }
    }

    public String readJsonFromInternalStorage() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.openFileInput(filename2));
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
        Log.d("Internal Data : ", stringBuilder.toString());
        return stringBuilder.toString();
    }

    public void processLargeJson() {
        Gson gson = new Gson();
        try (InputStreamReader inputStreamReader = new InputStreamReader(context.openFileInput(filename2));
             BufferedReader reader = new BufferedReader(inputStreamReader);
             JsonReader jsonReader = new JsonReader(reader)) {

            jsonReader.beginObject(); // Start of the outer JSON object
            if (jsonReader.nextName().equals("users")) {
                jsonReader.beginArray(); // Start of the "users" array
                while (jsonReader.hasNext()) {
                    Users user = gson.fromJson(jsonReader, Users.class);
                    System.out.println("USER ID: " + user.getId());
                }
                jsonReader.endArray(); // End of the "users" array
            }
            jsonReader.skipValue(); // End of the outer JSON object

        } catch (IOException e) {
            Log.e(String.valueOf(Level.WARNING), e.getMessage(), e);
        }
    }

    /**
     * This method processes a large JSON file and extracts a list of objects of the specified data type.
     *
     * @param dataType The class type of the objects to be extracted.
     * @return A list of objects of the specified data type, or null if an error occurs.
     */
    public <T> List<T> processLargeJson(Class<T> dataType) {
        Gson gson = new Gson();
        List<T> dataList = new ArrayList<>();
        try (InputStreamReader inputStreamReader = new InputStreamReader(context.openFileInput(filename2));
             BufferedReader reader = new BufferedReader(inputStreamReader);
             JsonReader jsonReader = new JsonReader(reader)) {

            jsonReader.beginObject();

            while (jsonReader.hasNext()) {
                String currentKey = jsonReader.nextName();
                if (currentKey.equals(getArrayKeyForClass(dataType))) {
                    //Type dataListType = new TypeToken<List<T>>() {}.getType();
                    Type data = TypeToken.getParameterized(List.class, dataType).getType();
                    dataList = gson.fromJson(jsonReader, data);
                    break; // Exit loop after finding the desired array
                } else {
                    jsonReader.skipValue(); // Skip other arrays or values
                }
            }

            jsonReader.skipValue();

        } catch (IOException e) {
            Log.e("JSON Processing Error", "Error processing JSON: " + e.getMessage(), e);
        }
        return dataList;
    }

    private <T> String getArrayKeyForClass(Class<T> dataType) {
        if (dataType == Cubundle.class) {
            return "cubundle";
        } else {
            return "users"; // Handle unknown data types appropriately
        }
    }

}