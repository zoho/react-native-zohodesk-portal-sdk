const {NativeModules, NativeEventEmitter, DeviceEventEmitter, Platform} = require('react-native');
const {RNZohoDeskPortalSalesIQ, RNSIQEventEmitter} = NativeModules;

const salesIQEventEmitter = Platform.OS == 'ios' ? new NativeEventEmitter(RNSIQEventEmitter) : DeviceEventEmitter;

const RNZohoDeskPortalSalesIQWrapper = {

    show: function() {
      RNZohoDeskPortalSalesIQ.show();
    },

    setGuestUser: function(email, name, phone) {
      RNZohoDeskPortalSalesIQ.setGuestUser(email, name, phone);
    },

    setChatVisibility: function(component,visible){
      RNZohoDeskPortalSalesIQ.setChatVisibility(component,visible);
    },

    setConversationTitle: function(title){
      RNZohoDeskPortalSalesIQ.setConversationTitle(title);
    },

    setKnowledgeBaseVisibility: function(enable){
      RNZohoDeskPortalSalesIQ.setKnowledgeBaseVisibility(enable);
    },

    enableDragToDismiss: function(enable){
      RNZohoDeskPortalSalesIQ.enableDragToDismiss(enable);
    },

    setConversationVisibility: function(visible){
      RNZohoDeskPortalSalesIQ.setConversationVisibility(visible);
    },

    showFeedbackAfterSkip: function(show){
      RNZohoDeskPortalSalesIQ.showFeedbackAfterSkip(show);
    },

    hideQueueTime: function(enable){
      RNZohoDeskPortalSalesIQ.hideQueueTime(enable);
    },

    setLoggerEnabled: function(enable){
      RNZohoDeskPortalSalesIQ.setLoggerEnabled(enable);
    },

    showOfflineMessage: function(show){
      RNZohoDeskPortalSalesIQ.showOfflineMessage(show);
    },
    
    showLauncher: function(mode){
      RNZohoDeskPortalSalesIQ.showLauncher(mode);
    },
    
    addSuccessInitListener: function(callback){
      RNZohoDeskPortalSalesIQ.setCallbackOnInit()
      return salesIQEventEmitter.addListener('onSalesIQInitSuccess', callback);
    },

    addErrorInitListener: function(callback){
      return salesIQEventEmitter.addListener('onSalesIQInitError', callback);
    },

    setThemeForAndroid: function(theme) {
      RNZohoDeskPortalSalesIQ.setThemeForAndroid(theme);
    },

    syncThemeWithOSForAndroid: function(sync) {
      RNZohoDeskPortalSalesIQ.syncThemeWithOSForAndroid(sync);
    },

    visibilityMode: {
      ALWAYS: "always",
      NEVER: "never",
      WHENACTIVECHAT: "whenActiveChat"
    },

    chatComponents: {
      PRE_CHAT_FORM: "preChatForm",
      VISITOR_NAME: "visitorName",
      SCREENSHOT_OPTION: "screenshotOption",
      PHOTO_LIBRARY: "photoLibrary",
      MEDIA_CAPTURE: "mediaCapture",
      FILE_SHARING: "fileSharing",
      EMAIL_TRANSCRIPT: "emailTranscript",
      RATING: "rating",
      FEEDBACK: "feedback",
      REOPEN: "reopen",
      END: "end",
      END_WHEN_IN_QUEUE: "endWhenInQueue",
      END_WHEN_BOT_CONNECTED: "endWhenBotConnected",
      END_WHEN_OPERATOR_CONNECTED: "endWhenOperatorConnected",
      QUEUE_POSITION: "queuePosition",
    }
}

module.exports = {RNZohoDeskPortalSalesIQWrapper}