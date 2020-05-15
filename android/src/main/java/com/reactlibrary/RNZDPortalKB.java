package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.kb.ZDPortalKB;

public class RNZDPortalKB extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    public RNZDPortalKB(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalKB";
    }

    @ReactMethod
    public void show() {
        ZDPortalKB.show(reactContext.getCurrentActivity());
    }
}
