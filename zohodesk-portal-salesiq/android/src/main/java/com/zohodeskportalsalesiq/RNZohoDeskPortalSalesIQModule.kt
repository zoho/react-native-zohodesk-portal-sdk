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
import com.zoho.desk.asap.siq.LauncherMode
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
        ZohoDeskPortalSalesIQ.show(reactApplicationContext.currentActivity)
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
    // One JS component can map to several native components (e.g. mediaCapture -> photo + video)
    mapStringToChatComponents(component).forEach { chatComponent ->
      ZohoDeskPortalSalesIQ.setChatVisibility(chatComponent, visible)
    }
  }

  @ReactMethod
  fun setConversationTitle(title: String){
    ZohoDeskPortalSalesIQ.setConversationTitle(title)
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

  private fun mapStringToChatComponents(component: String): List<Component> {
    return when (component) {
      "preChatForm" -> listOf(Component.PRECHAT_FORM)
      "visitorName" -> listOf(Component.VISITOR_NAME)
      "screenshotOption" -> listOf(Component.SCREENSHOT)
      // asap-siq 4.6.16 split MEDIA_CAPTURE into TAKE_PHOTO and RECORD_VIDEO
      "mediaCapture" -> listOf(Component.TAKE_PHOTO, Component.RECORD_VIDEO)
      "photoLibrary" -> listOf(Component.GALLERY)
      "fileSharing" -> listOf(Component.FILE_SHARE)
      "emailTranscript" -> listOf(Component.EMAIL_TRANSCRIPT)
      "rating" -> listOf(Component.RATING)
      "feedback" -> listOf(Component.FEEDBACK)
      "reopen" -> listOf(Component.REOPEN)
      "end" -> listOf(Component.END)
      "endWhenInQueue" -> listOf(Component.END_WHEN_IN_QUEUE)
      "endWhenBotConnected" -> listOf(Component.END_WHEN_BOT_CONNECTED)
      "endWhenOperatorConnected" -> listOf(Component.END_WHEN_OPERATOR_CONNECTED)
      "queuePosition" -> listOf(Component.QUEUE_POSITION)
      else -> emptyList()
    }
  }

  @ReactMethod
  fun setChatBrandDetails(appKey: String,accessKey: String) {
      ZohoDeskPortalSalesIQ.setChatBrandDetails(reactApplicationContext.applicationContext,appKey,accessKey);
  }

  @ReactMethod
  fun syncThemeWithOSForAndroid(sync: Boolean) {
    ZohoDeskPortalSalesIQ.syncThemeWithOS(sync);
  }

  @ReactMethod
  fun setThemeForAndroid(themeName: String) {
    val themeResId = reactContext.resources.getIdentifier(themeName, "style", reactContext.packageName)
    if (themeResId != 0) {
        ZohoDeskPortalSalesIQ.setTheme(themeResId);
    } else {
        
    }
  }
}
