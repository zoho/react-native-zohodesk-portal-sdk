package com.zoho.desk.asap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.graphics.Color;
import java.io.*; 
import com.zoho.desk.asap.Converter;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zoho.desk.asap.api.ZDPortalCallback;
import com.zoho.desk.asap.api.ZDPortalException;
import com.zoho.desk.asap.api.ZohoDeskPortalSDK;
import com.zoho.desk.asap.common.ZDPortalConfiguration;
import com.zoho.desk.asap.api.ZDPortalAPI;
import com.zoho.desk.asap.common.utils.ZDPTheme;
import com.zoho.desk.asap.common.utils.ZDPThemeType;
import com.zoho.desk.asap.api.response.TicketForm;
import com.zoho.desk.asap.api.response.Layouts;
import com.zoho.desk.asap.api.response.Layout;
import com.zoho.desk.asap.api.util.ZohoDeskAPIImpl;
import com.zoho.desk.asap.api.response.DepartmentsList;
import com.zoho.desk.asap.api.response.Department;
import com.zoho.desk.asap.api.ZDPortalCallback.TicketFormCallback;
import com.zoho.desk.asap.api.response.TicketFieldsList;
import com.zoho.desk.asap.api.ZDPortalCallback.TicketFieldsCallback;
import com.zoho.desk.asap.api.response.TicketFieldsList;
import com.zoho.desk.asap.api.ZDPortalTicketsAPI;
 

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class RNZohodeskPortalSDK extends ReactContextBaseJavaModule {

    public static final String colorPrimary = "colorPrimary";
    public static final String colorPrimaryDark = "colorPrimaryDark";
    public static final String colorAccent = "colorAccent";
    public static final String windowBackground = "windowBackground";
    public static final String itemBackground = "itemBackground";
    public static final String textColorPrimary = "textColorPrimary";
    public static final String textColorSecondary = "textColorSecondary";
    public static final String colorOnPrimary = "colorOnPrimary";
    public static final String iconTint = "iconTint";
    public static final String divider = "divider";
    public static final String listSelector = "listSelector";
    public static final String hint = "hint";
    public static final String formFieldBorder = "formFieldBorder";
    public static final String errorColor = "errorColor";

    public static final int systemTheme = 1;
    public static final int lightTheme = 2;
    public static final int darkTheme = 3;

    private final ReactApplicationContext reactContext;
    private static String firebaseInstanceId;
    private static boolean isInitDone = false;
    private static ZDPThemeType themeType = ZDPThemeType.SYSTEM;
    private static ZDPTheme themeObj = null;

    public RNZohodeskPortalSDK(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
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
        SharedPreferences preferences = application.getSharedPreferences("RNZohoDeskASAP", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("orgId", orgId);
        editor.putString("appId", appId);
        editor.putString("dcStr", dcStr);
        editor.apply();
        ZohoDeskPortalSDK portalSDK = ZohoDeskPortalSDK.getInstance(application);
        ZohoDeskAPIImpl.setRefererHeader("react-native");
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
        ZDPortalConfiguration.setThemeType(themeType);
        if(themeObj != null) {
            ZDPortalConfiguration.setThemeBuilder(themeObj);
        }
    }

    @ReactMethod
    public void setUserToken(final String userToken, final Callback successCallback, final Callback errorCallback) {
        if(getCurrentActivity() != null) {
            handleLogin(getCurrentActivity().getApplicationContext(), userToken, successCallback, errorCallback, false);
        }
    }
    
    @ReactMethod
    public void setJWTToken(final String userToken, final Callback successCallback, final Callback errorCallback) {
        if(getCurrentActivity() != null) {
            handleLogin(getCurrentActivity().getApplicationContext(), userToken, successCallback, errorCallback, true);
        }
    }

    @ReactMethod
    public void isUserSignedIn(final Callback callback) {
        if(getCurrentActivity() != null) {
            ZohoDeskPortalSDK deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity().getApplicationContext());
            callback.invoke(deskPortalSDK.isUserSignedIn());
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

    @ReactMethod
    public void enablePush() {
        if(!TextUtils.isEmpty(firebaseInstanceId) && getCurrentActivity() != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    ZohoDeskPortalSDK deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity().getApplicationContext());
                    deskPortalSDK.enablePush(firebaseInstanceId);
                }
            });
        }
    }

    @ReactMethod
    public void disablePush() {
        if(!TextUtils.isEmpty(firebaseInstanceId) && getCurrentActivity() != null) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    ZohoDeskPortalSDK deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity().getApplicationContext());
                    deskPortalSDK.disablePush(firebaseInstanceId);
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
        SharedPreferences sharedPreferences = application.getSharedPreferences("RNZohoDeskASAP", 0);
        final String orgId = sharedPreferences.getString("orgId", "");
        final String appId = sharedPreferences.getString("appId", "");
        final String dcStr = sharedPreferences.getString("dcStr", "");
        if (!TextUtils.isEmpty(orgId) && !TextUtils.isEmpty(appId)) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                public void run() {
                    initialiseDesk(application, orgId, appId, dcStr);
                    ZDPortalConfiguration.handleNotification(application, extras, icon);
                }
            });
        }
    }

    public static void setThemeType(int type) {
        switch (type) {
            case systemTheme: {
                themeType = ZDPThemeType.SYSTEM;
                break;
            }
            case lightTheme: {
                themeType = ZDPThemeType.LIGHT;
                break;
            }
            case darkTheme: {
                themeType = ZDPThemeType.DARK;
                break;
            }
        }
    }

    public static void setThemeBuilder(HashMap<String, String> themeColors, boolean isDarkTheme) {
        ZDPTheme.Builder themeBuilder = new ZDPTheme.Builder(isDarkTheme);
        Set<String> keysSet = themeColors.keySet();
        for (String key: keysSet) {
            switch (key) {
                case colorPrimary: {
                    themeBuilder.setColorPrimary(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case colorPrimaryDark: {
                    themeBuilder.setColorPrimaryDark(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case colorAccent: {
                    themeBuilder.setColorAccent(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case windowBackground: {
                    themeBuilder.setWindowBackground(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case itemBackground: {
                    themeBuilder.setItemBackground(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case textColorPrimary: {
                    themeBuilder.setTextColorPrimary(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case textColorSecondary: {
                    themeBuilder.setTextColorSecondary(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case colorOnPrimary: {
                    themeBuilder.setColorOnPrimary(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case iconTint: {
                    themeBuilder.setIconTint(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case divider: {
                    themeBuilder.setDividerColor(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case listSelector: {
                    themeBuilder.setListSelectorColor(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case hint: {
                    themeBuilder.setHintColor(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case formFieldBorder: {
                    themeBuilder.setFormFieldBorder(Color.parseColor(themeColors.get(key)));
                    break;
                }
                case errorColor: {
                    themeBuilder.setErrorColor(Color.parseColor(themeColors.get(key)));
                    break;
                }
            }
        }
        themeObj = themeBuilder.build();
    }

    @ReactMethod
    public void enableLogs() {
        ZohoDeskPortalSDK.Logger.enableLogs();
    }

    @ReactMethod
    public void disableLogs() {
        ZohoDeskPortalSDK.Logger.disableLogs();
    }

    @ReactMethod
    public void getLayouts(final ReadableMap params,final Callback successCallback,final Callback errorCallback) {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run(){
                ZDPortalCallback.LayoutsCallback layoutsCallback = new ZDPortalCallback.LayoutsCallback() {
                    @Override
                    public void onLayoutsDownloaded(Layouts layoutsList) {
                        WritableArray layoutsArray = Arguments.createArray();
                        for(Layout layout:layoutsList.getData()) {
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(layout);
                        layoutsArray.pushMap(Converter.toWritableMap(jsonString));   
                        }
                        successCallback.invoke(layoutsArray);
                    }
                    @Override
                    public void onException(ZDPortalException exception) {
                        WritableMap errorMap = Arguments.createMap(); 
                        errorMap.putInt("errorCode",exception.getErrorCode());
                        errorMap.putString("errorMsg",exception.getErrorMsg());
                        errorCallback.invoke(errorMap);
                    }
                };
            ZDPortalAPI.getLayouts(layoutsCallback,Converter.convertReadableMapToHashMap(params));
            }
        });  
    }

    @ReactMethod
    public void getDepartments(final Callback successCallback,final Callback errorCallback) {
         Handler handler = new Handler();
         handler.post(new Runnable(){
           @Override
            public void run(){
               ZDPortalCallback.DepartmensCallback departmentsCallback = new ZDPortalCallback.DepartmensCallback() {
                @Override
                public void onDepartmentsDownloaded(DepartmentsList departmentsList) {
                    WritableArray departmentsArray = Arguments.createArray();
                    for(Department department:departmentsList.getData()){
                      WritableMap departmentMap = Arguments.createMap(); 
                      Gson gson = new Gson();
                      String jsonString = gson.toJson(department);
                      departmentsArray.pushMap(Converter.toWritableMap(jsonString));
                    }
                    successCallback.invoke(departmentsArray);

                }
                @Override
                public void onException(ZDPortalException exception) {
                    WritableMap errorMap = Arguments.createMap(); 
                    errorMap.putInt("errorCode",exception.getErrorCode());
                    errorMap.putString("errorMsg",exception.getErrorMsg());
                    errorCallback.invoke(errorMap);
                }
            };
             ZDPortalAPI.getDepartments(departmentsCallback,null);
            }  
         });
    }

    private void handleLogin(Context context, String token, final Callback successCallback, final Callback errorCallback, boolean isJWTToken) {
        Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ZohoDeskPortalSDK deskPortalSDK = ZohoDeskPortalSDK.getInstance(context);
                    ZDPortalCallback.SetUserCallback callback = new ZDPortalCallback.SetUserCallback() {
                        @Override
                        public void onUserSetSuccess() {
                            successCallback.invoke("User set Success");
                        }

                        @Override
                        public void onException(ZDPortalException e) {
                            errorCallback.invoke("User set Failure");
                        }
                    };
                    if(isJWTToken) {
                        deskPortalSDK.loginWithJWTToken(token, callback);
                    } else {
                        deskPortalSDK.loginWithUserToken(token, callback);
                    }
                    
                }
            });
    }

    @ReactMethod
    public void clearDeskPortalData() {
        ZohoDeskPortalSDK deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity().getApplicationContext());
        deskPortalSDK.clearDeskPortalData();
    }

    @ReactMethod
    public void getTicketForm(final ReadableMap params,String flags,final Callback successCallback, final Callback errorCallback){
         Handler handler = new Handler();
         handler.post(new Runnable(){
            @Override
            public void run(){
              ZDPortalCallback.TicketFormCallback ticketFormCallback = new ZDPortalCallback.TicketFormCallback() {
                @Override
                public void onTicketFormDownloaded(TicketForm ticketForm) {
                  Gson gson = new Gson();
                  String jsonString = gson.toJson(ticketForm);
                  successCallback.invoke(Converter.toWritableMap(jsonString));
                }
                @Override
                public void onException(ZDPortalException exception) {
                    WritableMap errorMap = Arguments.createMap(); 
                    errorMap.putInt("errorCode",exception.getErrorCode());
                    errorMap.putString("errorMsg",exception.getErrorMsg());
                    errorCallback.invoke(errorMap);
                }
            };
            ZDPortalTicketsAPI.getTicketForm(ticketFormCallback,Converter.convertReadableMapToHashMap(params),flags);
              }
         });
    }

    @ReactMethod
    public void getTicketFields(final ReadableMap params,String flags,final Callback successCallback, final Callback errorCallback){
         Handler handler = new Handler();
         handler.post(new Runnable(){
            @Override
            public void run(){
              ZDPortalCallback.TicketFieldsCallback  ticketFieldsCallback = new ZDPortalCallback.TicketFieldsCallback() {
                @Override
                public void onTicketFieldsDownloaded(TicketFieldsList ticketFieldsList ) {
                  Gson gson = new Gson();
                  String jsonString = gson.toJson(ticketFieldsList);
                  successCallback.invoke(Converter.toWritableMap(jsonString));
                }
                @Override
                public void onException(ZDPortalException exception) {
                    WritableMap errorMap = Arguments.createMap(); 
                    errorMap.putInt("errorCode",exception.getErrorCode());
                    errorMap.putString("errorMsg",exception.getErrorMsg());
                    errorCallback.invoke(errorMap);
                }
            };
            ZDPortalTicketsAPI.getTicketFields(ticketFieldsCallback,Converter.convertReadableMapToHashMap(params),flags);
              }
         });
    }
}
