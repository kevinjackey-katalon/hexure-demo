import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify navigation remains usable when resizing between desktop and mobile breakpoints.
 * Expected result: Desktop menu is available at 1280 px and the mobile toggle is available at 390 px.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Set a desktop viewport and open Solutions.
WebUI.setViewPortSize(1280, 900)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_SolutionsMenu'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_SolutionsMenu'), 10)
// Step 3: Resize to the mobile viewport and verify the mobile toggle.
WebUI.setViewPortSize(390, 844)
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'), 10)
// Step 4: Remove any consent remnants and open the mobile menu.
WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-bar')?.remove();", null)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'))
// Step 5: Close the browser.
WebUI.closeBrowser()