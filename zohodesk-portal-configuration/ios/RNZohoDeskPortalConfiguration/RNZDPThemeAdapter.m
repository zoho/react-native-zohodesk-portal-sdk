#import "RNZDPThemeAdapter.h"

@interface RNZDPThemeToZDPThemeAdapter ()
@property (nonatomic, strong) id<RNZDPThemeProtocol> rnzdpTheme;
@end

@implementation RNZDPThemeToZDPThemeAdapter

- (instancetype)initWithRNZDPTheme:(id<RNZDPThemeProtocol>)rnzdpTheme {
    self = [super init];
    if (self) {
        _rnzdpTheme = rnzdpTheme;
    }
    return self;
}

- (UIColor *)themeColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(themeColor)]) {
        return [self.rnzdpTheme themeColor];
    }
    return nil;
}

- (UIColor *)primaryBackgroundColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(primaryBackgroundColor)]) {
        return [self.rnzdpTheme primaryBackgroundColor];
    }
    return nil;
}

- (UIColor *)secondaryBackgroundColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(secondaryBackgroundColor)]) {
        return [self.rnzdpTheme secondaryBackgroundColor];
    }
    return nil;
}

- (UIColor *)primaryTextColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(primaryTextColor)]) {
        return [self.rnzdpTheme primaryTextColor];
    }
    return nil;
}

- (UIColor *)secondaryTextColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(secondaryTextColor)]) {
        return [self.rnzdpTheme secondaryTextColor];
    }
    return nil;
}

- (UIColor *)placeholderTextColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(placeholderTextColor)]) {
        return [self.rnzdpTheme placeholderTextColor];
    }
    return nil;
}

- (UIColor *)iconColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(iconColor)]) {
        return [self.rnzdpTheme iconColor];
    }
    return nil;
}

- (UIColor *)borderColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(borderColor)]) {
        return [self.rnzdpTheme borderColor];
    }
    return nil;
}

- (UIColor *)separatorColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(separatorColor)]) {
        return [self.rnzdpTheme separatorColor];
    }
    return nil;
}

- (UIColor *)selectedColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(selectedColor)]) {
        return [self.rnzdpTheme selectedColor];
    }
    return nil;
}

- (UIColor *)navigationBarBackgroundColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(navigationBarBackgroundColor)]) {
        return [self.rnzdpTheme navigationBarBackgroundColor];
    }
    return nil;
}

- (UIColor *)navigationBarTextColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(navigationBarTextColor)]) {
        return [self.rnzdpTheme navigationBarTextColor];
    }
    return nil;
}

- (UIColor *)navigationBarButtonColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(navigationBarButtonColor)]) {
        return [self.rnzdpTheme navigationBarButtonColor];
    }
    return nil;
}

- (UIColor *)failureColor {
    if ([self.rnzdpTheme respondsToSelector:@selector(failureColor)]) {
        return [self.rnzdpTheme failureColor];
    }
    return nil;
}

@end
