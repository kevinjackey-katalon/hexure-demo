import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.navigateToUrl('https://hexure.com/')
['HEXURE2_SolutionsMenu','HEXURE2_ProductsMenu','HEXURE2_ResourcesMenu','HEXURE2_AboutUsMenu'].each { name ->
    def menu = findTestObject('Object Repository/HEXURE-2 Global header and mega-menu navigation/' + name)
    WebUI.sendKeys(menu, Keys.ENTER.toString())
    WebUI.verifyElementPresent(menu, 10)
    WebUI.sendKeys(menu, Keys.ESCAPE.toString())
}
WebUI.closeBrowser()