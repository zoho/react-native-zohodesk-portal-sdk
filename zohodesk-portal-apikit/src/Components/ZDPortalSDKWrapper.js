const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSDK} = NativeModules;
import {Department} from "../Models/Department";
import { FormLayout} from "../Models/FormLayout";
import { ZDPProduct } from "../Models/ZDPProduct";
import { ZDPProfile } from "../Models/ZDPProfile";
import { ZDPAttachment } from "../Models/ZDPTopic";
import { TicketField } from "../Models/TIcketField";
import { TicketForm } from "../Models/TicketForm";
import { ZDPTopic } from "../Models/ZDPTopic";

const ZDPortalSDKWrapper = {

    initialise: function(orgId, appId, dc) {
        RNZohoDeskPortalSDK.initialise(orgId, appId, dc);
    },

    isUserSignedIn: function(callback) {
        RNZohoDeskPortalSDK.isUserSignedIn(callback);
    },

    enablePush: function() {
        RNZohoDeskPortalSDK.enablePush();
    },

    disablePush: function() {
        RNZohoDeskPortalSDK.disablePush();
    },

    logout: function(successCallback, errorCallback) {
        RNZohoDeskPortalSDK.logout(successCallback, errorCallback);
    },

    enableLogs: function() {
        RNZohoDeskPortalSDK.enableLogs();
    },

    disableLogs: function() {
        RNZohoDeskPortalSDK.disableLogs();
    },

    loginWithJWTToken: function(jwtToken, successCallback, errorCallback) {
        RNZohoDeskPortalSDK.loginWithJWTToken(jwtToken, successCallback, errorCallback);
    },

    clearData: function() {
        RNZohoDeskPortalSDK.clearData();
    },

    getDepartments: function(successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getDepartments((department)=>{ 
            const departments = JSON.parse(department).map(dept => Department.fromJSON(dept));
            successCallback(departments)
        },errorCallback);
    },

    getLayouts: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getLayouts(params,(formLayout)=>{
            const formLayouts = JSON.parse(formLayout).map(layout => FormLayout.fromJSON(layout));
            successCallback(formLayouts)
        },errorCallback);
    },

    getMostPopularTopics: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getMostPopularTopics(params,(topics)=>{
            const popularTopics = JSON.parse(topics).map(topic => ZDPTopic.fromJSON(topic));
            successCallback(popularTopics);
        },errorCallback);
    },

    getMostDiscussedTopics: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getMostDiscussedTopics(params,(topics)=>{
            const discussedTopics = JSON.parse(topics).map(topic => ZDPTopic.fromJSON(topic));
            successCallback(discussedTopics);
        },errorCallback);
    },

    getProducts: function(departmentID,params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getProducts(departmentID,params,(ZDproducts)=>{
            console.log(ZDproducts)
            const products = JSON.parse(ZDproducts).map(product => ZDPProduct.fromJSON(product));
            successCallback(products)
        },errorCallback);
    },

    addAttachments: function(fileName,fileData,successCallback,errorCallback){
        RNZohoDeskPortalSDK.addAttachments(fileName,fileData,(attachment)=>{
            successCallback(ZDPAttachment.fromJSON(JSON.parse(attachment)));
        },errorCallback);
    },

    updateUserInformation: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.updateUserInformation(params,(userInfo)=>{
            successCallback(ZDPProfile.fromJSON(JSON.parse(userInfo)));
        },errorCallback);
    },

    getTicketFields: function(params, featureFlags, successCallback, errorCallback) {
        RNZohoDeskPortalSDK.getTicketFields(params, featureFlags, (ticketFields)=>{
            const fields = JSON.parse(ticketFields).map(field => TicketField.fromJSON(field))
            successCallback(fields)
        }, errorCallback);
    },

    getTicketForm: function(params,featureFlags,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getTicketForm(params,featureFlags,(ticketForms)=>{
            successCallback(TicketForm.fromJSON(JSON.parse(ticketForms)))
        },errorCallback);
    },

    createTicket: function(params, successCallback,errorCallback){
        RNZohoDeskPortalSDK.createTicket(params,(ticket)=>{
            console.log(ticket)
            successCallback(ZDPTicket.fromJSON(JSON.parse(ticket)))
        },errorCallback)
    },

    createGuestTicket: function(params, successCallback,errorCallback){
        RNZohoDeskPortalSDK.createGuestTicket(params,(ticket)=>{
            console.log(ticket)
            successCallback(ZDPTicket.fromJSON(JSON.parse(ticket)))
        }, errorCallback)
    }
};

module.exports = { ZDPortalSDKWrapper };