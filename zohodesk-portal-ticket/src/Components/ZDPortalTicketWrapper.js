const {NativeModules} = require('react-native');
const {RNZohoDeskPortalTickets} = NativeModules;

module.exports = {
  show: function() {
    RNZohoDeskPortalTickets.show();
  },

  setConfiguration: function(ZDPTicketConfiguration = {}){
    const config = {
      isReplyAllowed: ZDPTicketConfiguration?.isReplyAllowed ?? true,
      isCommentAllowed: ZDPTicketConfiguration?.isCommentAllowed ?? true,
      isTicketUpdateAllowed: ZDPTicketConfiguration?.isTicketUpdateAllowed ?? true,
      isCommentEditAllowed: ZDPTicketConfiguration?.isCommentEditAllowed ?? true,
      isCommentDeleteAllowed: ZDPTicketConfiguration?.isCommentDeleteAllowed ?? true,
      isAddTicketAllowed: ZDPTicketConfiguration?.isAddTicketAllowed ?? true,
      isCustomerHappinessThreadAllowed: ZDPTicketConfiguration?.isCustomerHappinessThreadAllowed ?? true,
      isTicketChannelAllowed: ZDPTicketConfiguration?.isTicketChannelAllowed ?? true,
      isTicketPropertiesAllowed: ZDPTicketConfiguration?.isTicketPropertiesAllowed ?? true,
      disableTicketDetailSearch: ZDPTicketConfiguration?.disableTicketDetailSearch ?? false
    };
    RNZohoDeskPortalTickets.updateConfiguration(config);
  }
}
