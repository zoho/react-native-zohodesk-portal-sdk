const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSDK} = NativeModules;
module.exports = {

    initialise: function(orgId, appId, dc) {
      RNZohoDeskPortalSDK.initialise(orgId, appId, dc);
    },

    setUserToken: function(userToken) {
      RNZohoDeskPortalSDK.setUserToken(userToken);
    }
}
