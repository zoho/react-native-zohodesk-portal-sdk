const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSDK} = NativeModules;
module.exports = {

    initialise: function(orgId, appId, dc) {
      RNZohoDeskPortalSDK.initialise(orgId, appId, dc);
    },

    setUserToken: function(userToken, successCallback, errorCallback) {
      RNZohoDeskPortalSDK.setUserToken(userToken, successCallback, errorCallback);
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
    }
}
