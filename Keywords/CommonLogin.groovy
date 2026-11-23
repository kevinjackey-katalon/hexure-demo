import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Common browser and privacy-consent actions shared by Hexure test cases.
 */
class CommonLogin {

    /**
     * Purpose: Open the requested URL and dismiss the privacy-consent dialog when it is displayed.
     *
     * Expected result: A browser is open on the requested URL. If the
     * "We value your privacy" dialog is present, it is dismissed by clicking
     * the "Accept All" button. If the dialog is not displayed, execution
     * continues without failing the test.
     *
     * @param url URL to open; defaults to the Hexure homepage.
     */
    @Keyword
    def openUrlAndAcceptPrivacy(String url = 'https://hexure.com/') {
        // Step 1: Open a new browser session.
        WebUI.openBrowser('')

        // Step 2: Navigate to the requested application URL.
        WebUI.navigateToUrl(url)

        // Step 3: Allow the privacy dialog time to render after navigation.
        WebUI.delay(5)

        // Step 4: Define a locator for the privacy-consent dialog text.
        TestObject privacyModal = new TestObject('privacyModal')
        privacyModal.addProperty(
            'xpath',
            ConditionType.EQUALS,
            "//*[contains(normalize-space(.), 'We value your privacy')]"
        )

        // Step 5: Define a locator for the consent dialog's Accept All control.
        TestObject acceptAllButton = new TestObject('acceptAllButton')
        acceptAllButton.addProperty(
            'xpath',
            ConditionType.EQUALS,
            "//button[normalize-space()='Accept All'] | //*[@role='button' and normalize-space()='Accept All']"
        )

        // Step 6: Detect the dialog without failing when it is absent.
        if (WebUI.verifyElementPresent(privacyModal, 5, FailureHandling.OPTIONAL)) {
            // Step 7: Accept the privacy policy and dismiss the dialog.
            WebUI.click(acceptAllButton)
        }
    }
}
