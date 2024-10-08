//
//  RNZDPortalTickets.m
//  RNZDPortalTickets
//
//  Created by Rajesh R on 16/09/24.
//
#import <React/RCTLog.h>
#import "RNZohoDeskPortalTickets.h"
@import ZohoDeskPortalTicket;
@import ZohoDeskPortalConfiguration;

@implementation RNZohoDeskPortalTickets

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalTicket showWithTitle:nil navigationMode:ZDPNavigationModePresent];
}

RCT_EXPORT_METHOD(updateConfiguration:(NSDictionary *)configDictionary){

  ZDPTicketConfiguration.configure.isReplyAllowed = [configDictionary[@"isReplyAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isCommentAllowed = [configDictionary[@"isCommentAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isTicketUpdateAllowed = [configDictionary[@"isTicketUpdateAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isCommentEditAllowed = [configDictionary[@"isCommentEditAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isCommentDeleteAllowed = [configDictionary[@"isCommentDeleteAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isAddTicketAllowed = [configDictionary[@"isAddTicketAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isCustomerHappinessThreadAllowed = [configDictionary[@"isCustomerHappinessThreadAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isTicketChannelAllowed = [configDictionary[@"isTicketChannelAllowed"] boolValue];
  ZDPTicketConfiguration.configure.isTicketPropertiesAllowed = [configDictionary[@"isTicketPropertiesAllowed"] boolValue];
  ZDPTicketConfiguration.configure.disableTicketDetailSearch = [configDictionary[@"disableTicketDetailSearch"] boolValue];
 
}
@end
