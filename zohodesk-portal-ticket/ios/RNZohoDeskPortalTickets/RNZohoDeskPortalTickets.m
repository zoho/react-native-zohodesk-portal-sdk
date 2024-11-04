//
//  RNZDPortalTickets.m
//  RNZDPortalTickets
//
//  Created by Rajesh R on 16/09/24.
//

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNZohoDeskPortalTickets, NSObject)
RCT_EXTERN_METHOD(show)
RCT_EXTERN_METHOD(updateConfiguration:(NSDictionary *)configDictionary)
RCT_EXTERN_METHOD(showTicketDetail: (NSString *)ticketId)
@end
