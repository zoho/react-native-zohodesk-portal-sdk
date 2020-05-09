const {NativeModules} = require('react-native');
const {RNZDPortalKB} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalKB.show();
    }
}
