
import Foundation
import ZohoDeskPortalTicket
import React

@objc(RNZohoDeskPortalTickets)
class RNZohoDeskPortalTickets: NSObject {

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
        ZDPortalTicket.show()
    }

    @objc
    func updateConfiguration(_ configDictionary: [String: Any]) {
        ZDPTicketConfiguration.configure.isReplyAllowed = configDictionary["isReplyAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isCommentAllowed = configDictionary["isCommentAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isTicketUpdateAllowed = configDictionary["isTicketUpdateAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isCommentEditAllowed = configDictionary["isCommentEditAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isCommentDeleteAllowed = configDictionary["isCommentDeleteAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isAddTicketAllowed = configDictionary["isAddTicketAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isCustomerHappinessThreadAllowed = configDictionary["isCustomerHappinessThreadAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isTicketChannelAllowed = configDictionary["isTicketChannelAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.isTicketPropertiesAllowed = configDictionary["isTicketPropertiesAllowed"] as? Bool ?? false
        ZDPTicketConfiguration.configure.disableTicketDetailSearch = configDictionary["disableTicketDetailSearch"] as? Bool ?? false
    }

    @objc
    func showTicketDetail(_ ticketId: String){
        ZDPortalTicket.showTicketDetail(forId: ticketId)
    }
}
