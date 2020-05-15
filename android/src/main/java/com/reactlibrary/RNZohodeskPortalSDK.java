package com.reactlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.zoho.desk.asap.api.ZDPortalCallback;
import com.zoho.desk.asap.api.ZDPortalException;
import com.zoho.desk.asap.api.ZohoDeskPortalSDK;
import com.zoho.desk.asap.common.ZDPortalConfiguration;

import java.util.Map;


public class RNZohodeskPortalSDK extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private static String firebaseInstanceId = "";
    private static boolean isInitDone = false;
    private static int themeResId = -1;

    public RNZohodeskPortalSDK(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        ZohoDeskPortalSDK.Logger.enableLogs();
    }

    @Override
    public String getName() {
        return "RNZohoDeskPortalSDK";
    }

    @ReactMethod
    public void initialise(final String orgId, final String appId, final String dcStr) {
        if(getCurrentActivity() != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    initialiseDesk(getCurrentActivity().getApplication(), orgId, appId, dcStr);
                }
            });
        }
    }

    private static void initialiseDesk(Context application, String orgId, String appId, String dcStr) {
        isInitDone = true;
        ZohoDeskPortalSDK portalSDK = ZohoDeskPortalSDK.getInstance(application);
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
        if(!TextUtils.isEmpty(firebaseInstanceId)) {
            portalSDK.enablePush(firebaseInstanceId);
        }
        if(themeResId != -1) {
            ZDPortalConfiguration.setThemeResource(themeResId);
        }
    }

    @ReactMethod
    public void setUserToken(final String userToken, final Callback successCallback, final Callback errorCallback) {
        if(getCurrentActivity() != null) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ZohoDeskPortalSDK deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity().getApplicationContext());
                    deskPortalSDK.setUserToken(userToken, new ZDPortalCallback.SetUserCallback() {
                        @Override
                        public void onUserSetSuccess() {
                            successCallback.invoke("User set Success");
                        }

                        @Override
                        public void onException(ZDPortalException e) {
                            errorCallback.invoke("User set Failure");
                        }
                    });
                }
            });
        }
    }

    @ReactMethod
    public void logout(final Callback successCallback, final Callback errorCallback) {
        if(getCurrentActivity() != null) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ZohoDeskPortalSDK deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity().getApplicationContext());
                    deskPortalSDK.logout(new ZDPortalCallback.LogoutCallback() {
                        @Override
                        public void onLogoutSuccess() {
                            successCallback.invoke("Logout Success");
                        }

                        @Override
                        public void onException(ZDPortalException e) {
                            errorCallback.invoke("Logout Failure");
                        }
                    });
                }
            });
        }
    }

    public static void setFirebaseId(String firebaseId) {
        firebaseInstanceId = firebaseId;
    }

    public static void handleNotification(final Context application, final Map extras, final int icon) {
        if(isInitDone) {
            ZDPortalConfiguration.handleNotification(application, extras, icon);
            return;
        }
        SharedPreferences sharedPreferences = application.getSharedPreferences("ZohoDeskASAPSDK", 0);
        final long orgId = sharedPreferences.getLong("orgId", -1);
        final String appId = sharedPreferences.getString("appId", "");
        if (orgId != -1 && !TextUtils.isEmpty(appId)) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    initialiseDesk(application, String.valueOf(orgId), appId, "us");
                    ZDPortalConfiguration.handleNotification(application, extras, icon);
                }
            });
        }
    }

    public static void setThemeResource(int resourceId) {
        themeResId = resourceId;
    }
}
