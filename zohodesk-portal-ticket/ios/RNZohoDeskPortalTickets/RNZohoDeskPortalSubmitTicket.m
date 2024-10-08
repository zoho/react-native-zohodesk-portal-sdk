//
//  RNZDPortalSubmitTicket.m
//  RNZDPortalSubmitTicket
//
//  Created by Rajesh R on 16/09/24.
//


#import "RNZohoDeskPortalSubmitTicket.h"
@import ZohoDeskPortalTicket;
@import ZohoDeskPortalConfiguration;

@implementation RNZohoDeskPortalSubmitTicket

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show){
    [ZDPortalSubmitTicket showWithTitle:nil showSideMenu:true];
}

RCT_EXPORT_METHOD(preFillTicketFields:(NSArray *)paramsDicts) {
    NSMutableArray<ZDCustomizedTicketForm *> *customTicketForms = [NSMutableArray array];
    for (NSDictionary *paramDict in paramsDicts) {
        NSString *departmentId = paramDict[@"departmentId"];
        NSString *layoutId = paramDict[@"layoutId"];
        NSArray *customizedFieldsArray = paramDict[@"customizedTicketFields"];
        
        NSMutableArray<ZDCustomizedTicketField *> *fields = [NSMutableArray array];
        for (NSDictionary *customFieldValue in customizedFieldsArray) {
            NSString *fieldName = customFieldValue[@"fieldApiName"];
            NSString *value = customFieldValue[@"fieldValue"];
            BOOL isEditable = [customFieldValue[@"isEditable"] boolValue];
            
            ZDCustomizedTicketField *field = [[ZDCustomizedTicketField alloc] initWithFieldName:fieldName value:value isEditable:isEditable];
            [fields addObject:field];
        }
        
        ZDCustomizedTicketForm *form = [[ZDCustomizedTicketForm alloc] initWithDepartmentId:departmentId layoutId:layoutId fields:fields];
        [customTicketForms addObject:form];
    }
    [ZDPortalSubmitTicket preFillTicketFieldsForForms:customTicketForms];
}

RCT_EXPORT_METHOD(setFieldsListTobeShown:(NSArray *)visibleFieldsList) {
    NSMutableArray<ZDVisibleTicketField *> *visibleFields = [NSMutableArray array];
    for (NSDictionary *fieldValue in visibleFieldsList) {
        NSString *departmentId = fieldValue[@"departmentId"];
        NSString *layoutId = fieldValue[@"layoutId"];
        NSArray *fieldNames = fieldValue[@"fieldNames"];
        
        ZDVisibleTicketField *field = [[ZDVisibleTicketField alloc] initWithDepartmentId:departmentId layoutId:layoutId fieldNames:fieldNames];
        [visibleFields addObject:field];
    }
    [ZDPortalSubmitTicket setFieldsListTobeShownWithFields:visibleFields];
}


RCT_EXPORT_METHOD(getCallbackOnCreate:(RCTResponseSenderBlock)successCallback
                  errorCallback:(RCTResponseSenderBlock)errorCallback) {

  [ZDPortalSubmitTicket getCallbackOnCreateTicketWithCompletion:^(ZDPTicket * _Nonnull ticket) {
      // Success block
    NSDictionary *ticketDict = @{@"ticket": ticket};
      successCallback(@[ticketDict]);

  } onError:^(NSError * _Nullable error) {
      // Error block
      if (error) {
          NSDictionary *errorInfo = @{
              @"errorCode": @(error.code),
              @"errorMessage": error.localizedDescription
          };
          errorCallback(@[errorInfo]);
      } else {
          errorCallback(@[@{@"errorMessage": @"Unknown error occurred"}]);
      }
  }];
}

@end
