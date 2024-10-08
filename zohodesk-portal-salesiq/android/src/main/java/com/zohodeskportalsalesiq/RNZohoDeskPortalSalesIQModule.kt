package com.zohodeskportalsalesiq

import android.os.Handler
import android.os.Looper
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.zoho.desk.asap.siq.ZohoDeskPortalSalesIQ

import com.zoho.desk.asap.siq.ZDPortalChatUser

class RNZDPoratalSalesIQModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  private val reactContext: ReactApplicationContext = reactContext

  override fun getName(): String {
    return "RNZohoDeskPortalSalesIQ"
  }
  
  @ReactMethod
  fun show() {
      Handler(Looper.getMainLooper()).post {
        ZohoDeskPortalSalesIQ.show(currentActivity)
      }
  }

  @ReactMethod
  fun setGuestUser(email: String?, name: String?, phone: String?) {
    val user: ZDPortalChatUser = ZDPortalChatUser()
    user.setEmail(email)
    user.setName(name)
    user.setPhone(phone)
    ZohoDeskPortalSalesIQ.setGuestUserDetails(user)
  }
}
