import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
try {
    WebUI.deleteAllCookies()
    WebUI.navigateToUrl('https://hexure.com/')
    WebUI.executeJavaScript("localStorage.clear(); sessionStorage.clear(); document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 20)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_Header'), 15)
    Map before = WebUI.executeJavaScript("return {w: document.documentElement.scrollWidth, h: document.documentElement.scrollHeight, top: document.querySelector('.elementor-element-fe36e33')?.getBoundingClientRect().top || 0}", null)
    WebUI.delay(3)
    Map after = WebUI.executeJavaScript("return {w: document.documentElement.scrollWidth, h: document.documentElement.scrollHeight, top: document.querySelector('.elementor-element-fe36e33')?.getBoundingClientRect().top || 0}", null)
    WebUI.verifyEqual(after.w, before.w)
    WebUI.verifyEqual(after.top, before.top)
    Boolean visibleWithoutFlash = WebUI.executeJavaScript("return document.readyState === 'complete' && !!document.querySelector('header')", null)
    WebUI.verifyEqual(visibleWithoutFlash, true)
} finally {
    WebUI.closeBrowser()
}