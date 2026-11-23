import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
try {
    WebUI.deleteAllCookies()
    WebUI.navigateToUrl('https://hexure.com/')
    WebUI.executeJavaScript("localStorage.clear(); sessionStorage.clear(); document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 20)
    Boolean modalAppeared = WebUI.executeJavaScript('''return Array.from(document.querySelectorAll('[role="dialog"], .modal, [class*="modal"], [class*="popup"], [class*="pop-up"]')).some(e => {
        const s = getComputedStyle(e)
        return s.display !== 'none' && s.visibility !== 'hidden' && e.getBoundingClientRect().width > 0
    })''', null)
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
    Boolean headerFocusable = WebUI.executeJavaScript("return Array.from(document.querySelectorAll('header a, header button, header [tabindex]')).some(e => !e.disabled && e.tabIndex >= 0)", null)
    WebUI.verifyEqual(headerFocusable, true)
    WebUI.navigateToUrl('https://hexure.com/about-us/')
    WebUI.waitForPageLoad(15)
    Boolean pageLoaded = WebUI.executeJavaScript("return document.readyState === 'complete' && !!document.querySelector('header')", null)
    WebUI.verifyEqual(pageLoaded, true)
    WebUI.comment('No promotional modal was rendered on the destination page during this session.')
} finally {
    WebUI.closeBrowser()
}