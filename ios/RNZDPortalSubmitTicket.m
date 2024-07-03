//
//  RNZDPortalSubmitTicket.m
//  RNZohodeskPortalSdk
//
//  Created by bala-zt124 on 22/07/20.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNZDPortalSubmitTicket.h"
@import ZohoDeskPortalTicket;
@import ZohoDeskPortalConfiguration;

@implementation RNZDPortalSubmitTicket

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

@end
