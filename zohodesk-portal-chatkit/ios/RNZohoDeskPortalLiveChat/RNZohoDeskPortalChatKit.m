



#import <React/RCTLog.h>
#import "RNZohoDeskPortalChatKit.h"
@import ZohoDeskPortalChatKit;

@implementation RNZohoDeskPortalChatKit

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(showGC){
    [ZDPortalChatKit showGC];
}

RCT_EXPORT_METHOD(showAnswerBot){
    [ZDPortalChatKit showAnswerBot];
}

RCT_EXPORT_METHOD(showBM){ 
    [ZDPortalChatKit showBM];
}

RCT_EXPORT_METHOD(setGCSessionVariable:(NSArray *)sessionVariable) {
    [ZDPortalChatKit setGCSessionVariableWithSessionVariables:sessionVariable];
}

RCT_EXPORT_METHOD(updateGCSessionVariable:(NSArray *)sessionVariable) {
    [ZDPortalChatKit updateGCSessionVariableWithSessionVariables:sessionVariable];
}

RCT_EXPORT_METHOD(setBMSessionVariable:(NSArray *)sessionVariable) {
    [ZDPortalChatKit setBMSessionVariableWithSessionVariables:sessionVariable];
}

RCT_EXPORT_METHOD(updateBMSessionVariable:(NSArray *)sessionVariable) {
    [ZDPortalChatKit updateBMSessionVariableWithSessionVariables:sessionVariable];
}

@end

