import { NativeModules, Platform } from 'react-native';
import ZDPortalChatKitWrapper from './src/Components/ZDPortalChatKitWrapper'

const LINKING_ERROR =
  `The package 'zohodesk-portal-chatkit' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const ZohodeskPortalChatkit = NativeModules.ZohodeskPortalChatkit
  ? NativeModules.ZohodeskPortalChatkit
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );


module.exports = {
  ZohoDeskPortalChatKit: ZDPortalChatKitWrapper
}