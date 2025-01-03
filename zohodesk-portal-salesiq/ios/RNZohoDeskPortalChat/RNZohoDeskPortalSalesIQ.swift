


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
}
