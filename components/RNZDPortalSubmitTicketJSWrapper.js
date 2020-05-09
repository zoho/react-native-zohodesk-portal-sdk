const {NativeModules} = require('react-native');
const {RNZDPortalSubmitTicket} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalSubmitTicket.show();
    }
}
