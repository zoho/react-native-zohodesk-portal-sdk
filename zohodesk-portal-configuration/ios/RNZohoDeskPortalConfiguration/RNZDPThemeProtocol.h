#import <UIKit/UIKit.h>

@protocol RNZDPThemeProtocol <NSObject>
@optional
@property (nonatomic, readonly, strong) UIColor * _Nonnull themeColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull primaryBackgroundColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull secondaryBackgroundColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull primaryTextColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull secondaryTextColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull placeholderTextColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull iconColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull borderColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull separatorColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull selectedColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull navigationBarBackgroundColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull navigationBarTextColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull navigationBarButtonColor;
@property (nonatomic, readonly, strong) UIColor * _Nonnull failureColor;
@end
