//
//  RNSIQEventEmitter.swift
//  Pods
//
//  Created by Rajesh R on 23/01/25.
//

import React
import Foundation

@objc(RNSIQEventEmitter)
class RNSIQEventEmitter: RCTEventEmitter{
    
    public static var emitter: RCTEventEmitter!

    
    override init() {
        super.init()
        RNSIQEventEmitter.emitter = self
    }
    
    // This method tells React Native which events this module supports
    override func supportedEvents() -> [String]! {
        return [EventName.onInitSuccess.rawValue, EventName.onInitError.rawValue]
    }
    
    @objc
    static func emitSuccessEvent(message: String) {
        RNSIQEventEmitter.emitter?.sendEvent(withName: EventName.onInitSuccess.rawValue, body: ["message": message])
    }
    
    @objc
    static func emitErrorEvent(message: String) {
        RNSIQEventEmitter.emitter?.sendEvent(withName: EventName.onInitError.rawValue, body: ["message": message])
    }
}

// Events to send to JavaScript
enum EventName: String {
    case onInitSuccess = "onSalesIQInitSuccess"
    case onInitError = "onSalesIQInitError"
}
