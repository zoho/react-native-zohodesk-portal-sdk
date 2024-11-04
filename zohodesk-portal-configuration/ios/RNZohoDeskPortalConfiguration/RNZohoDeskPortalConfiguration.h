//
//  RNZDPortalConfiguration.h
//  RNZDPortalConfiguration
//
//  Created by Rajesh R on 16/09/24.
//

#if __has_include("RCTEventEmitter.h")
#import "RCTEventEmitter.h"
#else
#import <React/RCTEventEmitter.h>
#endif

#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import "RNZDPThemeProtocol.h"


@interface RNZohoDeskPortalConfiguration : NSObject <RCTBridgeModule>

+ (void)processRemoteNotification:(NSDictionary *) userInfo;

+ (void)setModalPresentationStyle:(UIModalPresentationStyle)value;

+ (void)customFontName:(NSString *)fontName;


+ (void)updateLightTheme:(id<RNZDPThemeProtocol>)RNZDPThemeProtocol;

+ (void)updateDarkTheme:(id<RNZDPThemeProtocol>)RNZDPThemeProtocol;
@end

