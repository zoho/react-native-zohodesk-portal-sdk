//
//  RNZDPortalConfiguration.m
//  RNZDPortalConfiguration
//
//  Created by Rajesh R on 16/09/24.
//

#import <React/RCTBridgeModule.h>


@interface RCT_EXTERN_MODULE(RNZohoDeskPortalConfiguration, NSObject)
RCT_EXTERN_METHOD(setThemeType: (int)type);
RCT_EXTERN_METHOD(setSDKLanguage:(NSString *)languageCode);
RCT_EXTERN_METHOD(setConfiguration:(NSDictionary *)configDictionary);

@end