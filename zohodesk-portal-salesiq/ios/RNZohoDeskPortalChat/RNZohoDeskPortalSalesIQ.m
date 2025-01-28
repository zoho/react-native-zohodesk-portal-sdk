

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNZohoDeskPortalSalesIQ, NSObject)
RCT_EXTERN_METHOD(show)
RCT_EXTERN_METHOD(setGuestUser:(NSString *)email name:(NSString *)name phone:(NSString *)phone)
RCT_EXTERN_METHOD(setChatVisibility:(NSString *)component visible:(BOOL *)visible)
RCT_EXTERN_METHOD(setConversationTitle:(NSString *)title)
RCT_EXTERN_METHOD(setOfflineMessage:(NSString *)message)
RCT_EXTERN_METHOD(setKnowledgeBaseVisibility:(BOOL *)enable)
RCT_EXTERN_METHOD(enableDragToDismiss:(BOOL *)enable)
RCT_EXTERN_METHOD(setConversationVisibility:(BOOL *)visible)
RCT_EXTERN_METHOD(showFeedbackAfterSkip:(BOOL *)show)
RCT_EXTERN_METHOD(hideQueueTime:(BOOL *)show)
RCT_EXTERN_METHOD(setLoggerEnabled:(BOOL *)enable)
RCT_EXTERN_METHOD(showOfflineMessage:(BOOL *)show)
RCT_EXTERN_METHOD(setCallbackOnInit)
@end