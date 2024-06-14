const {NativeModules} = require('react-native');
const {RNZohoDeskPortalSDK} = NativeModules;

class Department {
  constructor(photoURL, deptDescription, name, id, nameInCustomerPortal, layoutCount) {
      this.photoURL = photoURL;
      this.deptDescription = deptDescription;
      this.name = name;
      this.id = id;
      this.nameInCustomerPortal = nameInCustomerPortal;
      this.layoutCount = layoutCount;
  }
}

class FormLayout {
  constructor(departmentID, hasLogo, id, isDefaultLayout, isStandardLayout, layoutDesc, layoutName, module) {
      this.departmentID = departmentID;
      this.hasLogo = hasLogo;
      this.id = id;
      this.isDefaultLayout = isDefaultLayout;
      this.isStandardLayout = isStandardLayout;
      this.layoutDesc = layoutDesc;
      this.layoutName = layoutName;
      this.module = module;
  }
}

module.exports = {

    initialise: function(orgId, appId, dc) {
      RNZohoDeskPortalSDK.initialise(orgId, appId, dc);
    },

    setUserToken: function(userToken, successCallback, errorCallback) {
      RNZohoDeskPortalSDK.setUserToken(userToken, successCallback, errorCallback);
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

    setJWTToken: function(jwtToken, successCallback, errorCallback) {
      RNZohoDeskPortalSDK.setJWTToken(jwtToken, successCallback, errorCallback);
    },

    clearDeskPortalData: function() {
      RNZohoDeskPortalSDK.clearDeskPortalData();
    },

    getDepartments: function(callback) {
      RNZohoDeskPortalSDK.getDepartments((departmentLists, error) => {
          if (error) {
              callback(null, error);
          } else {
              try {
                  const departments = departmentLists.map(dept => new Department(
                      dept.photoURL,
                      dept.deptDescription,
                      dept.name,
                      dept.id,
                      dept.nameInCustomerPortal,
                      dept.layoutCount
                  ));
                  callback(departments, null);
              } catch (parseError) {
                  callback(null, parseError.message);
              }
          }
      });
    },

    getLayouts: function(params, callback) {
      RNZohoDeskPortalSDK.getLayouts(params, (layoutLists, error) => {
          if (error) {
              callback(null, error);
          } else {
              try {
                  const layouts = layoutLists.map(layout => new FormLayout(
                      layout.departmentID,
                      layout.hasLogo,
                      layout.id,
                      layout.isDefaultLayout,
                      layout.isStandardLayout,
                      layout.layoutDesc,
                      layout.layoutName,
                      layout.module
                  ));
                  callback(layouts, null);
              } catch (parseError) {
                  callback(null, parseError.message);
              }
          }
      });
  }

}
