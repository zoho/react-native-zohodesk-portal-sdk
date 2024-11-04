//
//  RNZDPortalSubmitTicket.m
//  RNZDPortalSubmitTicket
//
//  Created by Rajesh R on 16/09/24.
//

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNZohoDeskPortalSubmitTicket, NSObject)

RCT_EXTERN_METHOD(show)
RCT_EXTERN_METHOD(preFillTicketFields:(NSArray *)paramsDicts)
RCT_EXTERN_METHOD(setFieldsListTobeShown:(NSArray *)visibleFieldsList)
RCT_EXTERN_METHOD(getCallbackOnCreate:(RCTResponseSenderBlock)successCallback
                  errorCallback:(RCTResponseSenderBlock)errorCallback)
@end