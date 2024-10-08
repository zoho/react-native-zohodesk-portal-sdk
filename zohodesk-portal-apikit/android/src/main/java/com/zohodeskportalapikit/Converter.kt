package com.zohodeskportalapikit

import com.facebook.react.bridge.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONArray
import com.facebook.react.bridge.ReadableMap


object Converter {

    fun ReadableMap.getBooleanOrDefault(key: String, default: Boolean): Boolean {
        return if (this.hasKey(key)) this.getBoolean(key) else default
    }
    
    fun convertReadableMapToJson(readableMap: ReadableMap): JSONObject {
        val jsonObject = JSONObject()
        val iterator = readableMap.keySetIterator()

        while (iterator.hasNextKey()) {
            val key = iterator.nextKey()
            try {
                when (readableMap.getType(key)) {
                    ReadableType.String -> jsonObject.put(key, readableMap.getString(key))
                    ReadableType.Number -> jsonObject.put(key, readableMap.getDouble(key))
                    ReadableType.Boolean -> jsonObject.put(key, readableMap.getBoolean(key))
                    ReadableType.Map -> jsonObject.put(key, convertReadableMapToJson(readableMap.getMap(key)!!))
                    ReadableType.Array -> jsonObject.put(key, convertReadableArrayToJson(readableMap.getArray(key)!!))
                    else -> {}
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        return jsonObject
    }

    fun convertReadableArrayToJson(readableArray: ReadableArray): JSONArray {
        val jsonArray = JSONArray()
        for (i in 0 until readableArray.size()) {
            try {
                when (readableArray.getType(i)) {
                    ReadableType.String -> jsonArray.put(readableArray.getString(i))
                    ReadableType.Number -> jsonArray.put(readableArray.getDouble(i))
                    ReadableType.Boolean -> jsonArray.put(readableArray.getBoolean(i))
                    ReadableType.Map -> jsonArray.put(convertReadableMapToJson(readableArray.getMap(i)))
                    ReadableType.Array -> jsonArray.put(convertReadableArrayToJson(readableArray.getArray(i)))
                    else -> {}
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return jsonArray
    }
    
    fun convertReadableMapToHashMap(readableMap: ReadableMap): HashMap<String, String> {
        val jsonObject = convertReadableMapToJson(readableMap)
        val gson = Gson()
        val type = object : TypeToken<HashMap<String, String>>() {}.type
        return gson.fromJson(jsonObject.toString(), type)
    }

    fun toWritableMap(jsonString: String): WritableMap {
        val map = Arguments.createMap()
        try {
            val jsonObject = JSONObject(jsonString)
            return jsonToWritableMap(jsonObject)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return map
    }


    @Throws(JSONException::class)
    private fun jsonToWritableMap(jsonObject: JSONObject): WritableMap {
        val map = Arguments.createMap()
        val keys = jsonObject.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            val value = jsonObject.get(key)
            when (value) {
                is Boolean -> map.putBoolean(key, value)
                is Int -> map.putInt(key, value)
                is Double -> map.putDouble(key, value)
                is String -> map.putString(key, value)
                is JSONObject -> map.putMap(key, jsonToWritableMap(value))
                is JSONArray -> map.putArray(key, jsonToWritableArray(value))
            }
        }
        return map
    }

    @Throws(JSONException::class)
    private fun jsonToWritableArray(jsonArray: JSONArray): WritableArray {
        val array = Arguments.createArray()
        for (i in 0 until jsonArray.length()) {
            val value = jsonArray.get(i)
            when (value) {
                is Boolean -> array.pushBoolean(value)
                is Int -> array.pushInt(value)
                is Double -> array.pushDouble(value)
                is String -> array.pushString(value)
                is JSONObject -> array.pushMap(jsonToWritableMap(value))
                is JSONArray -> array.pushArray(jsonToWritableArray(value))
            }
        }
        return array
    }       
}

