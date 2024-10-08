#import <UIKit/UIKit.h>
#import "RNZDPThemeProtocol.h"
@import ZohoDeskPortalConfiguration;

@interface RNZDPThemeToZDPThemeAdapter : NSObject <ZDPThemeProtocol>

- (instancetype)initWithRNZDPTheme:(id<RNZDPThemeProtocol>)rnzdpTheme;

@end
