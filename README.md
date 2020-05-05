
# react-native-zohodesk-portal-sdk

## Getting started

`$ npm install react-native-zohodesk-portal-sdk --save`

### Mostly automatic installation

`$ react-native link react-native-zohodesk-portal-sdk`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-zohodesk-portal-sdk` and add `RNZohodeskPortalSdk.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNZohodeskPortalSdk.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNZohodeskPortalSdkPackage;` to the imports at the top of the file
  - Add `new RNZohodeskPortalSdkPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-zohodesk-portal-sdk'
  	project(':react-native-zohodesk-portal-sdk').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-zohodesk-portal-sdk/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-zohodesk-portal-sdk')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNZohodeskPortalSdk.sln` in `node_modules/react-native-zohodesk-portal-sdk/windows/RNZohodeskPortalSdk.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Zohodesk.Portal.Sdk.RNZohodeskPortalSdk;` to the usings at the top of the file
  - Add `new RNZohodeskPortalSdkPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNZohodeskPortalSdk from 'react-native-zohodesk-portal-sdk';

// TODO: What to do with the module?
RNZohodeskPortalSdk;
```
  