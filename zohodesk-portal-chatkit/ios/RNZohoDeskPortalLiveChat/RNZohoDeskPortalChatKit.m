



#import <React/RCTBridgeModule.h>
@interface RCT_EXTERN_MODULE(RNZohoDeskPortalChatKit, NSObject)
    RCT_EXTERN_METHOD(showGC)
    RCT_EXTERN_METHOD(showAnswerBot)
    RCT_EXTERN_METHOD(showBM)
    RCT_EXTERN_METHOD(setGCSessionVariable:(NSArray *)sessionVariable)
    RCT_EXTERN_METHOD(updateGCSessionVariable:(NSArray *)sessionVariable)
    RCT_EXTERN_METHOD(setBMSessionVariable:(NSArray *)sessionVariable)
    RCT_EXTERN_METHOD(updateBMSessionVariable:(NSArray *)sessionVariable)
    RCT_EXTERN_METHOD(clearGC)
    RCT_EXTERN_METHOD(clearBM)
    RCT_EXTERN_METHOD(clearAnswerBot)
@end
