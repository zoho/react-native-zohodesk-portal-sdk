package com.zohodeskportalconfiguration

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.zoho.desk.asap.common.ZDPortalConfiguration
import com.zoho.desk.asap.common.utils.ZDPConfiguration
import com.facebook.react.bridge.ReadableMap
import com.zoho.desk.asap.common.utils.ZDPThemeType
import com.zoho.desk.asap.common.utils.ZDPFont
import com.zoho.desk.asap.common.utils.ZDPTheme
import android.graphics.Color
import com.zohodeskportalapikit.Converter.getBooleanOrDefault
import com.zohodeskportalapikit.RNZohoDeskPortalSDK
import android.os.Handler
import android.os.Looper
import android.content.Context
import android.text.TextUtils

class RNZohoDeskPortalConfigurationModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  companion object {
    const val colorPrimary = "colorPrimary"
    const val colorPrimaryDark = "colorPrimaryDark"
    const val colorAccent = "colorAccent"
    const val windowBackground = "windowBackground"
    const val itemBackground = "itemBackground"
    const val textColorPrimary = "textColorPrimary"
    const val textColorSecondary = "textColorSecondary"
    const val colorOnPrimary = "colorOnPrimary"
    const val iconTint = "iconTint"
    const val divider = "divider"
    const val listSelector = "listSelector"
    const val hint = "hint"
    const val formFieldBorder = "formFieldBorder"
    const val errorColor = "errorColor"
    var _lightThemeColors:HashMap<String, String>? = null
    var _darkThemeColors:HashMap<String, String>? = null
           
      val lightTheme = 1
      val darkTheme = 2
      val systemTheme = 3
      private var themeType = ZDPThemeType.SYSTEM

      @JvmStatic
      fun setCustomFont(builder: RNZDPFont){
        ZDPortalConfiguration.setFontBuilder(convertToZDPFont(builder))
      }
  
      private fun convertToZDPFont(rnZDPFont: RNZDPFont): ZDPFont {
        return ZDPFont.Builder()
          .setRegular(rnZDPFont.regular)
          .setLight(rnZDPFont.light)
          .setMedium(rnZDPFont.medium)
          .setThin(rnZDPFont.thin)
          .setBold(rnZDPFont.bold)
          .setBlack(rnZDPFont.black)
          .setSemiBold(rnZDPFont.semibold)
          .setHeave(rnZDPFont.heavy)
          .setUltraLight(rnZDPFont.ultralight)
          .setUnRecognised(rnZDPFont.unrecognised)
          .build()
      }


    @JvmStatic
    fun setThemeBuilder(themeColors: HashMap<String, String>, isDarkTheme: Boolean) {
      if(isDarkTheme) _darkThemeColors = themeColors else _lightThemeColors = themeColors
    }
     
    @JvmStatic
    fun handleNotification(application: Context, extras: Map<String,String>, icon: Int) {
      if (RNZohoDeskPortalSDK.isInitDone) {
          ZDPortalConfiguration.handleNotification(application, extras as MutableMap<Any?, Any?>, icon)
          return
      }
      val sharedPreferences = application.getSharedPreferences("RNZohoDeskASAP", 0)
      val orgId = sharedPreferences.getString("orgId", "")
      val appId = sharedPreferences.getString("appId", "")
      val dcStr = sharedPreferences.getString("dcStr", "")
      if (!TextUtils.isEmpty(orgId) && !TextUtils.isEmpty(appId)) {
          Handler(Looper.getMainLooper()).post {
              RNZohoDeskPortalSDK.initialiseDesk(application, orgId!!, appId!!, dcStr!!)
              ZDPortalConfiguration.handleNotification(application, extras as MutableMap<Any?, Any?>, icon)
          }
      }
    }

  }

  override fun getName(): String {
    return "RNZohoDeskPortalConfiguration"
  }

  @ReactMethod
  fun setThemeType(type: Int) {
    applyThemeColors(_lightThemeColors, false)
     applyThemeColors(_darkThemeColors, true)

    themeType = when (type) {
      lightTheme -> ZDPThemeType.LIGHT
      darkTheme -> ZDPThemeType.DARK
      systemTheme -> ZDPThemeType.SYSTEM
      else -> ZDPThemeType.SYSTEM
    }
    ZDPortalConfiguration.setThemeType(themeType)
  }

    private fun applyThemeColors(colors: Map<String, String>?, isDark: Boolean) {
        colors?.let {
            val themeBuilder = ZDPTheme.Builder(isDark)
            it.forEach { (key, value) ->
                val color = Color.parseColor(value)
                when (key) {
                    colorPrimary -> themeBuilder.setColorPrimary(color)
                    colorPrimaryDark -> themeBuilder.setColorPrimaryDark(color)
                    colorAccent -> themeBuilder.setColorAccent(color)
                    windowBackground -> themeBuilder.setWindowBackground(color)
                    itemBackground -> themeBuilder.setItemBackground(color)
                    textColorPrimary -> themeBuilder.setTextColorPrimary(color)
                    textColorSecondary -> themeBuilder.setTextColorSecondary(color)
                    colorOnPrimary -> themeBuilder.setColorOnPrimary(color)
                    iconTint -> themeBuilder.setIconTint(color)
                    divider -> themeBuilder.setDividerColor(color)
                    listSelector -> themeBuilder.setListSelectorColor(color)
                    hint -> themeBuilder.setHintColor(color)
                    formFieldBorder -> themeBuilder.setFormFieldBorder(color)
                    errorColor -> themeBuilder.setErrorColor(color)
                }
            }
            ZDPortalConfiguration.setThemeBuilder(themeBuilder.build())
        }
    }

  
  @ReactMethod
  fun setSDKLanguage(languageCode: String) {
    ZDPortalConfiguration.setLanguage(languageCode)
  }


  @ReactMethod
  fun setConfiguration(config: ReadableMap){
   val configuration = ZDPConfiguration.Builder()
    .isSideMenuEnabled(!config.getBooleanOrDefault("disableSidemenu", true))         
    .isLangChooserEnabled(!config.getBooleanOrDefault("disableLanguageChooser", true))      
    .isPoweredByFooterEnabled(!config.getBooleanOrDefault("disablePoweredByZoho", true))  
    .isGlobalSearchEnabled(!config.getBooleanOrDefault("disableGlobalSearch", true))    
    .isKBEnabled(!config.getBooleanOrDefault("disableKB", true))              
    .isCommunityEnabled(!config.getBooleanOrDefault("disableCommunity", true))         
    .isSubmitTicketEnabled(!config.getBooleanOrDefault("disableSubmitTicket", true))     
    .isAddTopicEnabled(!config.getBooleanOrDefault("disableAddTopic", true))          
    .isMyTicketsEnabled(!config.getBooleanOrDefault("disableMyTicket", true))       
    .isLiveChatEnabled(!config.getBooleanOrDefault("disableSalesIQ", true))          
    .isChatBotEnabled(!config.getBooleanOrDefault("disableGuidedConversation", true))          
    .isAttachmentDownloadEnabled(!config.getBooleanOrDefault("disableDownloadAttachment", true))
    .isAttachmentUploadEnabled(!config.getBooleanOrDefault("disableUploadAttachment", true))    
    .isModuleBasedSearchEnabled(config.getBooleanOrDefault("enableModuleBasedSearch", true))
    .isAnswerBotEnabled(!config.getBooleanOrDefault("disableAnswerBot", true))         
    .isBusinessMessengerEnabled(!config.getBooleanOrDefault("disableBusinessMessanger", true)) 
    .build()   
    ZDPortalConfiguration.setConfiguration(configuration)

    ZDPortalConfiguration.disableScreenShot(config.getBooleanOrDefault("disableScreenShot", true))
    ZDPortalConfiguration.disableCutCopy(config.getBooleanOrDefault("disableCopyPaste", true))

  }

}