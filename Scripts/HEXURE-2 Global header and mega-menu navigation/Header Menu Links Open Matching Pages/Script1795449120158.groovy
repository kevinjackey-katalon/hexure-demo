import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('https://hexure.com/')
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_Logo'), 10)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_SolutionsMenu'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_SolutionsMenu'), 10)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_ProductsMenu'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_ProductsMenu'), 10)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_ResourcesMenu'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_ResourcesMenu'), 10)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_AboutUsMenu'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_AboutUsMenu'), 10)
WebUI.closeBrowser()