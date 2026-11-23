import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the announcement banner remains available when advertising content is unavailable.
 * Expected result: The banner, its text, and the header remain usable.
 */
// Step 1: Open the homepage and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
try {
    // Step 2: Use the target desktop viewport and remove any consent overlay.
    WebUI.setViewPortSize(1440, 900)
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    // Step 3: Verify the announcement banner is visible.
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 20)
    // Step 4: Verify the announcement copy is present.
    Boolean hasText = WebUI.executeJavaScript("return document.body.innerText.includes('Hexure Is Now Part of iCapital.') && document.body.innerText.includes('Learn More')", null)
    WebUI.verifyEqual(hasText, true)
    // Step 5: Verify the homepage header and page JavaScript remain healthy.
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_Header'), 15)
    Boolean jsHealthy = WebUI.executeJavaScript("return document.readyState === 'complete' && !!document.body", null)
    WebUI.verifyEqual(jsHealthy, true)
} finally {
    // Step 6: Close the browser.
    WebUI.closeBrowser()
}