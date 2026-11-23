import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the Resources menu opens within the global header.
 * Expected result: Resources and the site logo remain present after opening the menu.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Open the Resources menu.
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_ResourcesMenu'))
// Step 3: Verify the menu trigger and logo remain present.
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_ResourcesMenu'), 10)
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'), 10)
// Step 4: Close the browser.
WebUI.closeBrowser()