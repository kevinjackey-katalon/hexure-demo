import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify dropdown controls and mobile navigation remain available at target viewports.
 * Expected result: Four desktop menu triggers are actionable at 1024 px and the mobile toggle is visible at 390 px.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
// Step 2: Set the desktop target viewport and remove consent overlays.
WebUI.setViewPortSize(1024, 900)
WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
// Step 3: Open each desktop dropdown using its visible control and verify the object.
[
    ['HEXURE2_SolutionsMenu', '#e-n-menu-dropdown-icon-3131'],
    ['HEXURE2_ProductsMenu', '#e-n-menu-dropdown-icon-3132'],
    ['HEXURE2_ResourcesMenu', '#e-n-menu-dropdown-icon-3133'],
    ['HEXURE2_AboutUsMenu', '#e-n-menu-dropdown-icon-3134']
].each { item ->
    WebUI.executeJavaScript("document.querySelector('${item[1]}')?.click()", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + item[0]), 10)
}
// Step 4: Resize to mobile and verify the mobile navigation toggle.
WebUI.setViewPortSize(390, 844)
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'), 10)
// Step 5: Close the browser.
WebUI.closeBrowser()