import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
try {
    WebUI.navigateToUrl('https://hexure.com/')
    WebUI.setViewPortSize(1440, 900)
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 15)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_Header'), 15)
    Boolean hasText = WebUI.executeJavaScript("return document.body.innerText.includes('Hexure Is Now Part of iCapital.') && document.body.innerText.includes('Learn More')", null)
    WebUI.verifyEqual(hasText, true)
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
    WebUI.closeBrowser()
}