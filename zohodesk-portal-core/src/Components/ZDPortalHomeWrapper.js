const {NativeEventEmitter, NativeModules, DeviceEventEmitter, Platform} = require('react-native');
const {RNZohoDeskPortalHome, RNHomeProviderActionDelegate} = NativeModules;
const eventEmitter = Platform.OS == 'ios' ? new NativeEventEmitter(RNHomeProviderActionDelegate) : DeviceEventEmitter;

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

  setDismissObserver: function(callback){
    RNZohoDeskPortalHome.backActionEvent()
    return eventEmitter.addListener('onBackTap', callback);
  }
}
