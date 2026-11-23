import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the Hexure public website and dismiss privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Confirm the Resources navigation control is available.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Resources menu'), 10)

// Step 3: Expand Resources so the Current Clients link is rendered in the page.
WebUI.click(findTestObject('Hexure-4 Client Login and Current Clients portal links/Resources menu'))

// Step 4: Confirm the Current Clients link is available in the expanded Resources menu.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Current Clients link'), 10)

// Step 5: Define the expected destinations for every Current Clients link.
String[] destinations = [
    'https://www.insurancetechnologies.com/clientcorner/',
    'https://dashboard.ixn.tech/#/login',
    'https://hexureacademy.tovuti.io/',
    'https://hexure.com/beyond-horizons/',
    'https://support.ixntech.com/en',
    'https://hexure.com/support/',
    'https://trust.hexure.com/'
]

// Step 6: Visit each expected destination and verify the resulting URL.
destinations.each { destination ->
    WebUI.navigateToUrl(destination)
    WebUI.verifyMatch(WebUI.getUrl(), java.util.regex.Pattern.quote(destination) + '.*', true)
}

// Step 7: Close the browser after all destination checks complete.
WebUI.closeBrowser()
