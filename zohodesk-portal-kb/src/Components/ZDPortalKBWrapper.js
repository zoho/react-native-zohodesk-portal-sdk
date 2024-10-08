const {NativeModules} = require('react-native');
const {RNZohoDeskPortalKB} = NativeModules;

module.exports = {
    show: function() {
      RNZohoDeskPortalKB.show();
    },

    showArticle: function(withPermalink) {
      RNZohoDeskPortalKB.showArticle(withPermalink);
    },

    showCategory: function(withPermalink){
      RNZohoDeskPortalKB.showCategory(withPermalink);
    }
}