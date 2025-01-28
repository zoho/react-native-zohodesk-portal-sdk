package com.zohodeskportalsalesiq

import android.os.Handler
import android.os.Looper
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

import com.zoho.desk.asap.siq.Component
import com.zoho.desk.asap.siq.ZDPortalChatUser
import com.zoho.desk.asap.siq.ZohoDeskPortalSalesIQ
import com.facebook.react.bridge.Callback
import com.zoho.desk.asap.siq.SalesIQInitCallback

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

  @ReactMethod
  fun setChatVisibility(component: String, visible: Boolean) {
    val chatComponent = mapStringToChatComponent(component)
    if (chatComponent == null) {
      return
    }
    ZohoDeskPortalSalesIQ.setChatVisibility(chatComponent, visible)
  }

  @ReactMethod
  fun setConversationTitle(title: String){
    ZohoDeskPortalSalesIQ.setConversationTitle(title)
  }

  @ReactMethod
  fun setOfflineMessage(message: String){
    ZohoDeskPortalSalesIQ.setOfflineMessage(message)
  }

  @ReactMethod
  fun setKnowledgeBaseVisibility(enable: Boolean){
    ZohoDeskPortalSalesIQ.setKnowledgeBaseVisibility(enable)
  }

  @ReactMethod
  fun enableDragToDismiss(enable: Boolean){
    ZohoDeskPortalSalesIQ.enableDragToDismiss(enable)
  }

  @ReactMethod
  fun setConversationVisibility(enable: Boolean){
    ZohoDeskPortalSalesIQ.setConversationVisibility(enable)
  }

  @ReactMethod
  fun showFeedbackAfterSkip(show: Boolean){
    ZohoDeskPortalSalesIQ.showFeedbackAfterSkip(show)
  }

  @ReactMethod
  fun hideQueueTime(show: Boolean){
    ZohoDeskPortalSalesIQ.hideQueueTime(show)
  }

  @ReactMethod
  fun setLoggerEnabled(enable: Boolean){
    ZohoDeskPortalSalesIQ.setLoggerEnabled(enable)
  }

  @ReactMethod
  fun showOfflineMessage(show: Boolean){
    ZohoDeskPortalSalesIQ.showOfflineMessage(show)
  }

  @ReactMethod
  fun showLauncher(mode: String){
    ZohoDeskPortalSalesIQ.setLauncherVisibility(mapStringToVisibilityMode(mode))
  }
  
  @ReactMethod
  fun setCallbackOnInit() {
    val eventEmitter = RNSIQEventEmitterModule(reactContext)
    Handler(Looper.getMainLooper()).post {
      val initCallback = object : SalesIQInitCallback {
        override fun onInitialized() {
          eventEmitter.emitSuccessEvent("success")
        }
        override fun onException(code: Int, message: String?) {
          eventEmitter.emitErrorEvent("failed")
        }
      }
      ZohoDeskPortalSalesIQ.setSalesIQInitCallback(initCallback)
    }
  }

  private  fun mapStringToVisibilityMode(mode: String): LauncherMode{
    return when (mode){
      "always" -> LauncherMode.ALWAYS_VISIBLE
      "never" -> LauncherMode.NEVER_VISIBLE
      "whenActiveChat" -> LauncherMode.VISIBLE_WHEN_ACTIVE_CHAT
      else -> LauncherMode.NEVER_VISIBLE
    }
  }

  private fun mapStringToChatComponent(component: String): Component? {
    return when (component) {
      "preChatForm" -> Component.PRECHAT_FORM
      "visitorName" -> Component.VISITOR_NAME
      "screenshotOption" -> Component.SCREENSHOT
      "mediaCapture" -> Component.MEDIA_CAPTURE
      "fileSharing" -> Component.FILE_SHARE
      "emailTranscript" -> Component.EMAIL_TRANSCRIPT
      "rating" -> Component.RATING
      "feedback" -> Component.FEEDBACK
      "reopen" -> Component.REOPEN
      "end" -> Component.END
      "endWhenInQueue" -> Component.END_WHEN_IN_QUEUE
      "endWhenBotConnected" -> Component.END_WHEN_BOT_CONNECTED
      "endWhenOperatorConnected" -> Component.END_WHEN_OPERATOR_CONNECTED
      "queuePosition" -> Component.QUEUE_POSITION
      else -> null
    }
  }

}
