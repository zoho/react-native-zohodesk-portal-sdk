
//
//  RNZohoDeskPortalAPIKit.swift
//  RNZohoDeskPortalAPIKit
//
//  Created by Rajesh R on 17/10/24.
//


import Foundation
import ZohoDeskPortalAPIKit

@objc(RNZohoDeskPortalSDK)
class RNZohoDeskPortalSDK: NSObject {
    
    @objc static func setDeviceIDForNotification(_ deviceID: String) {
        UserDefaults.standard.set(deviceID, forKey: "ZDPAPNSDeviceID")
        UserDefaults.standard.synchronize()
    }
    
    @objc func methodQueue() -> DispatchQueue {
        return DispatchQueue.main
    }
    
    @objc
    static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
    @objc
    func initialise(_ orgId: String, appId: String, dc: String) {
        var dataCenter: ZDPDataCenter = .US
        
        switch dc.uppercased() {
            case "IN": dataCenter = .IN
            case "CN": dataCenter = .CN
            case "EU": dataCenter = .EU
            case "AU": dataCenter = .AU
            case "JP": dataCenter = .JP
            case "CA": dataCenter = .CA
            case "SA": dataCenter = .SA
            default: dataCenter = .US
        }
        ZohoDeskPortalSDK.initialize(orgID: orgId, appID: appId, dataCenter: dataCenter)
    }
    
    @objc
    func loginWithJWTToken(_ jwtToken: String, successCallback: @escaping RCTResponseSenderBlock, errorCallback: @escaping RCTResponseSenderBlock) {
        ZohoDeskPortalSDK.login(withJWTToken: jwtToken) { isSuccess in
            if isSuccess {
                successCallback(["User set Success", "User set Success"])
            } else {
                errorCallback(["User set Failure", "User set Failure"])
            }
        }
    }
    
    @objc
    func logout(_ successCallback: @escaping RCTResponseSenderBlock, errorCallback: @escaping RCTResponseSenderBlock) {
        ZohoDeskPortalSDK.logout { isSuccess in
            if isSuccess {
                successCallback(["Logout Success", "Logout Success"])
            } else {
                errorCallback(["Logout Failure", "Logout Failure"])
            }
        }
    }
    
    @objc
    func enablePush() {
        if let deviceID = UserDefaults.standard.string(forKey: "ZDPAPNSDeviceID") {
            ZohoDeskPortalSDK.enablePushNotification(deviceToken: deviceID, isTestDevice: false, mode: .production)
        }
    }
    
    @objc
    func disablePush() {
        if let deviceID = UserDefaults.standard.string(forKey: "ZDPAPNSDeviceID") {
            ZohoDeskPortalSDK.disablePushNotifications(deviceToken: deviceID, mode: .production) { _ in
                // No callback action needed for now
            }
        }
    }
    
    @objc
    func isUserSignedIn(_ callback: @escaping RCTResponseSenderBlock) {
        let isLoggedIn = ZohoDeskPortalSDK.isUserLoggedIn
        callback([NSNumber(value: isLoggedIn), NSNumber(value: isLoggedIn)])
    }
    
    @objc
    func enableLogs() {
        ZohoDeskPortalSDK.enableLogs()
    }
    
    @objc
    func clearData() {
        ZohoDeskPortalSDK.clearAllLocalData()
    }
    

    
    func handleCallback<T>(result: T?, error: Error?,
                           successCallback: @escaping RCTResponseSenderBlock,
                           errorCallback: @escaping RCTResponseSenderBlock) {
        if let error = error {
            let errorObject: [String: Any] = ["errorCode": (error as NSError).code, "errorMsg": error.localizedDescription]
            errorCallback([errorObject])
        } else if let result = result {
            if let encodableResult = result as? Encodable {
                successCallback([encodableResult.jsonString ?? ""])
            } else if let dictionaryResult = result as? [String: Any] {
                do {
                    let jsonData = try JSONSerialization.data(withJSONObject: dictionaryResult, options: [])
                    let jsonString = String(data: jsonData, encoding: .utf8)
                    successCallback([jsonString ?? ""])
                } catch {
                    errorCallback([["errorCode": -1, "errorMsg": "Failed to encode response"]])
                }
            } else {
                errorCallback([["errorCode": -2, "errorMsg": "Unsupported response type"]])
            }
        }
    }
    
    
    @objc
    func getDepartments(_ successCallback: @escaping RCTResponseSenderBlock, errorCallback: @escaping RCTResponseSenderBlock) {
        ZohoDeskPortalSDK.getDepartments{ departments,error  in
            self.handleCallback(result: departments, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }
    
    
    @objc
    func getLayouts(_ params: [String: Any],
                    successCallback: @escaping RCTResponseSenderBlock,
                    errorCallback: @escaping RCTResponseSenderBlock)
    {
        ZohoDeskPortalSDK.Ticket.getLayouts(params) { layouts, error in
            self.handleCallback(result: layouts, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }

    @objc
    func getMostPopularTopics(_ params: [String: Any],
                               successCallback: @escaping RCTResponseSenderBlock,
                               errorCallback: @escaping RCTResponseSenderBlock)
    {
        ZohoDeskPortalSDK.Community.getMostPopularTopics(params) { topics, error in
            self.handleCallback(result: topics, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }

    @objc
    func getMostDiscussedTopics(_ params: [String: Any],
                               successCallback: @escaping RCTResponseSenderBlock,
                               errorCallback: @escaping RCTResponseSenderBlock)
    {
        ZohoDeskPortalSDK.Community.getMostDisuccedTopics(params) { topics, error in
            self.handleCallback(result: topics, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }
    
    @objc
    func getProducts(_ departmentID: String,
                     params: [String: Any],
                     successCallback: @escaping RCTResponseSenderBlock,
                     errorCallback: @escaping RCTResponseSenderBlock)
    {
        ZohoDeskPortalSDK.getProducts(inDepartmentID: departmentID, params: params){ departments, error in
            self.handleCallback(result: departments, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }


    @objc
    func addAttachments(_ fileName: String, fileData byteString: String,
                        successCallback: @escaping RCTResponseSenderBlock,
                        errorCallback: @escaping RCTResponseSenderBlock) {
        
        // Convert the Base64 string into Data
        guard let data = Data(base64Encoded: byteString, options: .ignoreUnknownCharacters) else {
            let errorObject: [String: Any] = ["errorMsg": "Invalid byte data"]
            errorCallback([errorObject])
            return
        }
        
        ZohoDeskPortalSDK.Ticket.addAttachment(with: data, andName: fileName, using: nil) { (attachment, error) in
            self.handleCallback(result: attachment, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }
    
    @objc
    func updateUserInformation(_ userInfo: [String: String],
                                successCallback: @escaping RCTResponseSenderBlock,
                               errorCallback: @escaping RCTResponseSenderBlock){
        ZohoDeskPortalSDK.updateUserInformation(with: userInfo) { (profile, error) in
            self.handleCallback(result: profile, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }

    @objc
    func getTicketForm(_ params: [String: Any],featureFlags: String,
                       successCallback: @escaping RCTResponseSenderBlock,
                      errorCallback: @escaping RCTResponseSenderBlock){
        ZohoDeskPortalSDK.Ticket.getForm(params, headers: nil){ (ticketForm, error) in
            self.handleCallback(result: ticketForm, error: error, successCallback: successCallback, errorCallback: errorCallback)
            
        }
    }
    
    @objc
    func getTicketFields(_ params: [String: Any],featureFlags: String,
                         successCallback: @escaping RCTResponseSenderBlock,
                         errorCallback: @escaping RCTResponseSenderBlock){
        ZohoDeskPortalSDK.Ticket.getFields(params){ (ticketFields, error) in
            self.handleCallback(result: ticketFields, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }

    @objc
    func createTicket(_ params: [String: Any],
                   successCallback: @escaping RCTResponseSenderBlock,
                   errorCallback: @escaping RCTResponseSenderBlock){
        ZohoDeskPortalSDK.Ticket.add(withFields: params){(ticket,error) in
            self.handleCallback(result: ticket, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }
    
    @objc
    func createGuestTicket(_ params: [String: Any],
                            successCallback: @escaping RCTResponseSenderBlock,
                           errorCallback: @escaping RCTResponseSenderBlock){
        ZohoDeskPortalSDK.Ticket.addAsGuest(withFields: params){ (ticket,error) in
            if let error = error {
                let errorObject: [String: Any] = ["errorCode": (error as NSError).code, "errorMsg": error.localizedDescription]
                errorCallback([errorObject])
            } else if let result = ticket {
                let dataModal: [String: Any] = [
                    "ticketNumber": result
                ]

                do {
                    let jsonData = try JSONSerialization.data(withJSONObject: dataModal, options: [])
                    if let jsonString = String(data: jsonData, encoding: .utf8) {
                        successCallback([jsonString])
                    } else {
                        print("Failed to convert JSON data to string.")
                    }
                } catch {
                    print("Error serializing JSON: \(error)")
                }
            }
        }
    }

    @objc
    func addComment(_ params: [String: Any], toTicketID: String, successCallback: @escaping RCTResponseSenderBlock, errorCallback: @escaping RCTResponseSenderBlock) {
        ZohoDeskPortalSDK.Ticket.add(params, toTicketID: toTicketID) { (comment, error) in
            self.handleCallback(result: comment, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }
    
    @objc
    func addReply(_ params: [String: Any], toTicketID: String, successCallback: @escaping RCTResponseSenderBlock, errorCallback: @escaping RCTResponseSenderBlock){
        ZohoDeskPortalSDK.Ticket.reply(params, toTicketID: toTicketID){(reply, error) in
            self.handleCallback(result: reply, error: error, successCallback: successCallback, errorCallback: errorCallback)
        }
    }
}

extension Encodable{
    public var jsonString: String? {
        do {
            let encoder = JSONEncoder()
            encoder.outputFormatting = .prettyPrinted
            return String(data: try encoder.encode(self), encoding: .utf8)
        } catch {
            return nil
        }
    }
}
