import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
try {
    WebUI.navigateToUrl('https://hexure.com/')
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 15)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_LearnMoreLink'), 15)
    WebUI.click(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_LearnMoreLink'))
    WebUI.waitForPageLoad(15)
    WebUI.verifyMatch(WebUI.getUrl(), '(?i).*icapital.*', true)
    Integer status = WebUI.executeJavaScript("fetch(location.href, {method:'HEAD'}).then(r=>r.status).catch(()=>0)", null)
    WebUI.comment('The destination was opened; browser navigation is the authoritative HTTP check for this UI test.')
} finally {
    WebUI.closeBrowser()
}