//
//  RNZDPortalConfiguration.m
//  RNZDPortalConfiguration
//
//  Created by Rajesh R on 16/09/24.
//

#import "RNZohoDeskPortalConfiguration.h"
#import <React/RCTLog.h> // Import to use RCTLog for React Native logging
@import ZohoDeskPortalConfiguration;
#import "RNZDPThemeAdapter.h"

@implementation RNZohoDeskPortalConfiguration

//Configuration Methods
+ (void)processRemoteNotification:(NSDictionary *) userInfo{
    [ZDPortalConfiguration processRemoteNotificationWithUserInfo:userInfo];
}

+ (void)setModalPresentationStyle:(UIModalPresentationStyle)value {
  ZDPortalConfiguration.modalPresentationStyle = value;
}

+ (void)customFontName:(NSString *)fontName {
  ZDPortalConfiguration.customFontName = fontName;
}

+ (void)updateLightTheme:(id<RNZDPThemeProtocol>)customTheme {
    RNZDPThemeToZDPThemeAdapter *adapter = [[RNZDPThemeToZDPThemeAdapter alloc] initWithRNZDPTheme:customTheme];
    [ZDPThemeManager updateLightThemeWithTheme:adapter];
}

+ (void)updateDarkTheme:(id<RNZDPThemeProtocol>)customTheme{
    RNZDPThemeToZDPThemeAdapter *adapter = [[RNZDPThemeToZDPThemeAdapter alloc] initWithRNZDPTheme:customTheme];
    [ZDPThemeManager updateDarkThemeWithTheme:adapter];
}
- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(setSDKLanguage:(NSString *)languageCode){
    [ZDPortalConfiguration setSDKLanguage:languageCode];
}

RCT_EXPORT_METHOD(setThemeType: (int)type) {
  ZDPThemeType theme = ZDPThemeTypeSystem; 
  switch (type) {
    case 1:
      theme = ZDPThemeTypeLight;
      break;
    case 2:
      theme = ZDPThemeTypeDark;
      break;
    case 3:
      theme = ZDPThemeTypeSystem;
      break;
    default:
      theme = ZDPThemeTypeSystem; 
  }
  [ZDPThemeManager setThemeWithType:theme];
}


RCT_EXPORT_METHOD(setConfiguration:(NSDictionary *)configDictionary) {
    ZDPConfiguration *configuration = [[ZDPConfiguration alloc] init];
    ZDPSecureContentConfiguration *secureConfiguration = [[ZDPSecureContentConfiguration alloc] init];

    configuration.disableSidemenu = [configDictionary[@"disableSidemenu"] boolValue];
    configuration.disableLanguageChooser = [configDictionary[@"disableLanguageChooser"] boolValue];
    configuration.disablePoweredByZoho = [configDictionary[@"disablePoweredByZoho"] boolValue];
    configuration.disableGlobalSearch = [configDictionary[@"disableGlobalSearch"] boolValue];
    configuration.disableKB = [configDictionary[@"disableKB"] boolValue];
    configuration.disableCommunity = [configDictionary[@"disableCommunity"] boolValue];
    configuration.disableAddTopic = [configDictionary[@"disableAddTopic"] boolValue];
    configuration.disableMyTicket = [configDictionary[@"disableMyTicket"] boolValue];
    configuration.disableSubmitTicket = [configDictionary[@"disableSubmitTicket"] boolValue];
    configuration.disableLiveChat = [configDictionary[@"disableGuidedConversation"] boolValue];
    configuration.disableKBBot = [configDictionary[@"disableAnswerBot"] boolValue];
    configuration.disableBMChat = [configDictionary[@"disableBusinessMessanger"] boolValue];
    configuration.disableChat = [configDictionary[@"disableSalesIQ"] boolValue];
    configuration.enableModuleBasedSearch = [configDictionary[@"enableModuleBasedSearch"] boolValue];

    secureConfiguration.disableCopyPaste = [configDictionary[@"disableCopyPaste"] boolValue];
    secureConfiguration.disableScreenShot = [configDictionary[@"disableScreenShot"] boolValue];
    secureConfiguration.disableDownloadAttachment = [configDictionary[@"disableDownloadAttachment"] boolValue];
    secureConfiguration.disableUploadAttachment = [configDictionary[@"disableUploadAttachment"] boolValue];
  
    [ZDPortalConfiguration setWithConfiguration:configuration];
    [ZDPortalConfiguration setZDPSecureConfigurationWithConfiguration:secureConfiguration];
}

@end