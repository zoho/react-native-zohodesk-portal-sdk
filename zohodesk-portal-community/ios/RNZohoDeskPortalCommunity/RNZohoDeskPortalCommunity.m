//
//  RNZDPortalCommunity.m
//  RNZDPortalCommunity
//
//  Created by Rajesh R on 16/09/24.
//

#import "RNZohoDeskPortalCommunity.h"
#import <React/RCTLog.h> 
@import ZohoDeskPortalCommunity;
@import ZohoDeskPortalConfiguration;

@implementation RNZohoDeskPortalCommunity

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalCommunity showWithTitle:nil navigationMode:ZDPNavigationModePresent];
}


RCT_EXPORT_METHOD(updateConfiguration:(NSDictionary *)configDictionary){
  
  ZDPCommunityConfiguration.isTopicEditAllowed = [configDictionary[@"isTopicEditAllowed"] boolValue];
  ZDPCommunityConfiguration.isTopicDeleteAllowed = [configDictionary[@"isTopicDeleteAllowed"] boolValue];
  ZDPCommunityConfiguration.isReplyAllowed = [configDictionary[@"isReplyAllowed"] boolValue];
  ZDPCommunityConfiguration.isReplyEditAllowed = [configDictionary[@"isReplyEditAllowed"] boolValue];
  ZDPCommunityConfiguration.isReplyDeleteAllowed = [configDictionary[@"isReplyDeleteAllowed"] boolValue];
  ZDPCommunityConfiguration.disableTopicDetailSearch = ![configDictionary[@"isTopicDetailSearchAllowed"] boolValue];
  
}

@end

