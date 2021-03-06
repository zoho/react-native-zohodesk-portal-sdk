package com.reactlibrary;


import android.os.Handler;
import android.os.Looper;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.chat.ZDPortalChat;

public class RNZDPortalChat extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZDPortalChat(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalChat";
    }

    @ReactMethod
    public void show() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                ZDPortalChat.show(reactContext.getCurrentActivity());
            }
        });
    }
}
