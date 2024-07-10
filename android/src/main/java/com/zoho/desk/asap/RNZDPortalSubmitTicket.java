package com.zoho.desk.asap;
import com.zoho.desk.asap.Converter;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.zoho.desk.asap.asap_tickets.ZDPortalSubmitTicket;
import org.json.JSONObject;
import org.json.JSONArray;
import com.zoho.desk.asap.RNZDCustomizedTicketForm;
import com.zoho.desk.asap.RNZDVisibleTicketField;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.zoho.desk.asap.asap_tickets.utils.PreFillTicketField;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import java.util.List;
import com.google.gson.reflect.TypeToken;


public class RNZDPortalSubmitTicket extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZDPortalSubmitTicket(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNZDPortalSubmitTicket";
    }

    @ReactMethod
    public void show() {
        ZDPortalSubmitTicket.show(reactContext.getCurrentActivity());
    }

    @ReactMethod
    public void preFillTicketFields(final ReadableArray params) {
       JSONArray jsonObject =  Converter.convertReadableArrayToJson(params);
       Gson gson = new Gson();
       Type type = new TypeToken<List<RNZDCustomizedTicketForm>>() {}.getType();
       List<RNZDCustomizedTicketForm> list =  gson.fromJson(jsonObject.toString(), type);
       for(RNZDCustomizedTicketForm ticketForm : list) {
         ZDPortalSubmitTicket.preFillTicketFields(ticketForm.getCustomizedTicketFields() , ticketForm.getDepartmentId(), ticketForm.getLayoutId()); 
       }
    }

    @ReactMethod
    public void setFieldsListTobeShown(final ReadableArray params){
        JSONArray jsonObject =  Converter.convertReadableArrayToJson(params);
        Gson gson = new Gson();
        Type type = new TypeToken<List<RNZDVisibleTicketField>>() {}.getType();
        List<RNZDVisibleTicketField> list = gson.fromJson(jsonObject.toString(), type);
        for(RNZDVisibleTicketField ticketFieldLists : list) {
         ZDPortalSubmitTicket.setTicketsFieldsListTobeShown(ticketFieldLists.getFieldNames() , ticketFieldLists.getDepartmentId(), ticketFieldLists.getLayoutId()); 
        }
    }
}
