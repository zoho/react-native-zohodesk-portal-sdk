const {NativeModules} = require('react-native');
const {RNZDPortalTickets} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalTickets.show();
    }
}
