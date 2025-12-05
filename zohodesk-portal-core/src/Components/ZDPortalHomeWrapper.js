const {NativeEventEmitter, NativeModules} = require('react-native');
const {RNZohoDeskPortalHome, RNHomeProviderActionDelegate} = NativeModules;

module.exports = {

    show: function() {
      RNZohoDeskPortalHome.show();
    },

    setConfiguration: function(ZDPHomeConfiguration = {}){
      const config = {
        enableCommunity: ZDPHomeConfiguration?.enableCommunity ?? true,
        enableHelpCenter: ZDPHomeConfiguration?.enableHelpCenter ?? true,
        enableMyTicket: ZDPHomeConfiguration?.enableMyTicket ?? true,
        enableCreateTicket: ZDPHomeConfiguration?.enableCreateTicket ?? true,
        enableAddTopic: ZDPHomeConfiguration?.enableAddTopic ?? true,
        showChat: ZDPHomeConfiguration?.showChat ?? true,
        showGC: ZDPHomeConfiguration?.showGC ?? true,
        showAnswerBot: ZDPHomeConfiguration?.showAnswerBot ?? true,
        showBM: ZDPHomeConfiguration?.showBM ?? true
      };
      RNZohoDeskPortalHome.updateConfiguration(config);
  },

  dismissObserver: function() {
    RNHomeProviderActionDelegate.backActionEvent();
  }

}