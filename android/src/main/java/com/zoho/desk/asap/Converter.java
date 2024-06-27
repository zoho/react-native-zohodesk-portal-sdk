package com.zoho.desk.asap;

import com.facebook.react.bridge.ReadableMap;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.lang.reflect.Type;
import java.util.HashMap;

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
                        jsonObject.put(key, readableMap.getArray(key).toArrayList());
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

    public static HashMap<String, String> convertReadableMapToHashMap(ReadableMap readableMap) {
        JSONObject jsonObject = convertReadableMapToJson(readableMap);
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String,String>>() {}.getType();
        return gson.fromJson(jsonObject.toString(), type);
    }
}
