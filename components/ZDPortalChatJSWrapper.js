const {NativeModules} = require('react-native');
const {RNZDPortalChat} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalChat.show();
    },

    setGuestUserDetails: function(email, name, phone) {
      RNZDPortalChat.setGuestUserDetails(email, name, phone);
    }
}
