const {NativeModules} = require('react-native');
const {RNZohoDeskPortalChatKit} = NativeModules;

module.exports = {

    showGC: function() {
        RNZohoDeskPortalChatKit.showGC();
    },
    showAnswerBot : function() {
        RNZohoDeskPortalChatKit.showAnswerBot();
    },
    showBM : function() {
        RNZohoDeskPortalChatKit.showBM();
    },
    setGCSessionVariable: function(sessionVariable) {
        RNZohoDeskPortalChatKit.setGCSessionVariable(sessionVariable);
    },
    updateGCSessionVariable : function(sessionVariable) {
        RNZohoDeskPortalChatKit.updateGCSessionVariable(sessionVariable);
    },
    setBMSessionVariable : function(sessionVariable) {
        RNZohoDeskPortalChatKit.setBMSessionVariable(sessionVariable);
    },
    updateBMSessionVariable: function(sessionVariable) {
        RNZohoDeskPortalChatKit.updateBMSessionVariable(sessionVariable);
    }

}