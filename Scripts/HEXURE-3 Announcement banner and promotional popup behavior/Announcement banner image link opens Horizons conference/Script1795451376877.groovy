import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the announcement banner image link opens the Horizons conference destination.
 * Expected result: The destination URL contains "horizons".
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
try {
    // Step 2: Remove any consent overlay and verify the banner/link are present.
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 15)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_HorizonsLink'), 15)
    // Step 3: Activate the Horizons conference link.
    WebUI.executeJavaScript("document.querySelector('a.eael-wrapper-link-91203ce')?.click()", null)
    WebUI.waitForPageLoad(15)
    // Step 4: Verify navigation reached the Horizons destination using a normalized URL.
    String actualUrl = WebUI.getUrl().replaceAll('/+$', '')
    WebUI.verifyMatch(actualUrl, '(?i)^https?://[^/]*horizons[^/]*(?:/.*)?$', true)
    WebUI.comment('The destination was opened; browser navigation is the authoritative HTTP check for this UI test.')
} finally {
    // Step 5: Close the browser.
    WebUI.closeBrowser()
}
