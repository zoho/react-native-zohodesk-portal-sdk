package com.zohodeskportalcore


import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule

class RNHomeBackEventEmitterModule (reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val reactContext: ReactApplicationContext = reactContext

    override fun getName(): String {
        return "RNHomeBackEventEmitter"
    }

    //  emit events to JavaScript
    private fun sendEvent(eventName: String, message: String) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            ?.emit(eventName, message)
    }

    @ReactMethod
    fun emitBackTapEvent(successMessage: String) {
        sendEvent("onBackTap", successMessage)
    }
}