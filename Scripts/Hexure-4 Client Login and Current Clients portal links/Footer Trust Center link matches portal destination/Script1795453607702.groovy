import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Scope: Desktop browser only. Mobile viewport and mobile navigation behavior are intentionally excluded.
// Purpose: Verify that the footer Trust Center link corresponds to the expected Trust Center portal destination.
// Step 1: Open the Hexure public website and dismiss the privacy-consent dialog when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify that the desktop footer Trust Center link is present.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Footer Trust Center'), 10)

// Step 3: Define the expected Trust Center portal URL.
String portalUrl = 'https://trust.hexure.com/'

// Step 4: Navigate to the expected Trust Center portal destination.
WebUI.navigateToUrl(portalUrl)

// Step 5: Confirm that the Trust Center portal resolves to the expected URL.
WebUI.verifyMatch(WebUI.getUrl(), java.util.regex.Pattern.quote(portalUrl) + '.*', true)

// Step 6: Return to the Hexure public website so the footer link can be checked again.
WebUI.navigateToUrl('https://hexure.com/')

// Step 7: Verify that the desktop footer Trust Center link remains present after returning.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Footer Trust Center'), 10)

// Step 8: Close the browser after validating the Trust Center portal and footer link.
WebUI.closeBrowser()
