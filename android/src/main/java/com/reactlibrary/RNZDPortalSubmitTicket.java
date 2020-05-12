package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.asap_tickets.ZDPortalSubmitTicket;

public class RNZDPortalSubmitTicket extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZDPortalSubmitTicket(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalSubmitTicket";
    }

    @ReactMethod
    public void show() {
        ZDPortalSubmitTicket.show(reactContext.getCurrentActivity());
    }
}
