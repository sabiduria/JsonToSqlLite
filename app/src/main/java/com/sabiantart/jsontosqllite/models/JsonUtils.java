package com.sabiantart.jsontosqllite.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class JsonUtils {

    public static String filename;
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    public JsonUtils(Context _context, String _filename){
        filename = _filename;
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

    public FormulesResponse parseJsonToModel(String ArrayName) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArray = obj.getJSONArray(ArrayName);
            String jsonString = String.format("{\"%s\":%s}", ArrayName, m_jArray);
            Log.d("Array Data", jsonString);
            Gson gson = new Gson();

            return gson.fromJson(jsonString, FormulesResponse.class);
        } catch (Exception ex) {
            Log.e(String.valueOf(Level.WARNING), ex.getMessage(), ex);
            return null;
        }
    }

}