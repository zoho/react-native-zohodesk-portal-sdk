package com.zoho.desk.asap;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.asap_tickets.ZDPortalTickets;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.zoho.desk.asap.api.ZDPortalCallback.TicketFormCallback;
import com.zoho.desk.asap.api.response.TicketFieldsList;
import com.zoho.desk.asap.api.ZDPortalCallback.TicketFieldsCallback;
import com.zoho.desk.asap.api.response.TicketFieldsList;
import com.zoho.desk.asap.api.ZDPortalException;
import com.zoho.desk.asap.api.ZDPortalTicketsAPI;
import com.zoho.desk.asap.api.response.TicketForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zoho.desk.asap.api.ZDPortalCallback;
import android.os.Handler;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

public class RNZDPortalTickets extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZDPortalTickets(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalTickets";
    }

    @ReactMethod
    public void show() {
        ZDPortalTickets.show(reactContext.getCurrentActivity());
    }


    @ReactMethod
    public void getTicketForm(final ReadableMap params,String flags,final Callback successCallback, final Callback errorCallback){
         Handler handler = new Handler();
         handler.post(new Runnable(){
            @Override
            public void run(){
              ZDPortalCallback.TicketFormCallback ticketFormCallback = new ZDPortalCallback.TicketFormCallback() {
                @Override
                public void onTicketFormDownloaded(TicketForm ticketForm) {
                  Gson gson = new Gson();
                  String jsonString = gson.toJson(ticketForm);
                  successCallback.invoke(Converter.toWritableMap(jsonString));
                }
                @Override
                public void onException(ZDPortalException exception) {
                    WritableMap errorMap = Arguments.createMap(); 
                    errorMap.putInt("errorCode",exception.getErrorCode());
                    errorMap.putString("errorMsg",exception.getErrorMsg());
                    errorCallback.invoke(errorMap);
                }
            };
            ZDPortalTicketsAPI.getTicketForm(ticketFormCallback,Converter.convertReadableMapToHashMap(params),flags);
              }
         });
    }

    @ReactMethod
    public void getTicketFields(final ReadableMap params,String flags,final Callback successCallback, final Callback errorCallback){
         Handler handler = new Handler();
         handler.post(new Runnable(){
            @Override
            public void run(){
              ZDPortalCallback.TicketFieldsCallback  ticketFieldsCallback = new ZDPortalCallback.TicketFieldsCallback() {
                @Override
                public void onTicketFieldsDownloaded(TicketFieldsList ticketFieldsList ) {
                  Gson gson = new Gson();
                  String jsonString = gson.toJson(ticketFieldsList);
                  successCallback.invoke(Converter.toWritableMap(jsonString));
                }
                @Override
                public void onException(ZDPortalException exception) {
                    WritableMap errorMap = Arguments.createMap(); 
                    errorMap.putInt("errorCode",exception.getErrorCode());
                    errorMap.putString("errorMsg",exception.getErrorMsg());
                    errorCallback.invoke(errorMap);
                }
            };
            ZDPortalTicketsAPI.getTicketFields(ticketFieldsCallback,Converter.convertReadableMapToHashMap(params),flags);
              }
         });
    }

}
