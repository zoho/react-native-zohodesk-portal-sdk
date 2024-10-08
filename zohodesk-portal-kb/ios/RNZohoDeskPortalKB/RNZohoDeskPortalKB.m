//
//  RNZDPortalKB.m
//  RNZDPortalKB
//
//  Created by Rajesh R on 16/09/24.
//
#import <React/RCTLog.h>
#import "RNZohoDeskPortalKB.h"
@import ZohoDeskPortalKB;
@import ZohoDeskPortalConfiguration;

@implementation RNZohoDeskPortalKB

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(show) {
    [ZDPortalKB showWithTitle:nil navigationMode:ZDPNavigationModePresent];
}

RCT_EXPORT_METHOD(showArticle:(NSString *)permalink) {
    [ZDPortalKB showArticleWithPermalink:permalink showSideMenu:true withTitle:nil];
}

RCT_EXPORT_METHOD(showCategory:(NSString *)permalink) {
    [ZDPortalKB showCategoryWithPermalink:permalink withTitle:nil];
}

@end
