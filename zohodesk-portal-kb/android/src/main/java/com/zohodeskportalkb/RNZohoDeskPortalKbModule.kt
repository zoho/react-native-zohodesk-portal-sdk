package com.zohodeskportalkb

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.zoho.desk.asap.kb.ZDPortalKB;

class RNZohoDeskPortalKbModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "RNZohoDeskPortalKB"
  }

  @ReactMethod
  fun show() {
      ZDPortalKB.show(currentActivity)
  }

  @ReactMethod
  fun showArticle(withPermalink: String) {
    ZDPortalKB.showArticleWithPermalink(currentActivity, withPermalink)
  }

  @ReactMethod
  fun showCategory(withPermalink: String){
    ZDPortalKB.showCategoryWithPermalink(currentActivity, withPermalink)
  }

}
