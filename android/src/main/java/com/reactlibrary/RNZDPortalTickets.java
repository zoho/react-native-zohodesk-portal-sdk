package com.reactlibrary;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.asap_tickets.ZDPortalTickets;

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
}
