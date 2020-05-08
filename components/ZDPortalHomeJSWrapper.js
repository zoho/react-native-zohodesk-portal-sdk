const {NativeModules} = require('react-native');
const {RNZDPortalHome} = NativeModules;
module.exports = {

    show: function() {
      RNZDPortalHome.show();
    }
}
