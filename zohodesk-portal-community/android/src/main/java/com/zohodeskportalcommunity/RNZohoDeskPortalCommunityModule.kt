package com.zohodeskportalcommunity

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.zoho.desk.asap.asap_community.ZDPortalCommunity
import com.facebook.react.bridge.ReadableMap
import com.zoho.desk.asap.asap_community.utils.ZDPCommunityConfiguration
import com.zohodeskportalapikit.Converter.getBooleanOrDefault

class RNZDPortalCommunityModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun show() {
    ZDPortalCommunity.show(currentActivity);
  }

  @ReactMethod
  fun updateConfiguration(config: ReadableMap) {

    val configuration = ZDPCommunityConfiguration.Builder()
    .isTopicEditAllowed(config.getBooleanOrDefault("isTopicEditAllowed", true))         
    .isTopicDeleteAllowed(config.getBooleanOrDefault("isTopicDeleteAllowed", true))   
    .isReplyAllowed(config.getBooleanOrDefault("isReplyAllowed", true))  
    .isReplyEditAllowed(config.getBooleanOrDefault("isReplyEditAllowed", true))    
    .isReplyDeleteAllowed(config.getBooleanOrDefault("isReplyDeleteAllowed", true))
    .isTopicDetailSearchAllowed(config.getBooleanOrDefault("isTopicDetailSearchAllowed", true))
    .build()

    ZDPortalCommunity.setConfiguration(configuration)
  }

  companion object {
    const val NAME = "RNZohoDeskPortalCommunity"
  }
}
