


import Foundation
import ZohoDeskPortalSalesIQ
import React

@objc(RNZohoDeskPortalSalesIQ)
class RNZohoDeskPortalSalesIQ: NSObject {

    @objc
    func methodQueue() -> DispatchQueue {
        return DispatchQueue.main
    }

    @objc
    static func requiresMainQueueSetup() -> Bool {
        return true
    }

    @objc
    func show() {
        ZDPortalSalesIQ.show()
    }

    @objc
    func setGuestUser(_ email: String, name: String, phone: String) {
        ZDPortalSalesIQ.setGuestUser(email: email, displayName: name, phoneNumber: phone)
    }

    @objc
    func setChatVisibility(_ component: String, visible: Bool) {
        guard let chatComponent = mapStringToChatComponent(component) else {
            print("Invalid chat component: \(component)")
            return
        }
        ZDPortalSalesIQ.setChatVisibility(chatComponent, visible: visible)
    }
    
    @objc
    func setConversationTitle(_ title: String){
        ZDPortalSalesIQ.setConversationTitle(title)
    }
    
    @objc
    func setKnowledgeBaseVisibility(_ enable: Bool){
        ZDPortalSalesIQ.setKnowledgeBaseVisibility(enable)
    }
    
    @objc
    func enableDragToDismiss(_ enable: Bool){
        ZDPortalSalesIQ.enableDragToDismiss(enable)
    }
    
    @objc
    func setConversationVisibility(_ visible: Bool){
        ZDPortalSalesIQ.setConversationVisibility(visible)
    }
    
    @objc
    func showFeedbackAfterSkip(_ show: Bool){
        ZDPortalSalesIQ.showFeedbackAfterSkip(show)
    }
    
    @objc
    func hideQueueTime(_ show: Bool){
        ZDPortalSalesIQ.hideQueueTime(show)
    }
    
    @objc
    func setLoggerEnabled(_ enable: Bool){
        ZDPortalSalesIQ.setLoggerEnabled(enable)
    }
    
    @objc
    func showOfflineMessage(_ show: Bool){
        ZDPortalSalesIQ.showOfflineMessage(show)
    }
    
    @objc
    func showLauncher(_ mode: String){
        ZDPortalSalesIQ.showLauncher(mapStringToVisibilityMode(mode))
    }

    @objc
    func setCallbackOnInit() {
        ZDPortalSalesIQ.setCallbackOnInit(
            onSuccess: {
                // Emit success event
                RNSIQEventEmitter.emitSuccessEvent(message: "Initialization succeeded")
            },
            onError: { 
                // Emit error event
                RNSIQEventEmitter.emitErrorEvent(message: "Initialization failed")
            }
        )
    }

    private func mapStringToVisibilityMode(_ mode: String) -> ZohoDeskPortalSalesIQ.ZDPSalesIQComponents.VisibilityMode{
        switch mode{
        case "always": return .always
        case "never": return .never
        case "whenActiveChat": return .whenActiveChat
        default: return .never
        }
    }

    private func mapStringToChatComponent(_ component: String) -> ZohoDeskPortalSalesIQ.ZDPSalesIQComponents.ChatComponent? {
        switch component {
        case "preChatForm": return .preChatForm
        case "visitorName": return .visitorName
        case "screenshotOption": return .screenshotOption
        case "photoLibrary": return .photoLibrary
        case "mediaCapture": return .mediaCapture
        case "fileSharing": return .fileSharing
        case "emailTranscript": return .emailTranscript
        case "rating": return .rating
        case "feedback": return .feedback
        case "reopen": return .reopen
        case "end": return .end
        case "endWhenInQueue": return .endWhenInQueue
        case "endWhenBotConnected": return .endWhenBotConnected
        case "endWhenOperatorConnected": return .endWhenOperatorConnected
        case "queuePosition": return .queuePosition
        default: return nil
        }
    }

}
