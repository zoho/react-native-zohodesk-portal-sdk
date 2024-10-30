//
//  RNZohoDeskPortalCommunity.swift
//  RNZohoDeskPortalCommunity
//
//  Created by Rajesh R on 17/10/24.
//


import Foundation
import ZohoDeskPortalCommunity

@objc(RNZohoDeskPortalCommunity)
class RNZohoDeskPortalCommunity: NSObject {

    @objc
    func methodQueue() -> DispatchQueue {
        return DispatchQueue.main
    }
    
    @objc
    static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
    @objc
    func show(){
        ZDPortalCommunity.show(navigationMode: .present)
    }
    
    @objc(updateConfiguration:)
    func updateConfiguration(configDictionary: [String: Any]) {
        ZDPCommunityConfiguration.isTopicEditAllowed =  configDictionary["isTopicDeleteAllowed"] as? Bool ?? true
        ZDPCommunityConfiguration.isTopicDeleteAllowed =  configDictionary["isTopicDeleteAllowed"] as? Bool ?? true
        ZDPCommunityConfiguration.isReplyAllowed = configDictionary["isReplyAllowed"] as? Bool ?? true
        ZDPCommunityConfiguration.isReplyEditAllowed = configDictionary["isReplyEditAllowed"] as? Bool ?? true
        ZDPCommunityConfiguration.isReplyDeleteAllowed = configDictionary["isReplyDeleteAllowed"] as? Bool ?? true
        ZDPCommunityConfiguration.disableTopicDetailSearch =  !(configDictionary["isTopicDetailSearchAllowed"] as? Bool ?? true)
    }
    
    @objc
    func showTopic(_ permaLink: String){
        ZDPortalCommunity.showTopic(withPermaLink: permaLink)
    }
}
