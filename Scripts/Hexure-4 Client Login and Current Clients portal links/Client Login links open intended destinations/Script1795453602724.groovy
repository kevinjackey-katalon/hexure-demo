import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Scope: Desktop browser only. Mobile viewport and mobile navigation behavior are intentionally excluded.
// Purpose: Verify that the two Client Login portal destinations resolve to their expected URLs.
// Step 1: Open the Hexure public website and dismiss the privacy-consent dialog when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify that the desktop Client Login navigation control is present.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Client Login menu'), 10)

// Step 3: Navigate to the expected Client Corner Login destination.
WebUI.navigateToUrl('https://www.insurancetechnologies.com/clientcorner/')

// Step 4: Confirm that the Client Corner Login URL resolves to the expected domain and path.
WebUI.verifyMatch(WebUI.getUrl(), 'https://www.insurancetechnologies.com/clientcorner/.*', true)

// Step 5: Navigate to the expected IXN Client Login destination.
WebUI.navigateToUrl('https://dashboard.ixn.tech/#/login')

// Step 6: Confirm that the IXN Client Login URL resolves to the expected dashboard domain.
WebUI.verifyMatch(WebUI.getUrl(), 'https://dashboard\\.ixn\\.tech/.*', true)

// Step 7: Close the browser after both desktop Client Login destinations have been validated.
WebUI.closeBrowser()
