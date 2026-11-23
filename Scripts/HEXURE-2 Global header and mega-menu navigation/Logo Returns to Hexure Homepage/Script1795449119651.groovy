import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify selecting the global logo returns to the Hexure homepage.
 * Expected result: The final URL is https://hexure.com/.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Verify the global logo is available.
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'), 10)
// Step 3: Select the logo.
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'))
// Step 4: Verify navigation returned to the homepage after normalizing the trailing slash.
String actualUrl = WebUI.getUrl().replaceAll('/+$', '')
WebUI.verifyEqual(actualUrl, 'https://hexure.com')
// Step 5: Close the browser.
WebUI.closeBrowser()
