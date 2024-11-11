package com.zohodeskportalticket

import android.os.Handler
import android.os.Looper
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.zoho.desk.asap.asap_tickets.ZDPortalSubmitTicket;
import com.zoho.desk.asap.asap_tickets.utils.PreFillTicketField

import com.facebook.react.bridge.ReadableArray
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zoho.desk.asap.api.ZDPortalCallback
import com.zoho.desk.asap.api.ZDPortalException
import com.zoho.desk.asap.api.response.Ticket
import java.lang.reflect.Field
import com.zohodeskportalapikit.Converter

class RNZohoDeskPortalSubmitTicketModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

 @ReactMethod
  fun show() {
      ZDPortalSubmitTicket.show(currentActivity)
  }



  @ReactMethod
  fun preFillTicketFields(params: ReadableArray) {
    val jsonObject = Converter.convertReadableArrayToJson(params)
    val gson = Gson()
    val type = object : TypeToken<List<RNZDCustomizedTicketForm>>() {}.type
    val list: List<RNZDCustomizedTicketForm> = gson.fromJson(jsonObject.toString(), type)

    for (ticketForm in list) {
      val customizedTicketFields: List<PreFillTicketField> = ticketForm.customizedTicketFields.toList()

      ZDPortalSubmitTicket.preFillTicketFields(
        customizedTicketFields,            
        ticketForm.departmentId,          
        ticketForm.layoutId                 
      )
    }
  }

  @ReactMethod
  fun setFieldsListTobeShown(params: ReadableArray) {
    val jsonObject = Converter.convertReadableArrayToJson(params)
    val gson = Gson()
    val type = object : TypeToken<List<RNZDVisibleTicketField>>() {}.type
    val list: List<RNZDVisibleTicketField> = gson.fromJson(jsonObject.toString(), type)


    for (ticketFieldLists in list) {
     
      val fieldNames: List<String> = ticketFieldLists.fieldNames.toList()

      ZDPortalSubmitTicket.setTicketsFieldsListTobeShown(
        fieldNames,           
        ticketFieldLists.departmentId,
        ticketFieldLists.layoutId
      )
    }
  }

  @ReactMethod
  fun getCallbackOnCreate(successCallback: Callback, errorCallback: Callback) {
    Handler(Looper.getMainLooper()).post {
      val createTicketCallback = object : ZDPortalCallback.CreateTicketCallback {
        override fun onTicketCreated(ticket: Ticket) {
          val gson = Gson()
          val jsonString = gson.toJson(ticket)
          successCallback.invoke(jsonString)
        }

        override fun onException(exception: ZDPortalException) {
          val errorMap = Arguments.createMap()
          errorMap.putInt("errorCode", exception.errorCode)
          errorMap.putString("errorMsg", exception.errorMsg)
          errorCallback.invoke(errorMap)
        }
      }
      ZDPortalSubmitTicket.setCreateTicketCallback(createTicketCallback)
    }
  }
 

  companion object {
    const val NAME = "RNZohoDeskPortalSubmitTicket"
  }
}