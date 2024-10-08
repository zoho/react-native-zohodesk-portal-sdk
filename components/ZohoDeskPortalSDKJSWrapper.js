const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSDK} = NativeModules;


module.exports = {

    initialise: function(orgId, appId, dc) {
      RNZohoDeskPortalSDK.initialise(orgId, appId, dc);
    },

    isUserSignedIn: function(callback) {
      RNZohoDeskPortalSDK.isUserSignedIn(callback);
    },

    enablePush: function() {
      RNZohoDeskPortalSDK.enablePush();
    },

    disablePush: function() {
      RNZohoDeskPortalSDK.disablePush();
    },

    logout: function(successCallback, errorCallback) {
      RNZohoDeskPortalSDK.logout(successCallback, errorCallback);
    },

    enableLogs: function() {
      RNZohoDeskPortalSDK.enableLogs();
    },

    disableLogs: function() {
      RNZohoDeskPortalSDK.disableLogs();
    },

    setJWTToken: function(jwtToken, successCallback, errorCallback) {
      RNZohoDeskPortalSDK.setJWTToken(jwtToken, successCallback, errorCallback);
    },

    clearDeskPortalData: function() {
      RNZohoDeskPortalSDK.clearDeskPortalData();
    },

    getDepartments: function(successCallback,errorCallback) {
      RNZohoDeskPortalSDK.getDepartments(successCallback,errorCallback);
    },

    getLayouts: function(params,successCallback,errorCallback) {
      RNZohoDeskPortalSDK.getLayouts(params,successCallback,errorCallback);
    },

    getTicketForm: function(params,featureFlags,successCallback,errorCallback) {
      RNZohoDeskPortalSDK.getTicketForm(params,featureFlags,successCallback,errorCallback);
    },
    
    getTicketFields: function(params, featureFlags, successCallback, errorCallback) {
      RNZohoDeskPortalSDK.getTicketFields(params, featureFlags, successCallback, errorCallback);
    }

}
