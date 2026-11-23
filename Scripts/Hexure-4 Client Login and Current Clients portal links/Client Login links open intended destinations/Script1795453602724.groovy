import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the Hexure public website and dismiss privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Confirm that the Client Login navigation control is available.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Client Login menu'), 10)

// Step 3: Navigate to the Client Corner Login destination.
WebUI.navigateToUrl('https://www.insurancetechnologies.com/clientcorner/')

// Step 4: Verify that Client Corner Login resolves to the expected destination.
WebUI.verifyMatch(WebUI.getUrl(), 'https://www.insurancetechnologies.com/clientcorner/.*', true)

// Step 5: Navigate to the IXN Client Login destination.
WebUI.navigateToUrl('https://dashboard.ixn.tech/#/login')

// Step 6: Verify that IXN Client Login resolves to the expected dashboard domain.
WebUI.verifyMatch(WebUI.getUrl(), 'https://dashboard\\.ixn\\.tech/.*', true)

// Step 7: Close the browser after both Client Login destinations have been validated.
WebUI.closeBrowser()
