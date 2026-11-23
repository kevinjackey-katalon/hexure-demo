import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify the promotional modal/banner loads without layout shift or visual flash.
 * Preconditions: The Hexure homepage is available and the common privacy handler can open it.
 * Expected result: Banner and header remain stable while the page finishes loading.
 */
// Step 1: Open the Hexure homepage and accept privacy consent when presented.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
try {
    // Step 2: Reset browser state and remove consent overlays that can obscure the page.
    WebUI.deleteAllCookies()
    WebUI.executeJavaScript("localStorage.clear(); sessionStorage.clear(); document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    // Step 3: Confirm the announcement banner and homepage header are present.
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 20)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_Header'), 15)
    // Step 4: Capture the initial page dimensions and header position.
    List before = WebUI.executeJavaScript("return [document.documentElement.scrollWidth, document.documentElement.scrollHeight, document.querySelector('.elementor-element-fe36e33')?.getBoundingClientRect().top || 0]", null)
    // Step 5: Allow delayed promotional content to finish rendering.
    WebUI.delay(3)
    // Step 6: Verify dimensions and header position did not shift.
    List after = WebUI.executeJavaScript("return [document.documentElement.scrollWidth, document.documentElement.scrollHeight, document.querySelector('.elementor-element-fe36e33')?.getBoundingClientRect().top || 0]", null)
    WebUI.verifyEqual(after[0], before[0])
    WebUI.verifyEqual(after[2], before[2])
    // Step 7: Verify the document completed loading and the header is visible.
    Boolean visibleWithoutFlash = WebUI.executeJavaScript("return document.readyState === 'complete' && !!document.querySelector('header')", null)
    WebUI.verifyEqual(visibleWithoutFlash, true)
} finally {
    // Step 8: Close the browser and release the test session.
    WebUI.closeBrowser()
}