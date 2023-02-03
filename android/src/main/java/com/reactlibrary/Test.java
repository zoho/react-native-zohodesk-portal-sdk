package com.reactlibrary;

import java.util.HashMap;

public class Test {


    public Test() {
        HashMap<String, String> themeSetup = new HashMap<>();
        themeSetup.put(RNZohodeskPortalSDK.colorPrimary, "#964B00");
        themeSetup.put(RNZohodeskPortalSDK.colorPrimaryDark, "#63351D");
        themeSetup.put(RNZohodeskPortalSDK.colorAccent, "#63351D");
        themeSetup.put(RNZohodeskPortalSDK.textColorPrimary, "#C0C0C0");
        RNZohodeskPortalSDK.setThemeBuilder(themeSetup, false);
    }
}
