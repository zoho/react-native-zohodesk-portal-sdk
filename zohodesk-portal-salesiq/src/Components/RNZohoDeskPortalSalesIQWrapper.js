const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSalesIQ} = NativeModules;

const RNZohoDeskPortalSalesIQWrapper = {

    show: function() {
      RNZohoDeskPortalSalesIQ.show();
    },

    setGuestUser: function(email, name, phone) {
      RNZohoDeskPortalSalesIQ.setGuestUser(email, name, phone);
    }
}

module.exports = {RNZohoDeskPortalSalesIQWrapper}