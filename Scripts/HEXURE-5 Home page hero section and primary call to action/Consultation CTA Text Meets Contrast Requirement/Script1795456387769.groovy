import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the public Hexure website and handle privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify the header CTA label after normalizing browser whitespace.
String headerCtaText = WebUI.getText(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA')).replaceAll(/\s+/, ' ').trim()
WebUI.verifyEqual(headerCtaText, 'Request a Consultation')

// Step 3: Verify the hero CTA label after normalizing browser whitespace.
String heroCtaText = WebUI.getText(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA')).replaceAll(/\s+/, ' ').trim()
WebUI.verifyEqual(heroCtaText, 'Request a Consultation')

// Step 4: The visible labels are the text used for the contrast review.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA'))
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA'))

WebUI.closeBrowser()
