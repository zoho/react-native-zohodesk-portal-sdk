const {NativeModules} = require('react-native');
const {RNZDPortalCommunity} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalCommunity.show();
    }
}
