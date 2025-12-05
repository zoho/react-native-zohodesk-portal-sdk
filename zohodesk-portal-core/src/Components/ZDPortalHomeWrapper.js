const {NativeEventEmitter, NativeModules} = require('react-native');
const {RNZohoDeskPortalHome, RNHomeProviderActionDelegate} = NativeModules;
const eventEmitter = new NativeEventEmitter(RNHomeProviderActionDelegate);

let dismissObserver = null;

module.exports = {

    show: function() {
      RNZohoDeskPortalHome.show();
    },

    setConfiguration: function(ZDPHomeConfiguration = {}){
      const config = {
        enableHeaderLogo: ZDPHomeConfiguration?.enableHeaderLogo ?? true,
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

  setDismissObserver: function(callback) {
    dismissObserver = callback;
  }

}

eventEmitter.addListener("onBackTap", (event) => {
  if (dismissObserver && typeof dismissObserver === 'function') {
    dismissObserver();
  }
});