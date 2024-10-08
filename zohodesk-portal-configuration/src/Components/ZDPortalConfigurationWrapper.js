const {NativeModules} = require('react-native');
const {RNZohoDeskPortalConfiguration} = NativeModules;


module.exports = {
    setSDKLanguage: function(languageCode) {
        RNZohoDeskPortalConfiguration.setSDKLanguage(languageCode);
    },

    setConfiguration: function(ZDPConfiguration = {}){
        const config = {
            disableSidemenu: ZDPConfiguration?.disableSidemenu ?? false,
            disableLanguageChooser: ZDPConfiguration?.disableLanguageChooser ?? false,
            disablePoweredByZoho: ZDPConfiguration?.disablePoweredByZoho ?? false,
            disableGlobalSearch: ZDPConfiguration?.disableGlobalSearch ?? false,
            disableKB: ZDPConfiguration?.disableKB ?? false,
            disableCommunity: ZDPConfiguration?.disableCommunity ?? false,
            disableSubmitTicket: ZDPConfiguration?.disableSubmitTicket ?? false,
            disableAddTopic: ZDPConfiguration?.disableAddTopic ?? false,
            disableMyTicket: ZDPConfiguration?.disableMyTicket ?? false,
            disableGuidedConversation: ZDPConfiguration?.disableGuidedConversation ?? false,
            disableAnswerBot: ZDPConfiguration?.disableAnswerBot ?? false,
            disableBusinessMessanger: ZDPConfiguration?.disableBusinessMessanger ?? false,
            disableSalesIQ: ZDPConfiguration?.disableSalesIQ ?? false,
            enableModuleBasedSearch: ZDPConfiguration?.enableModuleBasedSearch ?? false,

            //security Configurations
            disableDownloadAttachment: ZDPConfiguration?.disableDownloadAttachment ?? false,
            disableUploadAttachment: ZDPConfiguration?.disableUploadAttachment ?? false,
            disableScreenShot: ZDPConfiguration?.disableScreenShot ?? false,
            disableCopyPaste: ZDPConfiguration?.disableCopyPaste ?? false
        };
        RNZohoDeskPortalConfiguration.setConfiguration(config);
    },

    setTheme: function(themeValue = 1){
        RNZohoDeskPortalConfiguration.setThemeType(themeValue);
    }
}