package com.zoho.desk.asap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

public class RNZohodeskPortalSdkPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.<NativeModule>asList(new RNZohodeskPortalSDK(reactContext), new RNZDPortalHome(reactContext),
                new RNZDPortalKB(reactContext), new RNZDPortalCommunity(reactContext), new RNZDPortalTickets(reactContext),
                new RNZDPortalSubmitTicket(reactContext), new RNZDPortalChat(reactContext),new RNZDPortalLiveChat(reactContext));
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
