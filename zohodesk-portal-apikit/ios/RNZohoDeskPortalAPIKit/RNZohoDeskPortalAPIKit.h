//
//  RNZDPortalAPIKit.h
//  RNZDPortalAPIKit
//
//  Created by Rajesh R on 16/09/24.
//

#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif


@interface RNZohoDeskPortalSDK : NSObject <RCTBridgeModule>

    + (void)setDeviceIDForNotification: (NSString *) deviceID;

@end
