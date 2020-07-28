//
//  RNZDPortalTickets.m
//  RNZohodeskPortalSdk
//
//  Created by bala-zt124 on 22/07/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNZDPortalTickets.h"
@import ZohoDeskPortalTicket;
@import ZohoDeskPortalConfiguration;

@implementation RNZDPortalTickets

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalTicket showWithTitle:nil navigationMode:ZDPNavigationModePresent];
}

@end
