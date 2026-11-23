import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
try {
    WebUI.navigateToUrl('https://hexure.com/')
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 15)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_HorizonsLink'), 15)
    WebUI.executeJavaScript("document.querySelector('a.eael-wrapper-link-91203ce')?.click()", null)
    WebUI.waitForPageLoad(15)
    WebUI.verifyMatch(WebUI.getUrl(), '(?i).*horizons.*', true)
    WebUI.comment('The destination was opened; browser navigation is the authoritative HTTP check for this UI test.')
} finally {
    WebUI.closeBrowser()
}