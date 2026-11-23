import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('https://hexure.com/')
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'), 10)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'))
WebUI.verifyEqual(WebUI.getUrl(), 'https://hexure.com/')
WebUI.closeBrowser()