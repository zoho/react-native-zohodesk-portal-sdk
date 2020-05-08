package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.ZDPortalHome;

public class RNZDPortalHome extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZDPortalHome(ReactApplicationContext applicationContext) {
        super(applicationContext);
        this.reactContext = applicationContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalHome";
    }

    @ReactMethod
    public void show() {
        ZDPortalHome.show(reactContext.getCurrentActivity());
    }
}
