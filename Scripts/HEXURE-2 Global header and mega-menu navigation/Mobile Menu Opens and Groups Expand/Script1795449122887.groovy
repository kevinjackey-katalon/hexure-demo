import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('https://hexure.com/')
WebUI.setViewPortSize(390, 844)
WebUI.click(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'))
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'), 10)
['HEXURE2_SolutionsMenu','HEXURE2_ProductsMenu','HEXURE2_ResourcesMenu','HEXURE2_AboutUsMenu'].each { name ->
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name), 10)
}
WebUI.closeBrowser()