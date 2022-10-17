const {NativeModules} = require('react-native');
const {RNZDPortalLiveChat} = NativeModules;
module.exports = {

  show: function ()
  {
      RNZDPortalLiveChat.show();
    }
}
