import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('https://hexure.com/')
WebUI.setViewPortSize(1024, 900)
WebUI.executeJavaScript("document.querySelector('.cky-overlay')?.remove(); document.querySelector('.cky-consent-container')?.remove();", null)
[
    ['HEXURE2_SolutionsMenu', '#e-n-menu-dropdown-icon-3131'],
    ['HEXURE2_ProductsMenu', '#e-n-menu-dropdown-icon-3132'],
    ['HEXURE2_ResourcesMenu', '#e-n-menu-dropdown-icon-3133'],
    ['HEXURE2_AboutUsMenu', '#e-n-menu-dropdown-icon-3134']
].each { item ->
    WebUI.executeJavaScript("document.querySelector('${item[1]}')?.click()", null)
    WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + item[0]), 10)
}
WebUI.setViewPortSize(390, 844)
WebUI.verifyElementPresent(findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/HEXURE2_MobileToggle'), 10)
WebUI.closeBrowser()