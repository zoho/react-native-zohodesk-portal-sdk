
import Foundation
import ZohoDeskPortalKB
import ZohoDeskPortalConfiguration
import React

@objc(RNZohoDeskPortalKB)
class RNZohoDeskPortalKB: NSObject {

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
    ZDPortalKB.show(withTitle: nil, navigationMode: .present)
  }

  @objc
  func showArticle(_ permalink: String) {
    ZDPortalKB.showArticle(withPermalink: permalink, showSideMenu: true, withTitle: nil)
  }

  @objc
  func showCategory(_ permalink: String) {
    ZDPortalKB.showCategory(withPermalink: permalink, withTitle: nil)
  }

  @objc
  func setConfiguration(_ configDictionary: [String: Any]){
    ZDPortalKBConfiguration.disableArticleLike =  configDictionary["disableArticleLike"] as? Bool ?? false
    ZDPortalKBConfiguration.disableArticleDislike =  configDictionary["disableArticleDislike"] as? Bool ?? false
    ZDPortalKBConfiguration.disableArticleDetailSearch = configDictionary["disableArticleDetailSearch"] as? Bool ?? false
  }
}
