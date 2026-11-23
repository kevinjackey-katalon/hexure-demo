import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the public Hexure website and handle privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify the header CTA is visible and has the expected destination.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA'))
WebUI.mouseOver(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA'))
WebUI.verifyElementAttributeValue(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA'), 'href', 'https://hexure.com/request-consultation/', 10)

// Step 3: Verify the hero CTA is visible and has the expected destination.
WebUI.verifyElementVisible(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA'))
WebUI.mouseOver(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA'))
WebUI.verifyElementAttributeValue(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA'), 'href', 'https://hexure.com/request-consultation/', 10)

// Step 4: Both anchors are keyboard-addressable native links; verify they remain enabled.
WebUI.verifyElementClickable(findTestObject('HEXURE-5 Home page hero section and primary call to action/Header Consultation CTA'))
WebUI.verifyElementClickable(findTestObject('HEXURE-5 Home page hero section and primary call to action/Hero Consultation CTA'))

WebUI.closeBrowser()