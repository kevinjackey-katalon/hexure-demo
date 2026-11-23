import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the public Hexure website and handle privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify the hero headline is visible and readable.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Headline'))
String heroHeadlineText = WebUI.getText(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Headline')).replaceAll(/\s+/, ' ').trim()
WebUI.verifyEqual(heroHeadlineText, 'Connecting Sales for Insurance and Financial Services')

// Step 3: Verify the hero body text is visible and non-empty.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Body Text'))
WebUI.verifyNotEqual(WebUI.getText(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Body Text')).replaceAll(/\s+/, ' ').trim(), '')

WebUI.closeBrowser()
