package com.zoho.desk.asap;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.ZDPortalHome;
import com.zoho.desk.asap.api.ZohoDeskPortalSDK;
import com.zoho.desk.asap.asap_community.ZDPortalCommunity;

public class RNZDPortalCommunity extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZDPortalCommunity(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalCommunity";
    }

    @ReactMethod
    public void show() {
        ZDPortalCommunity.show(reactContext.getCurrentActivity());
    }
}
