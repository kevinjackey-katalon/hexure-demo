import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
try {
    WebUI.navigateToUrl('https://hexure.com/')
    WebUI.setViewPortSize(1440, 900)
    WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_AnnouncementBanner'), 20)
    Boolean hasText = WebUI.executeJavaScript("return document.body.innerText.includes('Hexure Is Now Part of iCapital.') && document.body.innerText.includes('Learn More')", null)
    WebUI.verifyEqual(hasText, true)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-3 Announcement banner and promotional popup behavior/HEXURE3_Header'), 15)
    Boolean jsHealthy = WebUI.executeJavaScript("return document.readyState === 'complete' && !!document.body", null)
    WebUI.verifyEqual(jsHealthy, true)
} finally {
    WebUI.closeBrowser()
}