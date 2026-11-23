import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('https://hexure.com/')
WebUI.setViewPortSize(1280, 900)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_SolutionsMenu'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_SolutionsMenu'), 10)
WebUI.setViewPortSize(390, 844)
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'), 10)
WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-bar')?.remove();", null)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'))
WebUI.closeBrowser()