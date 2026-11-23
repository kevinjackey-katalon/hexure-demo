import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the announcement banner's Learn More link opens the iCapital press release.
 * Expected result: The destination URL contains "icapital".
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
try {
    // Step 2: Remove consent overlays and verify the banner/link are available.
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 15)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_LearnMoreLink'), 15)
    // Step 3: Click Learn More and wait for navigation.
    WebUI.click(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_LearnMoreLink'))
    WebUI.waitForPageLoad(15)
    // Step 4: Verify the press-release destination using a normalized URL.
    String actualUrl = WebUI.getUrl().replaceAll('/+$', '')
    WebUI.verifyMatch(actualUrl, '(?i)^https?://[^/]*icapital[^/]*(?:/.*)?$', true)
    WebUI.comment('The destination was opened; browser navigation is the authoritative HTTP check for this UI test.')
} finally {
    // Step 5: Close the browser.
    WebUI.closeBrowser()
}
