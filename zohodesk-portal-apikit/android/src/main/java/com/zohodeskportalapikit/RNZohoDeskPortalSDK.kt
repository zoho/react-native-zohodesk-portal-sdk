package com.zohodeskportalapikit

import android.content.Context

import android.os.Handler
import android.os.Looper
import android.text.TextUtils

import java.io.*
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.Arguments
import com.google.gson.Gson
import com.zoho.desk.asap.api.ZDPortalCallback
import com.zoho.desk.asap.api.ZDPortalException
import com.zoho.desk.asap.api.ZohoDeskPortalSDK
import com.zoho.desk.asap.api.ZDPortalAPI
import com.zoho.desk.asap.api.response.Layouts
import com.zoho.desk.asap.api.util.ZohoDeskAPIImpl
import com.zoho.desk.asap.api.response.DepartmentsList
import com.zoho.desk.asap.api.ZDPortalCommunityAPI
import com.zoho.desk.asap.api.response.DeskTopicsList
import com.zoho.desk.asap.api.response.ProductsList
import  com.zoho.desk.asap.api.response.ASAPAttachmentUploadResponse
import com.zoho.desk.asap.api.ZDPortalTicketsAPI
import com.zoho.desk.asap.api.response.DeskUserProfile
import com.zoho.desk.asap.api.response.TicketFieldsList
import com.zoho.desk.asap.api.response.TicketForm
import com.zoho.desk.asap.api.response.Ticket
import java.util.HashMap

class RNZohoDeskPortalSDK(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    companion object {
        private var firebaseInstanceId: String? = null
        var isInitDone = false

        fun initialiseDesk(application: Context, orgId: String, appId: String, dcStr: String) {
            isInitDone = true
            val preferences = application.getSharedPreferences("RNZohoDeskASAP", 0)
            val editor = preferences.edit()
            editor.putString("orgId", orgId)
            editor.putString("appId", appId)
            editor.putString("dcStr", dcStr)
            editor.apply()
            val portalSDK = ZohoDeskPortalSDK.getInstance(application)
            ZohoDeskAPIImpl.setRefererHeader("react-native")
            var dc = ZohoDeskPortalSDK.DataCenter.US
            when (dcStr.toLowerCase()) {
                "eu" -> dc = ZohoDeskPortalSDK.DataCenter.EU
                "cn" -> dc = ZohoDeskPortalSDK.DataCenter.CN
                "in" -> dc = ZohoDeskPortalSDK.DataCenter.IN
                "au" -> dc = ZohoDeskPortalSDK.DataCenter.AU
                "jp" -> dc = ZohoDeskPortalSDK.DataCenter.JP
                "ca" -> dc = ZohoDeskPortalSDK.DataCenter.CA
                "sa" -> dc = ZohoDeskPortalSDK.DataCenter.SA
            }
            portalSDK.initDesk(orgId.toLong(), appId, dc)
        }

    

        fun setFirebaseId(firebaseId: String) {
            firebaseInstanceId = firebaseId
        }
    }

    override fun getName(): String {
        return "RNZohoDeskPortalSDK"
    }

    @ReactMethod
    fun initialise(orgId: String, appId: String, dcStr: String) {
        getCurrentActivity()?.let {
            Handler(Looper.getMainLooper()).post {
                initialiseDesk(it.application, orgId, appId, dcStr)
            }
        }
    }

    @ReactMethod
    fun setUserToken(userToken: String, successCallback: Callback, errorCallback: Callback) {
        getCurrentActivity()?.let {
            handleLogin(it.applicationContext, userToken, successCallback, errorCallback, false)
        }
    }

    @ReactMethod
    fun loginWithJWTToken(userToken: String, successCallback: Callback, errorCallback: Callback) {
        getCurrentActivity()?.let {
            handleLogin(it.applicationContext, userToken, successCallback, errorCallback, true)
        }
    }

    @ReactMethod
    fun isUserSignedIn(callback: Callback) {
        getCurrentActivity()?.let {
            val deskPortalSDK = ZohoDeskPortalSDK.getInstance(it.applicationContext)
            callback.invoke(deskPortalSDK.isUserSignedIn())
        }
    }

    
    @ReactMethod
    fun logout(successCallback: Callback, errorCallback: Callback) {
        getCurrentActivity()?.let {
            Handler().post {
                val deskPortalSDK = ZohoDeskPortalSDK.getInstance(it.applicationContext)
                deskPortalSDK.logout(object : ZDPortalCallback.LogoutCallback {
                    override fun onLogoutSuccess() {
                        successCallback.invoke("Logout Success")
                    }

                    override fun onException(e: ZDPortalException) {
                        errorCallback.invoke("Logout Failure")
                    }
                })
            }
        }
    }

    @ReactMethod
    fun enablePush() {
        if (!TextUtils.isEmpty(firebaseInstanceId) && getCurrentActivity() != null) {
            Handler(Looper.getMainLooper()).post {
                val deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity()!!.applicationContext)
                deskPortalSDK.enablePush(firebaseInstanceId!!)
            }
        }
    }

    @ReactMethod
    fun disablePush() {
        if (!TextUtils.isEmpty(firebaseInstanceId) && getCurrentActivity() != null) {
            Handler(Looper.getMainLooper()).post {
                val deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity()!!.applicationContext)
                deskPortalSDK.disablePush(firebaseInstanceId!!)
            }
        }
    }

    @ReactMethod
    fun enableLogs() {
        ZohoDeskPortalSDK.Logger.enableLogs()
    }

    @ReactMethod
    fun disableLogs() {
        ZohoDeskPortalSDK.Logger.disableLogs()
    }

    @ReactMethod
    fun getLayouts(params: ReadableMap, successCallback: Callback, errorCallback: Callback) {
        Handler(Looper.getMainLooper()).post {
            val layoutsCallback = object : ZDPortalCallback.LayoutsCallback {
                override fun onLayoutsDownloaded(layoutsList: Layouts) {
                    val gson = Gson()
                    val jsonString = gson.toJson(layoutsList.data)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            ZDPortalAPI.getLayouts(layoutsCallback, Converter.convertReadableMapToHashMap(params))
        }
    }

    @ReactMethod
    fun getDepartments(successCallback: Callback, errorCallback: Callback) {
        Handler(Looper.getMainLooper()).post {
            val departmentsCallback = object : ZDPortalCallback.DepartmensCallback {
                override fun onDepartmentsDownloaded(departmentsList: DepartmentsList) {
                    val gson = Gson()
                    val jsonString = gson.toJson(departmentsList.data)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            ZDPortalAPI.getDepartments(departmentsCallback, null)
        }
    }

    private fun handleLogin(context: Context, token: String, successCallback: Callback, errorCallback: Callback, isJWTToken: Boolean) {
        Handler(Looper.getMainLooper()).post {
            val deskPortalSDK = ZohoDeskPortalSDK.getInstance(context)
            val callback = object : ZDPortalCallback.SetUserCallback {
                override fun onUserSetSuccess() {
                    successCallback.invoke("User set Success")
                }

                override fun onException(e: ZDPortalException) {
                    errorCallback.invoke("User set Failure")
                }
            }
            if (isJWTToken) {
                deskPortalSDK.loginWithJWTToken(token, callback)
            } else {
                deskPortalSDK.loginWithUserToken(token, callback)
            }
        }
    }

    @ReactMethod
    fun clearData() {
        val deskPortalSDK = ZohoDeskPortalSDK.getInstance(getCurrentActivity()!!.applicationContext)
        deskPortalSDK.clearDeskPortalData()
    }

    @ReactMethod
    fun getMostDiscussedTopics(params: ReadableMap, successCallback: Callback, errorCallback: Callback) {
        Handler(Looper.getMainLooper()).post {
            val mostDiscussedTopicsCallback = object : ZDPortalCallback.CommunityTopicsCallback {
                override fun onCommunityTopicsDownloaded(topicsList: DeskTopicsList) {
                    val gson = Gson()
                    val jsonString = gson.toJson(topicsList.data)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            ZDPortalCommunityAPI.getMostDiscussedTopics(mostDiscussedTopicsCallback, Converter.convertReadableMapToHashMap(params))
        }
    }

    @ReactMethod
    fun getMostPopularTopics(params: ReadableMap, successCallback: Callback, errorCallback: Callback){
        Handler(Looper.getMainLooper()).post {
            val mostPopularTopicsCallback = object : ZDPortalCallback.CommunityTopicsCallback {
                override fun onCommunityTopicsDownloaded(topicsList: DeskTopicsList) {
                    val gson = Gson()
                    val jsonString = gson.toJson(topicsList.data)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            ZDPortalCommunityAPI.getMostPopularTopics(mostPopularTopicsCallback,Converter.convertReadableMapToHashMap(params))
        }
    }


    @ReactMethod
    fun getProducts(departmentId: String, params: ReadableMap, successCallback: Callback, errorCallback: Callback) {
        Handler(Looper.getMainLooper()).post {
            val productsCallback = object : ZDPortalCallback.ProductsCallback {
                override fun onProductsDownloaded(productList: ProductsList) {
                    val gson = Gson()
                    val jsonString = gson.toJson(productList.data)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            val paramsMap = Converter.convertReadableMapToHashMap(params)

            // Add departmentId to the HashMap
            paramsMap["departmentId"] = departmentId
            ZDPortalAPI.getProductsList(productsCallback, paramsMap)
        }
    }

    @ReactMethod
    fun addAttachments(fileName: String, fileData: String, successCallback: Callback, errorCallback: Callback) {
        Handler(Looper.getMainLooper()).post {
            try {
                   // Create a temporary file in the app's internal storage
                   val tempFile = File(reactApplicationContext.filesDir, fileName)

                   // Write the byte array to the file
                   tempFile.writeBytes(fileData.toByteArray())
    
                // Callback to handle the upload response
                val attachmentCallback = object : ZDPortalCallback.UploadAttachmentCallback {
                    override fun onAttachmentUploaded(attachment: ASAPAttachmentUploadResponse) {
                        val gson = Gson()
                        val jsonString = gson.toJson(attachment)
                        // Optionally, delete the temp file after uploading
                        tempFile.delete()
                        successCallback.invoke(jsonString)
                    }
    
                    override fun attachProgress(progress: Float) {
                        // Invoke progress callback (if needed) - can be added if a progress callback exists
                    }
    
                    override fun onException(exception: ZDPortalException) {
                        // Handle error and invoke error callback
                        val errorMap = Arguments.createMap().apply {
                            putInt("errorCode", exception.errorCode)
                            putString("errorMsg", exception.errorMsg)
                        }
                        errorCallback.invoke(errorMap)
    
                        // Optionally, delete the temp file if there's an error
                        tempFile.delete()
                    }
                }
    
                // Prepare any additional headers (if required by the API)
                val headers = HashMap<String, String>()
                // Call the API to upload the attachment
                ZDPortalTicketsAPI.uploadAttachment(attachmentCallback, tempFile, headers)
    
            } catch (e: IOException) {
                // Handle any file writing error and invoke the error callback
                val errorMap = Arguments.createMap().apply {
                    putInt("errorCode", -1) // Custom error code for IO error
                    putString("errorMsg", e.localizedMessage)
                }
                errorCallback.invoke(errorMap)
            }
        }
    }
    

    @ReactMethod
    fun updateUserInformation(params: ReadableMap, successCallback: Callback, errorCallback: Callback) {
        Handler(Looper.getMainLooper()).post {
            val createTicketCallback = object : ZDPortalCallback.UserDetailsCallback {
                override fun onUserDetailsSuccess(userProfile: DeskUserProfile) {
                    val gson = Gson()
                    val jsonString = gson.toJson(userProfile)
                    successCallback.invoke(jsonString)
                }  

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }

            // Convert ReadableMap to HashMap to pass it to the API
            val paramsHashMap = Converter.convertReadableMapToHashMap(params)

            // Call the updateProfileDetails API
            ZDPortalAPI.updateProfileDetails(createTicketCallback, paramsHashMap)
        }
    }


    @ReactMethod
    fun getTicketForm(params: ReadableMap, flags: String, successCallback: Callback, errorCallback: Callback) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val ticketFormCallback = object : ZDPortalCallback.TicketFormCallback {
                override fun onTicketFormDownloaded(ticketForm: TicketForm) {
                    val gson = Gson()
                    val jsonString = gson.toJson(ticketForm.form)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            ZDPortalTicketsAPI.getTicketForm(ticketFormCallback, Converter.convertReadableMapToHashMap(params), flags)
        }
    }

    @ReactMethod
    fun getTicketFields(params: ReadableMap, flags: String, successCallback: Callback, errorCallback: Callback) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val ticketFieldsCallback = object : ZDPortalCallback.TicketFieldsCallback {
                override fun onTicketFieldsDownloaded(ticketFieldsList: TicketFieldsList) {
                    val gson = Gson()
                    val jsonString = gson.toJson(ticketFieldsList.data)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            ZDPortalTicketsAPI.getTicketFields(ticketFieldsCallback, Converter.convertReadableMapToHashMap(params), flags)
        }
    }

    @ReactMethod
    fun createTicket(params: ReadableMap, successCallback: Callback, errorCallback: Callback) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            val createTicketCallback = object  : ZDPortalCallback.CreateTicketCallback{
                override  fun  onTicketCreated(ticketData: Ticket){
                    val gson = Gson()
                    val jsonString = gson.toJson(ticketData)
                    successCallback.invoke(jsonString)
                }

                override fun onException(exception: ZDPortalException) {
                    val errorMap = Arguments.createMap()
                    errorMap.putInt("errorCode", exception.errorCode)
                    errorMap.putString("errorMsg", exception.errorMsg)
                    errorCallback.invoke(errorMap)
                }
            }
            val headers = HashMap<String, String>()
            ZDPortalTicketsAPI.createTicket(createTicketCallback,Converter.convertReadableMapToHashMapAny(params),headers)
        }
    }
}

