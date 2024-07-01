package com.zoho.desk.asap;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class Converter {
    
    public static JSONObject convertReadableMapToJson(ReadableMap readableMap) {
        JSONObject jsonObject = new JSONObject();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();

        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            try {
                switch (readableMap.getType(key)) {
                    case String:
                        jsonObject.put(key, readableMap.getString(key));
                        break;
                    case Number:
                        jsonObject.put(key, readableMap.getDouble(key));
                        break;
                    case Boolean:
                        jsonObject.put(key, readableMap.getBoolean(key));
                        break;
                    case Map:
                        jsonObject.put(key, convertReadableMapToJson(readableMap.getMap(key)));
                        break;
                    case Array:
                        jsonObject.put(key, convertReadableArrayToJson(readableMap.getArray(key)));
                        break;
                    default:
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonObject;
    }

    public static JSONArray convertReadableArrayToJson(ReadableArray readableArray) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < readableArray.size(); i++) {
            try {
                switch (readableArray.getType(i)) {
                    case String:
                        jsonArray.put(readableArray.getString(i));
                        break;
                    case Number:
                        jsonArray.put(readableArray.getDouble(i));
                        break;
                    case Boolean:
                        jsonArray.put(readableArray.getBoolean(i));
                        break;
                    case Map:
                        jsonArray.put(convertReadableMapToJson(readableArray.getMap(i)));
                        break;
                    case Array:
                        jsonArray.put(convertReadableArrayToJson(readableArray.getArray(i)));
                        break;
                    default:
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray;
    }

    public static HashMap<String, String> convertReadableMapToHashMap(ReadableMap readableMap) {
        JSONObject jsonObject = convertReadableMapToJson(readableMap);
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        return gson.fromJson(jsonObject.toString(), type);
    }
}
