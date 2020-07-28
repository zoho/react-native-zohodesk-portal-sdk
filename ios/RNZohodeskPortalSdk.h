

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

typedef NS_ENUM(NSInteger, RNZDThemeType) {
/// Light theme
  RNZDThemeLight = 0,
/// Dark Theme
  RNZDThemeDark = 1,
};

@interface RNZohoDeskPortalSDK : NSObject <RCTBridgeModule>

+ (void)setDeviceIDForZDPortal: (NSString *) deviceID;
+ (void)processRemoteNotification:(NSDictionary *) userInfo;
+ (void)setTheme:(RNZDThemeType) type;

@end
  
