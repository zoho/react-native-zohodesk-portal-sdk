const {NativeModules} = require('react-native');
const {RNZDPortalChat} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalChat.show();
    }
}
