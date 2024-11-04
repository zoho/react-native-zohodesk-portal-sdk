//
//  RNZDPortalKB.m
//  RNZDPortalKB
//
//  Created by Rajesh R on 16/09/24.
//


#import <React/RCTBridgeModule.h>


@interface RCT_EXTERN_MODULE(RNZohoDeskPortalKB, NSObject)
RCT_EXTERN_METHOD(show)
RCT_EXTERN_METHOD(showArticle:(NSString *)permalink)
RCT_EXTERN_METHOD(showCategory:(NSString *)permalink)
RCT_EXTERN_METHOD(setConfiguration:(NSDictionary *)configDictionary)
@end
