import React from 'react';
import ZohoDeskPortalSDKJSWrapper from './components/ZohoDeskPortalSDKJSWrapper';
import ZDPortalHomeJSWrapper from './components/ZDPortalHomeJSWrapper';
import ZDPortalKBJSWrapper from './components/ZDPortalKBJSWrapper';
import ZDPortalCommunityJSWrapper from './components/ZDPortalCommunityJSWrapper';
import ZDPortalTicketsJSWrapper from './components/ZDPortalTicketsJSWrapper';
import ZDPortalSubmitTicketJSWrapper from './components/ZDPortalSubmitTicketJSWrapper';
import ZDPortalChatJSWrapper from './components/ZDPortalChatJSWrapper';

module.exports = {
    ZohoDeskPortalSDK: ZohoDeskPortalSDKJSWrapper,
    ZDPortalHome: ZDPortalHomeJSWrapper,
    ZDPortalKB: ZDPortalKBJSWrapper,
    ZDPortalCommunity: ZDPortalCommunityJSWrapper,
    ZDPortalTickets: ZDPortalTicketsJSWrapper,
    ZDPortalSubmitTicket: ZDPortalSubmitTicketJSWrapper,
    ZDPortalChat: ZDPortalChatJSWrapper
};
