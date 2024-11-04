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
    },

    setConfiguration: function(ZDKBConfiguration={}){
      const config = {
        disableArticleLike: ZDKBConfiguration?.disableArticleLike ?? false,
        disableArticleDislike: ZDKBConfiguration?.disableArticleDislike ?? false,
        disableArticleDetailSearch: ZDKBConfiguration?.disableArticleDetailSearch ?? false
      };
      RNZohoDeskPortalKB.setConfiguration(config);
    }
}