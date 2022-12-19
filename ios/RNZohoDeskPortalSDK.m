//
//  RNZohoDeskPortalSDK.m
//  RNZohodeskPortalSdk
//
//  Created by bala-zt124 on 28/07/20.
//  Copyright © 2020 Facebook. All rights reserved.
//


#import <React/RCTConvert.h>
#import "RNZohoDeskPortalSDK.h"
@import ZohoDeskPortalAPIKit;
@import ZohoDeskPortalConfiguration;
@import ZDThemeKit;

@implementation RNZohoDeskPortalSDK

+ (void)setDeviceIDForZDPortal: (NSString *) deviceID{
    [[NSUserDefaults standardUserDefaults] setObject:deviceID forKey:@"ZDPAPNSDeviceID"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (void)processRemoteNotification:(NSDictionary *) userInfo{
    [ZDPortalConfiguration processRemoteNotificationWithUserInfo:userInfo];
}

+ (void)setTheme:(RNZDThemeType) type{
    
    ZDThemeType theme = ZDThemeTypeLight;
    switch (type) {
        case RNZDThemeLight:
            theme = ZDThemeTypeLight;
        case RNZDThemeDark:
            theme = ZDThemeTypeDark;
    }
    [ZDThemeManager setThemeWithType:theme];
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(initialise:(NSString *)orgId appId:(NSString *)appId dc:(NSString *)dc){
    
    ZDPDataCenter dataCenter = ZDPDataCenterUS;

    if ([dc isEqualToString:@"US"]){
        dataCenter = ZDPDataCenterUS;
    }else if ([dc isEqualToString:@"IN"]){
        dataCenter = ZDPDataCenterIN;
    }else if ([dc isEqualToString:@"CN"]){
        dataCenter = ZDPDataCenterCN;
    }else if ([dc isEqualToString:@"EU"]){
        dataCenter = ZDPDataCenterEU;
    }else if ([dc isEqualToString:@"AU"]){
        dataCenter = ZDPDataCenterAU;
    }
 
    [ZohoDeskPortalSDK initializeWithOrgID:orgId appID:appId dataCenter:dataCenter];
}

RCT_EXPORT_METHOD(setUserToken:(NSString *)userToken
                  successCallback:(RCTResponseSenderBlock)successCallback
                  errorCallback:(RCTResponseSenderBlock)errorCallback)
{
    [ZohoDeskPortalSDK loginWithUserToken:userToken onCompletion:^(BOOL isSuccess) {
        if (isSuccess){
            successCallback(@[[NSString stringWithFormat:@"User set Success"], @"User set Success"]);
        }else{
            errorCallback(@[[NSString stringWithFormat:@"User set Failure"], @"User set Failure"]);
        }
    }];
}

RCT_EXPORT_METHOD(logout:(RCTResponseSenderBlock)successCallback
                  errorCallback:(RCTResponseSenderBlock)errorCallback){
    [ZohoDeskPortalSDK logoutOnCompletion:^(BOOL isSuccess) {
        if (isSuccess){
            successCallback(@[[NSString stringWithFormat:@"Logout Success"], @"Logout Success"]);
        }else{
            errorCallback(@[[NSString stringWithFormat:@"Logout Failure"], @"Logout Failure"]);
        }
    }];
}

RCT_EXPORT_METHOD(enablePush){
    NSString *deviceID = [[NSUserDefaults standardUserDefaults] stringForKey:@"ZDPAPNSDeviceID"];
    if (deviceID != nil){
        [ZohoDeskPortalSDK enablePushNotificationWithDeviceToken:deviceID isTestDevice:NO mode:APNSmodeProduction];
    }
}

RCT_EXPORT_METHOD(disablePush){
    NSString *deviceID = [[NSUserDefaults standardUserDefaults] stringForKey:@"ZDPAPNSDeviceID"];
    if (deviceID != nil){
        [ZohoDeskPortalSDK disablePushNotificationsWithDeviceToken:deviceID mode:APNSmodeProduction onCompletion:^(BOOL isSuccess) {
            //
        }];
    }
}

RCT_EXPORT_METHOD(isUserSignedIn:(RCTResponseSenderBlock)callback)
{
    callback(@[[NSNumber numberWithBool:[ZohoDeskPortalSDK isUserLoggedIn]], [NSNumber numberWithBool:[ZohoDeskPortalSDK isUserLoggedIn]]]);
}

RCT_EXPORT_METHOD(enableLogs){
    
}

RCT_EXPORT_METHOD(disableLogs){
    [ZohoDeskPortalSDK disableLogs];
}

RCT_EXPORT_METHOD(setLanguage:(NSString * _Nonnull)lang){
    [ZDPortalConfiguration setSDKLanguage:lang];
}

@end
