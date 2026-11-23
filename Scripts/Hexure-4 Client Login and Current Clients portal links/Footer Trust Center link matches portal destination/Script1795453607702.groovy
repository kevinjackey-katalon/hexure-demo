import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the Hexure public website and dismiss privacy consent when present.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Confirm that the footer Trust Center link is available.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Footer Trust Center'), 10)

// Step 3: Define the expected Trust Center portal destination.
String portalUrl = 'https://trust.hexure.com/'

// Step 4: Navigate to the expected Trust Center portal destination.
WebUI.navigateToUrl(portalUrl)

// Step 5: Verify that the Trust Center portal resolves to the expected URL.
WebUI.verifyMatch(WebUI.getUrl(), java.util.regex.Pattern.quote(portalUrl) + '.*', true)

// Step 6: Return to the Hexure public website to confirm the footer link remains available.
WebUI.navigateToUrl('https://hexure.com/')

// Step 7: Verify that the footer Trust Center link is still present after returning.
WebUI.verifyElementPresent(findTestObject('Hexure-4 Client Login and Current Clients portal links/Footer Trust Center'), 10)

// Step 8: Close the browser after validating the Trust Center destination and footer link.
WebUI.closeBrowser()
