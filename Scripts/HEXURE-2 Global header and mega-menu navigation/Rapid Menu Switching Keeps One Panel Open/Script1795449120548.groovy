import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the primary desktop menus can be switched sequentially.
 * Expected result: Each menu trigger remains available as the active menu changes.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Open each primary menu in rapid sequence and verify its trigger.
['HEXURE2_SolutionsMenu','HEXURE2_ProductsMenu','HEXURE2_ResourcesMenu','HEXURE2_AboutUsMenu'].each { name ->
    WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name))
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name), 10)
}
// Step 3: Return focus to the homepage via the logo.
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'))
// Step 4: Close the browser.
WebUI.closeBrowser()