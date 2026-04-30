package com.zohodeskportalchatkit

import androidx.annotation.ColorInt
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.zoho.desk.asap.chatkit.ZohoDeskPortalChatKit
import com.google.gson.Gson
import java.util.ArrayList
import java.util.HashMap
import com.google.gson.reflect.TypeToken
import com.zoho.desk.asap.chatkit.util.ZDPortalBMConfiguration
import com.zoho.desk.asap.chatkit.util.ZDPortalGCConfiguration
import com.zohodeskportalapikit.Converter
import com.zohodeskportalapikit.Converter.getBooleanOrDefault

class RNZohoDeskPortalChatkitModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  private val reactContext: ReactApplicationContext = reactContext

  companion object{

    @JvmStatic
    fun setNavBarColor(@ColorInt color: Int, isDark: Boolean) {
      ZohoDeskPortalChatKit.setNavBarTextColor(color,isDark)
    }
  }

  override fun getName(): String {
      return "RNZohoDeskPortalChatKit"
  }

  @ReactMethod
  fun clearBM() {
    ZohoDeskPortalChatKit.clearBM(reactApplicationContext.currentActivity)
  }

  @ReactMethod
  fun clearGC() {
    ZohoDeskPortalChatKit.clearGC(reactApplicationContext.currentActivity)
  }

  @ReactMethod
  fun clearAnswerBot() {
    ZohoDeskPortalChatKit.clearAnswerBot(reactApplicationContext.currentActivity)
  }

  @ReactMethod
  fun showGC() {
    ZohoDeskPortalChatKit.showGC(reactApplicationContext.currentActivity)
  }

  @ReactMethod
  fun showAnswerBot() {
    ZohoDeskPortalChatKit.showAnswerBot(reactApplicationContext.currentActivity)
  }

  @ReactMethod
  fun showBM() {
    ZohoDeskPortalChatKit.showBM(reactApplicationContext.currentActivity)
  }


  private fun sessionVariableConverter(params: ReadableArray): ArrayList<HashMap<String, Any>> {
    val jsonObject = Converter.convertReadableArrayToJson(params)
    val gson = Gson()
    val type = object : TypeToken<ArrayList<HashMap<String, Any>>>() {}.type
    return gson.fromJson(jsonObject.toString(), type)
  }

  @ReactMethod
  fun setGCSessionVariable(params: ReadableArray) {
    ZohoDeskPortalChatKit.setGCSessionVariable(
      reactApplicationContext.currentActivity,
      sessionVariableConverter(params)
    )
  }

  @ReactMethod
  fun updateGCSessionVariable(params: ReadableArray) {
    ZohoDeskPortalChatKit.updateGCSessionVariable(
      reactApplicationContext.currentActivity,
      sessionVariableConverter(params)
    )
  }

  @ReactMethod
  fun setBMSessionVariable(params: ReadableArray) {
    ZohoDeskPortalChatKit.setBMSessionVariable(
      reactApplicationContext.currentActivity,
      sessionVariableConverter(params)
    )
  }

  @ReactMethod
  fun updateBMSessionVariable(params: ReadableArray) {
    ZohoDeskPortalChatKit.updateBMSessionVariable(
      reactApplicationContext.currentActivity,
      sessionVariableConverter(params)
    )
  }

  @ReactMethod
  fun hideEndChatPopupWindow(isHide:Boolean){
    ZohoDeskPortalChatKit.hideEndChatPopupWindow(isHide)
  }

  @ReactMethod
  fun setGCConfiguration(config: ReadableMap) {
    val configuration = ZDPortalGCConfiguration.Builder()
      .enableLanguagePicker(config.getBooleanOrDefault("enableLanguagePicker",default = false))
      .build()

    ZohoDeskPortalChatKit.setGCConfiguration(configuration)
  }

  @ReactMethod
  fun setBMConfiguration(config: ReadableMap) {
    val configuration = ZDPortalBMConfiguration.Builder()
      .disableMoreOption(config.getBooleanOrDefault("disableMoreOption", default = false))
      .build()

    ZohoDeskPortalChatKit.setBMConfiguration(configuration)
  }
}
