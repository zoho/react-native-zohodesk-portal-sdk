import { NativeModules, Platform } from 'react-native';
import {RNZohoDeskPortalSalesIQWrapper} from './src/Components/RNZohoDeskPortalSalesIQWrapper'

const LINKING_ERROR =
  `The package 'zohodesk-portal-salesiq' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const ZohodeskPortalSalesiq = NativeModules.ZohodeskPortalSalesiq
  ? NativeModules.ZohodeskPortalSalesiq
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

module.exports = {
  ZohoDeskPortalSalesIQ: RNZohoDeskPortalSalesIQWrapper
}

