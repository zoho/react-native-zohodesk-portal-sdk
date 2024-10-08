
#import "RNZohoDeskPortalSalesIQ.h"
@import ZohoDeskPortalSalesIQ;

@implementation RNZohoDeskPortalSalesIQ

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalSalesIQ show];
}

RCT_EXPORT_METHOD(setGuestUser:(NSString *)email name:(NSString *)name phone:(NSString *)phone){
    [ZDPortalSalesIQ setGuestUserWithEmail:email displayName:name phoneNumber:phone];
}

@end
