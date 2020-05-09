package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.zoho.desk.asap.api.ZDPortalCallback;
import com.zoho.desk.asap.api.ZDPortalException;
import com.zoho.desk.asap.api.ZohoDeskPortalSDK;

public class RNZohodeskPortalSDK extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private ZohoDeskPortalSDK portalSDK;

    public RNZohodeskPortalSDK(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        portalSDK = ZohoDeskPortalSDK.getInstance(reactContext.getApplicationContext());
    }

    @Override
    public String getName() {
        return "RNZohoDeskPortalSDK";
    }

    @ReactMethod
    public void initialise(String orgId, String appId, String dcStr) {
        ZohoDeskPortalSDK.DataCenter dc = ZohoDeskPortalSDK.DataCenter.US;
        switch (dcStr.toLowerCase()) {
            case "eu":
                dc = ZohoDeskPortalSDK.DataCenter.EU;
                break;
            case "cn":
                dc = ZohoDeskPortalSDK.DataCenter.CN;
                break;
            case "in":
                dc = ZohoDeskPortalSDK.DataCenter.IN;
                break;
            case "au":
                dc = ZohoDeskPortalSDK.DataCenter.AU;
                break;
        }
        portalSDK.initDesk(Long.valueOf(orgId), appId, dc);
    }

    @ReactMethod
    public void setUserToken(String userToken, final Callback successCallabck, final Callback errorCallback) {
        portalSDK.setUserToken(userToken, new ZDPortalCallback.SetUserCallback() {
            @Override
            public void onUserSetSuccess() {
                successCallabck.invoke();
            }

            @Override
            public void onException(ZDPortalException e) {
                errorCallback.invoke();
            }
        });
    }
}
