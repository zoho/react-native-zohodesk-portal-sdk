
import Foundation
import ZohoDeskPortalTicket
import React
import RNZohoDeskPortalAPIKit

@objc(RNZohoDeskPortalSubmitTicket)
class RNZohoDeskPortalSubmitTicket: NSObject {

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
        ZDPortalSubmitTicket.show(withTitle: nil, showSideMenu: true)
    }

    @objc
    func preFillTicketFields(_ paramsDicts: [[String: Any]]) {
        var customTicketForms = [ZDCustomizedTicketForm]()
        
        for paramDict in paramsDicts {
            guard let departmentId = paramDict["departmentId"] as? String,
                  let layoutId = paramDict["layoutId"] as? String,
                  let customizedFieldsArray = paramDict["customizedTicketFields"] as? [[String: Any]] else {
                continue // Skip if any required fields are missing
            }

            var fields = [ZDCustomizedTicketField]()
            for customFieldValue in customizedFieldsArray {
                guard let fieldName = customFieldValue["fieldApiName"] as? String,
                      let value = customFieldValue["fieldValue"] as? String,
                      let isEditable = customFieldValue["isEditable"] as? Bool else {
                    continue // Skip if any required fields are missing
                }
                
                let field = ZDCustomizedTicketField(fieldName: fieldName, value: value, isEditable: isEditable)
                fields.append(field)
            }

            let form = ZDCustomizedTicketForm(departmentId: departmentId, layoutId: layoutId, fields: fields)
            customTicketForms.append(form)
        }
        
        ZDPortalSubmitTicket.preFillTicketFields(forForms: customTicketForms)
    }

    @objc
    func setFieldsListTobeShown(_ visibleFieldsList: [[String: Any]]) {
        var visibleFields = [ZDVisibleTicketField]()
        
        for fieldValue in visibleFieldsList {
            guard let departmentId = fieldValue["departmentId"] as? String,
                  let layoutId = fieldValue["layoutId"] as? String,
                  let fieldNames = fieldValue["fieldNames"] as? [String] else {
                continue 
            }
            
            let field = ZDVisibleTicketField(departmentId: departmentId, layoutId: layoutId, fieldNames: fieldNames)
            visibleFields.append(field)
        }
        
        ZDPortalSubmitTicket.setFieldsListTobeShown(fields: visibleFields)
    }

    @objc
    func getCallbackOnCreate(_ successCallback: @escaping RCTResponseSenderBlock, errorCallback: @escaping RCTResponseSenderBlock) {
        ZDPortalSubmitTicket.getCallbackOnCreateTicket { ticket in
            // Success block
            successCallback([ticket.jsonString ?? ""])
        } onError: { error in
            // Error block
            if let error = error as? NSError {
                let errorInfo: [String: Any] = [
                    "errorCode": error.code,
                    "errorMessage": error.localizedDescription
                ]
                errorCallback([errorInfo])
            } else {
                errorCallback([["errorMessage": "Unknown error occurred"]])
            }
        }
    }
}
