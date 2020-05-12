const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSDK} = NativeModules;
module.exports = {

    initialise: function(orgId, appId, dc) {
      RNZohoDeskPortalSDK.initialise(orgId, appId, dc);
    },

    setUserToken: function(userToken, successCallback, errorCallback) {
      RNZohoDeskPortalSDK.setUserToken(userToken, successCallback, errorCallback);
    },

    logout: function(successCallback, errorCallback) {
      RNZohoDeskPortalSDK.logout(successCallback, errorCallback);
    }
}
