const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSDK} = NativeModules;

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
        RNZohoDeskPortalSDK.getDepartments(successCallback,errorCallback);
    },

    getLayouts: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getLayouts(params,successCallback,errorCallback);
    },

    getMostPopularTopics: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getMostPopularTopics(params,successCallback,errorCallback);
    },

    getMostDiscussedTopics: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getMostDiscussedTopics(params,successCallback,errorCallback);
    },

    getProducts: function(departmentID,params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getProducts(departmentID,params,successCallback,errorCallback);
    },

    addAttachments: function(fileName,fileData,successCallback,errorCallback){
        RNZohoDeskPortalSDK.addAttachments(fileName,fileData,successCallback,errorCallback);
    },

    updateUserInformation: function(params,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.updateUserInformation(params,successCallback,errorCallback);
    },

    getTicketFields: function(params, featureFlags, successCallback, errorCallback) {
        RNZohoDeskPortalSDK.getTicketFields(params, featureFlags, successCallback, errorCallback);
    },

    getTicketForm: function(params,featureFlags,successCallback,errorCallback) {
        RNZohoDeskPortalSDK.getTicketForm(params,featureFlags,successCallback,errorCallback);
    }
};

module.exports = { ZDPortalSDKWrapper };