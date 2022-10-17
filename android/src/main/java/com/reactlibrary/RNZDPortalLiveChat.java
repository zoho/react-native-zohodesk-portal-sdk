package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.livechat.ZDPortalLiveChat;

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
    public void show() {
        ZDPortalLiveChat.show(reactContext.getCurrentActivity());
    }

}
