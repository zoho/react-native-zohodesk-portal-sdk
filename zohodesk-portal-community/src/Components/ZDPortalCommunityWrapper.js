const {NativeModules} = require('react-native');
const {RNZohoDeskPortalCommunity} = NativeModules;

const ZDPortalCommunityWrapper  = {
  
    show: function() {
      RNZohoDeskPortalCommunity.show();
    },

    setConfiguration: function(ZDPConfiguration = {}){

      const config = {
        isTopicEditAllowed: ZDPConfiguration?.isTopicEditAllowed ?? true,
        isTopicDeleteAllowed: ZDPConfiguration?.isTopicDeleteAllowed ?? true,
        isReplyAllowed: ZDPConfiguration?.isReplyAllowed ?? true,
        isReplyEditAllowed: ZDPConfiguration?.isReplyEditAllowed ?? true,
        isReplyDeleteAllowed: ZDPConfiguration?.isReplyDeleteAllowed ?? true,
        isTopicDetailSearchAllowed: ZDPConfiguration?.isTopicDetailSearchAllowed ?? true,
      };

      RNZohoDeskPortalCommunity.updateConfiguration(config);
  }
}

module.exports = { ZDPortalCommunityWrapper };