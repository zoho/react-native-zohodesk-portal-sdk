


import Foundation
import ZohoDeskPortalConfiguration

@objc(RNZohoDeskPortalConfiguration)
class RNZohoDeskPortalConfiguration: NSObject {

    @objc
    func methodQueue() -> DispatchQueue {
        return DispatchQueue.main
    }
    
    @objc
    static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
    // Configuration Methods
    @objc static func processRemoteNotification(_ userInfo: [AnyHashable: Any]) {
        ZDPortalConfiguration.processRemoteNotification(userInfo: userInfo)
    }

    @objc static func setModalPresentationStyle(_ value: UIModalPresentationStyle) {
        ZDPortalConfiguration.modalPresentationStyle = value
    }

    @objc static func customFontName(_ fontName: String) {
        ZDPortalConfiguration.customFontName = fontName
    }

    @objc static func updateLightTheme(_ customTheme: RNZDPThemeProtocol) {
        let adapter = RNZDPThemeToZDPThemeAdapter(rnzdpTheme: customTheme)
        ZDPThemeManager.updateLightTheme(theme: adapter)
    }

    @objc static func updateDarkTheme(_ customTheme: RNZDPThemeProtocol) {
        let adapter = RNZDPThemeToZDPThemeAdapter(rnzdpTheme: customTheme)
        ZDPThemeManager.updateDarkTheme(theme: adapter)
    }


    @objc func setSDKLanguage(_ languageCode: String) {
        ZDPortalConfiguration.setSDKLanguage(languageCode)
    }

    @objc func setThemeType(_ type: Int) {
        var theme: ZDPThemeType = .system
        switch type {
        case 1:
            theme = .light
        case 2:
            theme = .dark
        case 3:
            theme = .system
        default:
            theme = .system
        }
        ZDPThemeManager.setTheme(type: theme)
    }

    @objc func setConfiguration(_ configDictionary: [String: Any]) {
        let configuration = ZDPConfiguration()
        let secureConfiguration = ZDPSecureContentConfiguration()

        configuration.disableSidemenu = configDictionary["disableSidemenu"] as? Bool ?? false
        configuration.disableLanguageChooser = configDictionary["disableLanguageChooser"] as? Bool ?? false
        configuration.disablePoweredByZoho = configDictionary["disablePoweredByZoho"] as? Bool ?? false
        configuration.disableGlobalSearch = configDictionary["disableGlobalSearch"] as? Bool ?? false
        configuration.disableKB = configDictionary["disableKB"] as? Bool ?? false
        configuration.disableCommunity = configDictionary["disableCommunity"] as? Bool ?? false
        configuration.disableAddTopic = configDictionary["disableAddTopic"] as? Bool ?? false
        configuration.disableMyTicket = configDictionary["disableMyTicket"] as? Bool ?? false
        configuration.disableSubmitTicket = configDictionary["disableSubmitTicket"] as? Bool ?? false
        configuration.disableGC = configDictionary["disableGuidedConversation"] as? Bool ?? false
        configuration.disableAnswerBot = configDictionary["disableAnswerBot"] as? Bool ?? false
        configuration.disableBM = configDictionary["disableBusinessMessanger"] as? Bool ?? false
        configuration.disableChat = configDictionary["disableSalesIQ"] as? Bool ?? false
        configuration.enableModuleBasedSearch = configDictionary["enableModuleBasedSearch"] as? Bool ?? false

        secureConfiguration.disableCopyPaste = configDictionary["disableCopyPaste"] as? Bool ?? false
        secureConfiguration.disableScreenShot = configDictionary["disableScreenShot"] as? Bool ?? false
        secureConfiguration.disableDownloadAttachment = configDictionary["disableDownloadAttachment"] as? Bool ?? false
        secureConfiguration.disableUploadAttachment = configDictionary["disableUploadAttachment"] as? Bool ?? false

        ZDPortalConfiguration.set(configuration: configuration)
        ZDPortalConfiguration.setZDPSecureConfiguration(configuration: secureConfiguration)
    }
}
