//
//  RNZDPortalChat.m
//  RNZohodeskPortalSdk
//
//  Created by bala-zt124 on 22/07/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNZDPortalChat.h"
@import ZohoDeskPortalSalesIQ;

@implementation RNZDPortalChat

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalSalesIQ show];
}

RCT_EXPORT_METHOD(setGuestUserDetails:(NSString *)email name:(NSString *)name phone:(NSString *)phone){
    [ZDPortalSalesIQ setGuestUserWithEmail:email displayName:name phoneNumber:phone];
}

@end
