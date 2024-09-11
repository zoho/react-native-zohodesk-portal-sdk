package com.zoho.desk.asap;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.livechat.ZDPortalLiveChat;
import com.facebook.react.bridge.Callback;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.google.gson.reflect.TypeToken;
import com.zoho.desk.asap.Converter;

public class RNZDPortalLiveChat extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZDPortalLiveChat(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalLiveChat";
    }


   @ReactMethod
   public void showGC() {
     ZDPortalLiveChat.showGC(reactContext.getCurrentActivity());
   }

   @ReactMethod
   public void showAnswerBot() {
     ZDPortalLiveChat.showAnswerBot(reactContext.getCurrentActivity());
   }

   @ReactMethod
   public void showBM() {
     ZDPortalLiveChat.showBusinessMessenger(reactContext.getCurrentActivity());
   }

   private ArrayList<HashMap<String, Object>> sessionVariableConverter (final ReadableArray params) {
     JSONArray jsonObject =  Converter.convertReadableArrayToJson(params);
     Gson gson = new Gson();
     Type type = new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType();
     return gson.fromJson(jsonObject.toString(), type);
   }

   @ReactMethod
   public void setGCSessionVariable(final ReadableArray params) {
     ZDPortalLiveChat.setGCSessionVariable(reactContext.getCurrentActivity(),sessionVariableConverter(params));
   }

   @ReactMethod
   public void updateGCSessionVariable(final ReadableArray params) {
     ZDPortalLiveChat.updateGCSessionVariable(reactContext.getCurrentActivity(),sessionVariableConverter(params));
   }

   @ReactMethod
   public void setBMSessionVariable(final ReadableArray params) {
     ZDPortalLiveChat.setBMSessionVariable(reactContext.getCurrentActivity(),sessionVariableConverter(params));
   }

   @ReactMethod
   public void updateBMSessionVariable(final ReadableArray params) {
     ZDPortalLiveChat.updateBMSessionVariable(reactContext.getCurrentActivity(),sessionVariableConverter(params));
   }
}
