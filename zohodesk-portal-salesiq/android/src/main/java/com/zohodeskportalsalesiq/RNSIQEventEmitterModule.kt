package com.zohodeskportalsalesiq

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule

class RNSIQEventEmitterModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val reactContext: ReactApplicationContext = reactContext

    override fun getName(): String {
        return "RNSIQEventEmitter"
    }

    //  emit events to JavaScript
    private fun sendEvent(eventName: String, message: String) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            ?.emit(eventName, message)
    }

    // emit a success event
    @ReactMethod
    fun emitSuccessEvent(successMessage: String) {
        sendEvent("onSalesIQInitSuccess", successMessage)
    }

    // emit an error event
    @ReactMethod
    fun emitErrorEvent(errorMessage: String) {
        sendEvent("onSalesIQInitError", errorMessage)
    }
}
