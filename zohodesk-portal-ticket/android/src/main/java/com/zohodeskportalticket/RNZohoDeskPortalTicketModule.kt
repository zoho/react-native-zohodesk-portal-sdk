package com.zohodeskportalticket

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.zoho.desk.asap.asap_tickets.ZDPortalTickets;
import com.facebook.react.bridge.ReadableMap
import com.zoho.desk.asap.asap_tickets.utils.ZDPTicketConfiguration
import com.zohodeskportalapikit.Converter.getBooleanOrDefault


class RNZohoDeskPortalTicketModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

 @ReactMethod
  fun show() {
    ZDPortalTickets.show(currentActivity)
  }

  @ReactMethod
  fun updateConfiguration(ticketConfig: ReadableMap) {

    val configuration = ZDPTicketConfiguration.Builder()
      .isReplyAllowed(ticketConfig.getBooleanOrDefault("isReplyAllowed", true))
      .isCommentAllowed(ticketConfig.getBooleanOrDefault("isCommentAllowed", true))
      .isTicketUpdateAllowed(ticketConfig.getBooleanOrDefault("isTicketUpdateAllowed", true))
      .isCommentEditAllowed(ticketConfig.getBooleanOrDefault("isCommentEditAllowed", true))
      .isCommentDeleteAllowed(ticketConfig.getBooleanOrDefault("isCommentDeleteAllowed", true))
      .isAddTicketAllowed(ticketConfig.getBooleanOrDefault("isAddTicketAllowed", true))
      .isHappinessThreadAllowed(ticketConfig.getBooleanOrDefault("isCustomerHappinessThreadAllowed", true))
      .isTicketPropertiesAllowed(ticketConfig.getBooleanOrDefault("isTicketPropertiesAllowed", true))
      .isTicketChannelAllowed(ticketConfig.getBooleanOrDefault("isTicketChannelAllowed", true))
      .isTicketDetailSearchAllowed(!ticketConfig.getBooleanOrDefault("disableTicketDetailSearch", true))
      .build()
    
    ZDPortalTickets.setConfiguration(configuration)
  }

  @ReactMethod
    fun showTicketDetail(ticketId: String){
        ZDPortalTickets.show(currentActivity,ticketId)
    }
    
  companion object {
    const val NAME = "RNZohoDeskPortalTickets"
  }
}
