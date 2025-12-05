//
//  RNHomeProviderActionDelegate.swift
//  zohoDeskPortalSDKSampleAppsReactNative
//
//  Created by Vinoth N on 04/12/25.
//

import Foundation
import React
import ZohoDeskPortalCore

@objc(RNHomeProviderActionDelegate)
class RNHomeProviderActionDelegate: RCTEventEmitter, ZDPHomeProviderDelegate {
    

    internal static var emitter: RNHomeProviderActionDelegate?

    override init() {
        super.init()
      RNHomeProviderActionDelegate.emitter = self
    }

    override func supportedEvents() -> [String]! {
      return HomeActions.allCases.map { $0.rawValue }
    }

    // Public method callable from anywhere in Swift
    func backButtonTapped() {
        RNHomeProviderActionDelegate.emitter?.sendEvent(withName: HomeActions.backAction.rawValue, body: [:])
    }
}

private enum HomeActions: String, CaseIterable {
  case backAction = "onBackTap"
}
