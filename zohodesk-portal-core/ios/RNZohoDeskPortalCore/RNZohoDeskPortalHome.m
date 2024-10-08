//
//  RNZDPortalCore.m
//  RNZDPortalCore
//
//  Created by Rajesh R on 16/09/24.
//

#import "RNZohoDeskPortalHome.h"
#import <React/RCTLog.h>
@import ZohoDeskPortalCore;
@import ZohoDeskPortalConfiguration;

@implementation RNZohoDeskPortalHome

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
  [ZDPortalHome showWithTitle:nil navigationMode:ZDPNavigationModePresent];
}



RCT_EXPORT_METHOD(updateConfiguration:(NSDictionary *)configDictionary){
  ZDPHomeConfiguration *configuration = [[ZDPHomeConfiguration alloc] init];
  
  configuration.enableCommunity = [configDictionary[@"enableCommunity"] boolValue];
  configuration.enableHelpCenter = [configDictionary[@"enableHelpCenter"] boolValue];
  configuration.enableMyTicket = [configDictionary[@"enableMyTicket"] boolValue];
  configuration.enableCreateTicket = [configDictionary[@"enableCreateTicket"] boolValue];
  configuration.enableAddTopic = [configDictionary[@"enableAddTopic"] boolValue];
  configuration.showChat = [configDictionary[@"showChat"] boolValue];
  configuration.showGC = [configDictionary[@"showGC"] boolValue];
  configuration.showAnswerBot = [configDictionary[@"showAnswerBot"] boolValue];
  configuration.showBM = [configDictionary[@"showBM"] boolValue];
  
  [ZDPortalHome updateConfigurationWith:configuration];
}

@end
