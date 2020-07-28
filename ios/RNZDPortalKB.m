//
//  RNZDPortalKB.m
//  RNZohodeskPortalSdk
//
//  Created by bala-zt124 on 21/07/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNZDPortalKB.h"
@import ZohoDeskPortalKB;
@import ZohoDeskPortalConfiguration;

@implementation RNZDPortalKB

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalKB showWithTitle:nil navigationMode:ZDPNavigationModePresent];
}

@end
