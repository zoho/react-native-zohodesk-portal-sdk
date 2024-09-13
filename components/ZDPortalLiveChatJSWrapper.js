const {NativeModules} = require('react-native');
const {RNZDPortalLiveChat} = NativeModules;
module.exports = {

    showGC: function() {
      RNZDPortalLiveChat.showGC();
    },
    showAnswerBot : function() {
      RNZDPortalLiveChat.showAnswerBot();
    },
    showBM : function() {
      RNZDPortalLiveChat.showBM();
    },
    setGCSessionVariable: function(sessionVariable) {
      RNZDPortalLiveChat.setGCSessionVariable(sessionVariable);
    },
    updateGCSessionVariable : function(sessionVariable) {
      RNZDPortalLiveChat.updateGCSessionVariable(sessionVariable);
    },
    setBMSessionVariable : function(sessionVariable) {
      RNZDPortalLiveChat.setBMSessionVariable(sessionVariable);
    },
    updateBMSessionVariable: function(sessionVariable) {
      RNZDPortalLiveChat.updateBMSessionVariable(sessionVariable);
    }

}
