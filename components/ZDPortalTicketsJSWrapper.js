const {NativeModules} = require('react-native');
const {RNZDPortalTickets} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalTickets.show();
    },
    getTicketForm: function(params,flags,successCallback,errorCallback) {
      RNZDPortalTickets.getTicketForm(params,flags,successCallback,errorCallback);
    },
    getTicketFields: function(params,flags,successCallback,errorCallback) {
      RNZDPortalTickets.getTicketFields(params,flags,successCallback,errorCallback);
    }

}
