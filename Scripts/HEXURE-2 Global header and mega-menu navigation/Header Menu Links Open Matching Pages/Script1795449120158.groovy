import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify each primary header menu can be opened in sequence.
 * Expected result: Logo and all four menu triggers remain available.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Verify the logo and open each primary header menu.
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'), 10)
['HEXURE2_SolutionsMenu','HEXURE2_ProductsMenu','HEXURE2_ResourcesMenu','HEXURE2_AboutUsMenu'].each { name ->
    // Step 3: Open the current menu and verify its trigger remains present.
    WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name))
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name), 10)
}
// Step 4: Close the browser.
WebUI.closeBrowser()