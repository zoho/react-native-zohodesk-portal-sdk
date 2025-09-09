package com.zohodeskportalchatkit

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import com.zoho.desk.asap.chatkit.ZohoDeskPortalChatKit
import com.google.gson.Gson
import java.util.ArrayList
import java.util.HashMap
import com.google.gson.reflect.TypeToken
import com.zohodeskportalapikit.Converter

class RNZohoDeskPortalChatkitModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  private val reactContext: ReactApplicationContext = reactContext

  override fun getName(): String {
      return "RNZohoDeskPortalChatKit"
  }

  @ReactMethod
  fun clearBM() {
    ZohoDeskPortalChatKit.clearBM(currentActivity)
  }

  @ReactMethod
  fun clearGC() {
    ZohoDeskPortalChatKit.clearGC(currentActivity)
  }

  @ReactMethod
  fun clearAnswerBot() {
    ZohoDeskPortalChatKit.clearAnswerBot(currentActivity)
  }

  @ReactMethod
  fun showGC() {
    ZohoDeskPortalChatKit.showGC(currentActivity)
  }

  @ReactMethod
  fun showAnswerBot() {
    ZohoDeskPortalChatKit.showAnswerBot(currentActivity)
  }

  @ReactMethod
  fun showBM() {
    ZohoDeskPortalChatKit.showBM(currentActivity)
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
      currentActivity,
      sessionVariableConverter(params)
    )
  }

  @ReactMethod
  fun updateGCSessionVariable(params: ReadableArray) {
    ZohoDeskPortalChatKit.updateGCSessionVariable(
      currentActivity,
      sessionVariableConverter(params)
    )
  }

  @ReactMethod
  fun setBMSessionVariable(params: ReadableArray) {
    ZohoDeskPortalChatKit.setBMSessionVariable(
      currentActivity,
      sessionVariableConverter(params)
    )
  }

  @ReactMethod
  fun updateBMSessionVariable(params: ReadableArray) {
    ZohoDeskPortalChatKit.updateBMSessionVariable(
      currentActivity,
      sessionVariableConverter(params)
    )
  }
}
