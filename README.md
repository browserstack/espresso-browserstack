# espresso-browserstack

[Espresso](https://developer.android.com/training/testing/espresso/index.html) Integration with BrowserStack

![BrowserStack Logo](https://d98b8t1nnulk5.cloudfront.net/production/images/layout/logo-header.png?1469004780)

<img src ="https://developer.android.com/images/training/testing/espresso.png" height = "300">

## Setup

* Clone the repo
* Download JDK8 from [here](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html)
* Update your JDK location via Project Structure (Android Studio)
    * Go to File > Project Structure.
    * Select the SDK Location section in the list of the left.
    * Enter the absolute path of your installed JDK in the text box.
* Build the main application: `./gradlew assemble` (apk will be generated in the `app/build/outputs/apk/debug/` directory)
* Build the test application: `./gradlew assembleAndroidTest` (apk will be generated in the `app/build/outputs/apk/androidTest/debug/` directory)
* Upload both the apk files to BrowserStack and start a session. Refer our documentation for details: [Using Espresso with BrowserStack](https://www.browserstack.com/app-automate/espresso/get-started)

## Notes
* You can view your test results on the [BrowserStack app-automate dashboard](https://www.browserstack.com/app-automate)

## Additional Resources
* [Customizing your tests on BrowserStack](https://www.browserstack.com/app-automate/capabilities)
* [Browsers & mobile devices for app-automate testing on BrowserStack](https://www.browserstack.com/list-of-browsers-and-platforms?product=app_automate)
* [Using REST API to access information about your builds via the command-line interface](https://www.browserstack.com/app-automate/rest-api)
* [Using Espresso with BrowserStack](https://www.browserstack.com/app-automate/espresso/get-started)
* [Generating JUnit XML report or Test coverage report using Espresso with BrowserStack](https://www.browserstack.com/docs/app-automate/espresso/view-test-reports). 
   example:  
   curl -u "< username >:< password >" \
   -X POST "https://api-cloud.browserstack.com/app-automate/espresso/v2/build" \
   -d '{"app": "bs://<app_id>", "testSuite": "bs://<test_suite_id>", "devices": ["Google Pixel-8.0"], "coverage": true, "enableSpoonFramework": true }' \
   -H "Content-Type: application/json"
