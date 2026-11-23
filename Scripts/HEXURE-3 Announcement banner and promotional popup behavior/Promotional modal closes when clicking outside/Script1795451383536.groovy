import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Purpose: Verify an available promotional modal closes after clicking outside it.
 * Expected result: Any visible modal closes and the page remains usable.
 */
// Step 1: Open the homepage and accept privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')
try {
    // Step 2: Reset storage and remove consent overlays.
    WebUI.deleteAllCookies()
    WebUI.executeJavaScript("localStorage.clear(); sessionStorage.clear(); document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    // Step 3: Verify the announcement banner and detect a visible promotional modal.
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 20)
    Boolean modalAppeared = WebUI.executeJavaScript('''return Array.from(document.querySelectorAll('[role="dialog"], .modal, [class*="modal"], [class*="popup"], [class*="pop-up"]')).some(e => {
        const s = getComputedStyle(e)
        return s.display !== 'none' && s.visibility !== 'hidden' && e.getBoundingClientRect().width > 0
    })''', null)
    // Step 4: Click outside the modal when one is present and verify it closes.
    if (modalAppeared) {
        WebUI.executeJavaScript("document.elementFromPoint(5, 5)?.click()", null)
        WebUI.delay(1)
        Boolean modalClosed = WebUI.executeJavaScript('''return !Array.from(document.querySelectorAll('[role="dialog"], .modal, [class*="modal"], [class*="popup"], [class*="pop-up"]')).some(e => {
            const s = getComputedStyle(e)
            return s.display !== 'none' && s.visibility !== 'hidden' && e.getBoundingClientRect().width > 0
        })''', null)
        WebUI.verifyEqual(modalClosed, true)
    } else {
        WebUI.comment('No promotional modal appeared in this environment; outside-click behavior was not applicable.')
    }
    // Step 5: Verify the header remains keyboard focusable and a secondary page loads.
    Boolean headerFocusable = WebUI.executeJavaScript("return Array.from(document.querySelectorAll('header a, header button, header [tabindex]')).some(e => !e.disabled && e.tabIndex >= 0)", null)
    WebUI.verifyEqual(headerFocusable, true)
    WebUI.navigateToUrl('https://hexure.com/about-us/')
    WebUI.waitForPageLoad(15)
    Boolean pageLoaded = WebUI.executeJavaScript("return document.readyState === 'complete' && !!document.querySelector('header')", null)
    WebUI.verifyEqual(pageLoaded, true)
} finally {
    // Step 6: Close the browser.
    WebUI.closeBrowser()
}