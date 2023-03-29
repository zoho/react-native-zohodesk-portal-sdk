//
//  RNZDPortalHome.m
//  RNZohodeskPortalSdk
//
//  Created by bala-zt124 on 22/07/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNZDPortalHome.h"
@import ZohoDeskPortalCore;
@import ZohoDeskPortalConfiguration;

@implementation RNZDPortalHome

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalHome showWithTitle:nil navigationMode:ZDPNavigationModePush];
}

@end
