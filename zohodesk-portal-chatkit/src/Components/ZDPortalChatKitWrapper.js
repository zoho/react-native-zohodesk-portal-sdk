const {NativeModules,Platform} = require('react-native');
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
    },
    clearGC: function() {
        RNZohoDeskPortalChatKit.clearGC(); 
    },
    clearBM: function() {
        RNZohoDeskPortalChatKit.clearBM();
    },
    clearAnswerBot: function() {
        RNZohoDeskPortalChatKit.clearAnswerBot();
    },
    hideEndChatPopupWindow:function(isHide) {
        RNZohoDeskPortalChatKit.hideEndChatPopupWindow(isHide);
    },
    setGCConfiguration: function(ZDGCConfiguration={}){
      const config = {
        enableLanguagePicker: ZDGCConfiguration?.enableLanguagePicker ?? false
      };
      RNZohoDeskPortalChatKit.setGCConfiguration(config);
    },
    setBMConfiguration: function(ZDBMConfiguration={}){
      const config = {
        disableMoreOption: ZDBMConfiguration?.disableMoreOption ?? false
      };
      RNZohoDeskPortalChatKit.setBMConfiguration(config);
    }

}