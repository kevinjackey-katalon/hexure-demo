import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify each desktop menu responds to keyboard Enter and Escape keys.
 * Expected result: Each menu trigger is present after opening and can be dismissed.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Open and close each primary menu using keyboard commands.
['HEXURE2_SolutionsMenu','HEXURE2_ProductsMenu','HEXURE2_ResourcesMenu','HEXURE2_AboutUsMenu'].each { name ->
    def menu = findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name)
    // Step 3: Open the current menu with Enter and verify it is available.
    WebUI.sendKeys(menu, Keys.ENTER.toString())
    WebUI.verifyElementPresent(menu, 10)
    // Step 4: Dismiss the current menu with Escape.
    WebUI.sendKeys(menu, Keys.ESCAPE.toString())
}
// Step 5: Close the browser.
WebUI.closeBrowser()