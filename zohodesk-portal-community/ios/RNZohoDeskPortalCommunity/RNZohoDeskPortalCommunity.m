//
//  RNZDPortalCommunity.m
//  RNZDPortalCommunity
//
//  Created by Rajesh R on 16/09/24.
//


#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNZohoDeskPortalCommunity, NSObject)
    RCT_EXTERN_METHOD(show)
    RCT_EXTERN_METHOD(updateConfiguration:(NSDictionary *)configDictionary)
    RCT_EXTERN_METHOD(showTopic: (NSString *)permaLink )
@end
