package com.zoho.desk.asap;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


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

    public static WritableMap toWritableMap(String jsonString) {
        WritableMap map = Arguments.createMap();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            map = jsonToWritableMap(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static WritableMap jsonToWritableMap(JSONObject jsonObject) throws JSONException {
        WritableMap map = Arguments.createMap();
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = jsonObject.get(key);
            if (value instanceof Boolean) {
                map.putBoolean(key, (Boolean) value);
            } else if (value instanceof Integer) {
                map.putInt(key, (Integer) value);
            } else if (value instanceof Double) {
                map.putDouble(key, (Double) value);
            } else if (value instanceof String) {
                map.putString(key, (String) value);
            } else if (value instanceof JSONObject) {
                map.putMap(key, jsonToWritableMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                map.putArray(key, jsonToWritableArray((JSONArray) value));
            }
        }
        return map;
    }

    private static WritableArray jsonToWritableArray(JSONArray jsonArray) throws JSONException {
        WritableArray array = Arguments.createArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof Boolean) {
                array.pushBoolean((Boolean) value);
            } else if (value instanceof Integer) {
                array.pushInt((Integer) value);
            } else if (value instanceof Double) {
                array.pushDouble((Double) value);
            } else if (value instanceof String) {
                array.pushString((String) value);
            } else if (value instanceof JSONObject) {
                array.pushMap(jsonToWritableMap((JSONObject) value));
            } else if (value instanceof JSONArray) {
                array.pushArray(jsonToWritableArray((JSONArray) value));
            }
        }
        return array;
    }       
}
