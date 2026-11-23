import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the announcement banner is visible above the homepage header without overlap.
 * Expected result: Required banner/header content is present and the banner bottom is above the header top.
 */
// Step 1: Open the homepage and accept privacy consent if shown.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
try {
    // Step 2: Set a desktop viewport and remove consent overlays.
    WebUI.setViewPortSize(1440, 900)
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    // Step 3: Verify the banner and header objects are present.
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 15)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_Header'), 15)
    // Step 4: Verify the announcement copy is visible.
    Boolean hasText = WebUI.executeJavaScript("return document.body.innerText.includes('Hexure Is Now Part of iCapital.') && document.body.innerText.includes('Learn More')", null)
    WebUI.verifyEqual(hasText, true)
    // Step 5: Verify the banner does not overlap the header.
    Boolean noOverlap = WebUI.executeJavaScript('''return (() => {
        const b = document.querySelector('[data-id="5f4dd0d"]');
        const h = document.querySelector('[data-id="fe36e33"]');
        if (!b || !h) return false;
        const br = b.getBoundingClientRect();
        const hr = h.getBoundingClientRect();
        return br.bottom <= hr.top + 2;
    })()''', null)
    WebUI.verifyEqual(noOverlap, true)
} finally {
    // Step 6: Close the browser.
    WebUI.closeBrowser()
}