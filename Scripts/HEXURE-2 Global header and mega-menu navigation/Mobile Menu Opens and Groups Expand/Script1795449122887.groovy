import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the mobile navigation opens and exposes all primary menu groups.
 * Expected result: The mobile menu toggle and four menu groups are visible.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Switch to the mobile viewport.
WebUI.setViewPortSize(390, 844)
// Step 3: Open the mobile navigation.
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'), 10)
// Step 4: Verify each primary group is available.
['HEXURE2_SolutionsMenu','HEXURE2_ProductsMenu','HEXURE2_ResourcesMenu','HEXURE2_AboutUsMenu'].each { name ->
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name), 10)
}
// Step 5: Close the browser.
WebUI.closeBrowser()