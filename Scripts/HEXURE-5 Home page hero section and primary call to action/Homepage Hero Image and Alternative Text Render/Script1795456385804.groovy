import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the public Hexure website and handle privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify the hero visual region renders without a broken element.
WebUI.verifyElementPresent(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Image'), 10)
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Image'))

// Step 3: Verify the associated hero content is present and readable.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Headline'))
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Body Text'))

WebUI.closeBrowser()