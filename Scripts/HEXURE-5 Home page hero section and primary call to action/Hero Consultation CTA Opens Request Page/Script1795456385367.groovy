import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the public Hexure website and handle privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify the hero headline, body, and image container are present.
WebUI.verifyElementPresent(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Headline'), 10)
WebUI.verifyElementPresent(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Body Text'), 10)
WebUI.verifyElementPresent(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Image'), 10)

// Step 3: Verify the hero CTA is visible and targets the expected destination.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA'))
String heroCtaHref = WebUI.getAttribute(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA'), 'href').replaceAll('/+$', '')
WebUI.verifyEqual(heroCtaHref, 'https://hexure.com/request-consultation')

// Step 4: Resolve the destination and verify the normalized URL path.
WebUI.navigateToUrl('https://hexure.com/request-consultation/')
String actualUrl = WebUI.getUrl().replaceAll('/+$', '')
WebUI.verifyMatch(actualUrl, '(?i)^https://hexure\\.com/request-consultation(?:\\?.*)?$', true)

WebUI.closeBrowser()
