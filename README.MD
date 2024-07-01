# Compose Multiplatform Application

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 or higher on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`
Run android simulator UI tests: `./gradlew :composeApp:pixel5Check`

### Desktop
Run the desktop application: `./gradlew :composeApp:run`
Run desktop UI tests: `./gradlew :composeApp:jvmTest`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
Run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`

### Wasm Browser (Alpha)
Run the browser application: `./gradlew :composeApp:wasmJsBrowserDevelopmentRun --continue`
Run browser UI tests: `./gradlew :composeApp:wasmJsBrowserTest`

# Image Details

All images used in this project are licensed under various Creative Commons licenses. Specific 
details for each image are provided in the table below:

# Image Details

All images used in this project are licensed under various Creative Commons licenses. Specific details for each image are provided in the table below:

| **Image** | **Details** |
| --- | --- |
| <img alt="Pitched-roof pavilion sits on top of a London office building" src="/composeApp/src/commonMain/composeResources/drawable/img_01.png" width="240px"/> | **License details**: [CC BY-NC-ND 3.0](http://creativecommons.org/licenses/by-nc-nd/3.0/)<br>**Creator**: Rory Gardiner<br>**Copyright**: © RORY GARDINER 2014 |
| <img alt="Alien's architecture" src="/composeApp/src/commonMain/composeResources/drawable/img_02.png" width="240px"/> | **License details**: [CC BY-NC-ND 3.0](http://creativecommons.org/licenses/by-nc-nd/3.0/)<br>**Creator**: ezio |
| <img alt="Leonardo Glass Cube - Corporate architecture" src="/composeApp/src/commonMain/composeResources/drawable/img_03.png" width="240px"/> | **Source**: [Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Leonardo_Glass_Cube_-_Corporate_architecture.jpg)<br>**License details**: [CC BY-NC-ND 3.0](http://creativecommons.org/licenses/by-nc-nd/3.0/) |
| <img alt="Fish-eye lens architectural photography of concrete building" src="/composeApp/src/commonMain/composeResources/drawable/img_04.png" width="240px"/> | **License details**: [Public Domain](https://creativecommons.org/licenses/publicdomain/) |
| <img alt="Cove Hollow Farm Road Residence - Architecture Outfit" src="/composeApp/src/commonMain/composeResources/drawable/img_05.png" width="240px"/> | **License details**: [CC BY-NC-ND 4.0](http://creativecommons.org/licenses/by-nc-nd/4.0/)<br>**Creator**: Eric Petschek<br>**Copyright**: ©2021, Eric Petschek |
| <img alt="Colorful historic European city at dawn" src="/composeApp/src/commonMain/composeResources/drawable/img_06.png" width="240px"/> | **Source**: [PxHere](https://pxhere.com/en/photo/1592361)<br>**License details**: [Public Domain](https://creativecommons.org/licenses/publicdomain/)<br>**Creator**: Boris Stroujko<br>**Credit**: Shutterstock / Boris Stroujko |
