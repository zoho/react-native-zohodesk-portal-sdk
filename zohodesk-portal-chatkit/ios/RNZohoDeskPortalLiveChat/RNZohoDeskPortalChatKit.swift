//
//  RNZohoDeskPortalChatKit.swift
//  RNZohoDeskPortalChatKit
//
//  Created by Rajesh R on 21/10/24.
//


import Foundation
import ZohoDeskPortalChatKit

@objc(RNZohoDeskPortalChatKit)
class RNZohoDeskPortalChatKit: NSObject {

  @objc
  func methodQueue() -> DispatchQueue {
      return DispatchQueue.main
  }
  
  @objc
  static func requiresMainQueueSetup() -> Bool {
      return true
  }
  
   @objc
   func showGC(){
        ZDPortalChatKit.showGC()
   }

    @objc
    func showAnswerBot(){
        ZDPortalChatKit.showAnswerBot()
    }

    @objc
    func showBM(){
        ZDPortalChatKit.showBM()
    }

    @objc
    public static func setGCSessionVariable(sessionVariables: [[String: Any]]){
       ZDPortalChatKit.setGCSessionVariable(sessionVariables: sessionVariables)
    }
  
    @objc
    func updateGCSessionVariable(sessionVariables: [[String: Any]]){
      ZDPortalChatKit.updateGCSessionVariable(sessionVariables: sessionVariables)
    }

    @objc 
    func setBMSessionVariable(sessionVariables: [[String: Any]]){
      ZDPortalChatKit.setBMSessionVariable(sessionVariables: sessionVariables)
    }

    @objc
    func updateBMSessionVariable(sessionVariables: [[String: Any]]){
      ZDPortalChatKit.updateBMSessionVariable(sessionVariables: sessionVariables)
    }
}
