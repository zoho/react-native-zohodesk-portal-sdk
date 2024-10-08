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

@implementation RNZohoDeskPortalSDK

+ (void)setDeviceIDForZDPortal: (NSString *) deviceID{
    [[NSUserDefaults standardUserDefaults] setObject:deviceID forKey:@"ZDPAPNSDeviceID"];
    [[NSUserDefaults standardUserDefaults] synchronize];
}

+ (void)processRemoteNotification:(NSDictionary *) userInfo{
    [ZDPortalConfiguration processRemoteNotificationWithUserInfo:userInfo];
}

+ (void)setTheme:(RNZDThemeType) type{
    
    ZDPThemeType theme = ZDPThemeTypeLight;
    switch (type) {
        case RNZDThemeLight:
            theme = ZDPThemeTypeLight;
        case RNZDThemeDark:
            theme = ZDPThemeTypeDark;
    }
    [ZDPThemeManager setThemeWithType:theme];
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
    }else if ([dc isEqualToString:@"JP"]){
        dataCenter = ZDPDataCenterJP;
    }
 
    [ZohoDeskPortalSDK initializeWithOrgID:orgId appID:appId dataCenter:dataCenter];
}

RCT_EXPORT_METHOD(setJWTToken:(NSString *)jwtToken
                  successCallback:(RCTResponseSenderBlock)successCallback
                  errorCallback:(RCTResponseSenderBlock)errorCallback){
    [ZohoDeskPortalSDK loginWithJWTToken:jwtToken onCompletion:^(BOOL isSuccess) {
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
    [ZohoDeskPortalSDK enableLogs];
}

RCT_EXPORT_METHOD(disableLogs){
}

RCT_EXPORT_METHOD(clearDeskPortalData) {
    [ZohoDeskPortalSDK clearAllLocalData];
}

RCT_EXPORT_METHOD(getDepartments:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback) {
    [ZohoDeskPortalSDK getDepartmentsOnCompletion:^(NSArray<Department *> * _Nonnull departments, NSError * _Nonnull error) {
        if (error) {
            NSDictionary *errorObject = @{
                @"errorCode" : @(error.code),
                @"errorMsg" : error.localizedDescription
            };
            errorCallback(@[errorObject]);
        } else {
            NSMutableArray *departmentArray = [NSMutableArray array];
            for (Department *department in departments) {
                NSDictionary *dict = @{
                    @"photoURL": department.photoURL ?: @"",
                    @"description": department.deptDescription ?: @"",
                    @"name": department.name,
                    @"id": department.id,
                    @"nameInCustomerPortal": department.nameInCustomerPortal,
                    @"layoutCount": @(department.layoutCount)
                };
                [departmentArray addObject:dict];
            }
            
            successCallback(@[departmentArray]);
        }
    }];
}

RCT_EXPORT_METHOD(getLayouts:(NSDictionary *)params successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback) {
    [ZohoDeskPortalSDKTicket getLayouts:params onCompletion:^(NSArray<FormLayout *> * _Nullable layouts, NSError * _Nullable error) {
        if (error) {
            NSDictionary *errorObject = @{
                @"errorCode" : @(error.code),
                @"errorMsg" : error.localizedDescription
            };
            errorCallback(@[errorObject]);
        } else {
            NSMutableArray *layoutArray = [NSMutableArray array];
            for (FormLayout *layout in layouts) {
                NSDictionary *dict = @{
                    @"departmentId": layout.departmentID,
                    @"hasLogo": @(layout.hasLogo),
                    @"id": layout.id,
                    @"isDefaultLayout": @(layout.isDefaultLayout),
                    @"isStandardLayout": @(layout.isStandardLayout),
                    @"layoutDesc": layout.layoutDesc,
                    @"layoutName": layout.layoutName,
                    @"module": layout.module,
                    @"photoURL": [NSNull null]
                };
                [layoutArray addObject:dict];
            }
            successCallback(@[layoutArray]);
        }
    }];
    
}

RCT_EXPORT_METHOD(getTicketForm:(NSDictionary *)params featureFlags:(NSString *)flags successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback) {
    // Android handled feature flags value in this API. iOS need to handle this
    [ZohoDeskPortalSDKTicket getForm:params headers:nil onCompletion:^(TicketForm * _Nullable ticketForm, NSError * _Nullable error) {
        if (error) {
            errorCallback(@[@{@"error": error.localizedDescription}]);
            return;
        }

        if (ticketForm) {
            // Convert the TicketForm object to NSDictionary
            NSDictionary *formDict =  [RNZohoDeskPortalSDK dictionaryFromTicketForm:ticketForm];
            successCallback(@[formDict]);
        } else {
            errorCallback(@[@{@"error": @"No ticket form available"}]);
        }
    }];
}

RCT_EXPORT_METHOD(getTicketFields:(NSDictionary *)params featureFlags:(NSString *)flags successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback) {
     // Android handle feature flags value in this API. iOS need to handle this
    [ZohoDeskPortalSDKTicket getFields:params onCompletion:^(NSArray<ZDTicketField *> * _Nullable ticketFields, NSError * _Nullable error) {
        if (error) {
            errorCallback(@[@{@"error": error.localizedDescription}]);
            return;
        }

        if (ticketFields) {
            NSArray *fieldsDictArray = [RNZohoDeskPortalSDK dictionaryFromTicketFieldArray:ticketFields];
            NSDictionary *fieldData = @{@"data": fieldsDictArray};
            successCallback(@[fieldData]);
        } else {
            errorCallback(@[@{@"error": @"No ticket fields available"}]);
        }
    }];
}

// Convert TicketForm to NSDictionary
+ (NSDictionary *)dictionaryFromTicketForm:(TicketForm *)ticketForm {
    NSMutableArray *sectionsArray = [NSMutableArray array];
    
    for (FormSection *section in ticketForm.sections) {
        NSDictionary *sectionDict = @{
            @"sectionName": section.sectionName,
            @"fields": [self dictionaryFromTicketFieldArray:section.fields]
        };
        [sectionsArray addObject:sectionDict];
    }
    
    NSDictionary *sections = @{@"sections": sectionsArray};
    
    return @{@"form": sections};
}

// Convert TicketField array to NSDictionary
+ (NSArray<NSDictionary *> *)dictionaryFromTicketFieldArray:(NSArray<ZDTicketField *> *)fields {
    NSMutableArray *fieldsArray = [NSMutableArray array];
    
    for (ZDTicketField *field in fields) {
        NSDictionary *fieldDict = @{
            @"displayLabel": field.displayLabel ?: [NSNull null],
            @"i18NLabel": field.i18NLabel ?: [NSNull null],
            @"apiName": field.apiName ?: [NSNull null],
            @"id": field.id ?: [NSNull null],
            @"name": field.name ?: [NSNull null],
            @"type": field.type,
            @"isMandatory": @(field.isMandatory),
            @"allowedValues": field.allowedValues ?: [NSNull null],
            @"dependency": field.dependency ?: [NSNull null],
            @"defaultValue": field.defaultValue ?: [NSNull null],
            @"toolTip": field.toolTip ?: [NSNull null],
            @"toolTipType": field.toolTipType ?: [NSNull null],
            @"sortBy": field.sortBy ?: [NSNull null],
            @"statusMapping": [self dictionaryFromStatusMapping:field.statusMapping] ?: [NSNull null],
            @"restoreOnReplyValues": field.restoreOnReplyValues ?: [NSNull null],
            @"roundingOption": field.roundingOption ?: [NSNull null],
            @"fieldName": field.fieldName ?: [NSNull null],
            @"uploadAttachment": field.uploadAttachment ?: [NSNull null],
            @"captchaURL": field.captchaURL ?: [NSNull null],
            @"isPHI": @(field.isPHI),
            @"isEncryptedField": @(field.isEncryptedField),
            @"isNested": @(field.isNested),
            @"isVisible": @(field.isVisible),
            @"maxLength": @(field.maxLength),
            @"precison": @(field.precison),
            @"decimals": @(field.decimals),
            @"isReadOnly": @(field.isReadOnly),
            @"isCustomField": @(field.isCustomField)
        };
        [fieldsArray addObject:fieldDict];
    }
    
    return fieldsArray;
}

// Convert StatusMap array to NSDictionary
+ (NSArray<NSDictionary *> *)dictionaryFromStatusMapping:(NSArray<StatusMap *> *)statusMapping {
    NSMutableArray *statusArray = [NSMutableArray array];
    
    for (StatusMap *status in statusMapping) {
        NSDictionary *statusDict = @{
            @"mappingValue": status.mappingValue ?: [NSNull null],
            @"name": status.name ?: [NSNull null]
        };
        [statusArray addObject:statusDict];
    }
    
    return statusArray;
}



@end
