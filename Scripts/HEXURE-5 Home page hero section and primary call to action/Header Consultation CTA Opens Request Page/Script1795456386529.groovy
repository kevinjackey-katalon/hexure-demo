import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the public Hexure website and handle privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify the header CTA is visible and points to the expected page.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA'))
String headerCtaHref = WebUI.getAttribute(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA'), 'href').replaceAll('/+$', '')
WebUI.verifyEqual(headerCtaHref, 'https://hexure.com/request-consultation')

// Step 3: Resolve the destination and verify the normalized URL path.
WebUI.navigateToUrl('https://hexure.com/request-consultation/')
String actualUrl = WebUI.getUrl().replaceAll('/+$', '')
WebUI.verifyMatch(actualUrl, '(?i)^https://hexure\\.com/request-consultation(?:\\?.*)?$', true)

WebUI.closeBrowser()
