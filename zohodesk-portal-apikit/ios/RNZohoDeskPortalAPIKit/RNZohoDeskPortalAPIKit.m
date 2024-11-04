//
//  RNZDPortalAPIKit.m
//  RNZDPortalAPIKit
//
//  Created by Rajesh R on 16/09/24.
//


#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNZohoDeskPortalSDK, NSObject)
RCT_EXTERN_METHOD(initialise:(NSString *)orgId appId:(NSString *)appId dc:(NSString *)dc)
RCT_EXTERN_METHOD(loginWithJWTToken:(NSString *)jwtToken successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(logout:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(enablePush)
RCT_EXTERN_METHOD(disablePush)
RCT_EXTERN_METHOD(isUserSignedIn:(RCTResponseSenderBlock)callback)
RCT_EXTERN_METHOD(enableLogs)
RCT_EXTERN_METHOD(clearData)
RCT_EXTERN_METHOD(getDepartments:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(getLayouts:(NSDictionary *)params successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(getMostPopularTopics: (NSDictionary *)params successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(getMostDiscussedTopics: (NSDictionary *)params successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(getProducts:(NSString *)departmentID params:(NSDictionary *)params successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(addAttachments:(NSString *)fileName fileData:(NSString *)byteString successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(updateUserInformation:(NSDictionary *)userInfo successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(getTicketFields:(NSDictionary *)params featureFlags:(NSString *)flags  successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(getTicketForm:(NSDictionary *)params featureFlags:(NSString *)flags  successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(createTicket:(NSDictionary *)params successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
RCT_EXTERN_METHOD(createGuestTicket:(NSDictionary *)params successCallback:(RCTResponseSenderBlock)successCallback errorCallback:(RCTResponseSenderBlock)errorCallback)
@end