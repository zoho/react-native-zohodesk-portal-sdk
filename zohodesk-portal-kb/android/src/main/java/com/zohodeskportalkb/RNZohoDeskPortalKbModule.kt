package com.zohodeskportalkb

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.zoho.desk.asap.kb.ZDPortalKB;
import com.zoho.desk.asap.kb.utils.ZDPKBConfiguration
import com.zohodeskportalapikit.Converter.getBooleanOrDefault

class RNZohoDeskPortalKbModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "RNZohoDeskPortalKB"
  }

  @ReactMethod
  fun show() {
      ZDPortalKB.show(reactApplicationContext.currentActivity)
  }

  @ReactMethod
  fun showArticle(withPermalink: String) {
    ZDPortalKB.showArticleWithPermalink(reactApplicationContext.currentActivity, withPermalink)
  }

  @ReactMethod
  fun showCategory(withPermalink: String){
    ZDPortalKB.showCategoryWithPermalink(reactApplicationContext.currentActivity, withPermalink)
  }

  @ReactMethod
  fun setConfiguration(config: ReadableMap){
    val configuration = ZDPKBConfiguration.Builder()
      .disableArticleLike(config.getBooleanOrDefault("disableArticleLike",default = false))
      .disableArticleDislike(config.getBooleanOrDefault("disableArticleDislike",default = false))
      .isArticleDetailSearchAllowed(!config.getBooleanOrDefault("disableArticleDetailSearch",default = false))
      .build()
    
    ZDPortalKB.setConfiguration(configuration)
  }

}
