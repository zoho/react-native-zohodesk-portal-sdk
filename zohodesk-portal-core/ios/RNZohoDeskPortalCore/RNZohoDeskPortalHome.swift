import Foundation
import ZohoDeskPortalCore
import ZohoDeskPortalConfiguration
import React

@objc(RNZohoDeskPortalHome)
class RNZohoDeskPortalHome: NSObject {

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
    ZDPortalHome.show(withTitle: nil, navigationMode: .present)
  }

  @objc
  func updateConfiguration(_ configDictionary: [String: Any]) {
    let configuration = ZDPHomeConfiguration()

    configuration.enableCommunity = configDictionary["enableCommunity"] as? Bool ?? true
    configuration.enableHelpCenter = configDictionary["enableHelpCenter"] as? Bool ?? true
    configuration.enableMyTicket = configDictionary["enableMyTicket"] as? Bool ?? true
    configuration.enableCreateTicket = configDictionary["enableCreateTicket"] as? Bool ?? true
    configuration.enableAddTopic = configDictionary["enableAddTopic"] as? Bool ?? true
    configuration.showChat = configDictionary["showChat"] as? Bool ?? true
    configuration.showGC = configDictionary["showGC"] as? Bool ?? true
    configuration.showAnswerBot = configDictionary["showAnswerBot"] as? Bool ?? true
    configuration.showBM = configDictionary["showBM"] as? Bool ?? true
    
    ZDPortalHome.updateConfiguration(with: configuration)
  }
}

