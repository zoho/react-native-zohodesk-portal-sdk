package com.zohodeskportalcore

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.zoho.desk.asap.ZDPortalHome
import com.zoho.desk.asap.ZDPHomeConfiguration
import com.facebook.react.bridge.ReadableMap
import com.zohodeskportalapikit.Converter.getBooleanOrDefault

class RNZohodeskPortalCoreModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun show() {
      ZDPortalHome.show(reactApplicationContext.currentActivity)
  }


  @ReactMethod
  fun updateConfiguration(homeConfig: ReadableMap) {

    val configuration = ZDPHomeConfiguration.Builder()
    .isKBEnabled(homeConfig.getBooleanOrDefault("enableHelpCenter", true))         
    .isCommunityEnabled(homeConfig.getBooleanOrDefault("enableCommunity", true))   
    .isSubmitTicketEnabled(homeConfig.getBooleanOrDefault("enableCreateTicket", true))  
    .isAddTopicEnabled(homeConfig.getBooleanOrDefault("enableAddTopic", true))    
    .isMyTicketsEnabled(homeConfig.getBooleanOrDefault("enableMyTicket", true))
    .isLiveChatEnabled(homeConfig.getBooleanOrDefault("showChat", true))
    .isChatBotEnabled(homeConfig.getBooleanOrDefault("showGC", true))
    .isAnswerBotEnabled(homeConfig.getBooleanOrDefault("showAnswerBot", true))      
    .isBMEnabled(homeConfig.getBooleanOrDefault("showBM",true))  
    .build()

    ZDPortalHome.setConfiguration(configuration)
  }

  companion object {
    const val NAME = "RNZohoDeskPortalHome"
  }
}
