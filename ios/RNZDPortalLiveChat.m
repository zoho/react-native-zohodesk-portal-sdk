//
//  RNZDPortalLiveChat.m
//  RNZohodeskPortalSdk
//
//  Created by bala-zt124 on 21/07/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNZDPortalLiveChat.h"
@import ZohoDeskPortalChatKit;

@implementation RNZDPortalLiveChat

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
