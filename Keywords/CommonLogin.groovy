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
     * Opens the requested URL and dismisses the privacy-consent dialog when it is displayed.
     * The consent button is checked directly because CookieYes can render its overlay
     * before the dialog text becomes available in the DOM.
     */
    @Keyword
    def openUrlAndAcceptPrivacy(String url = 'https://hexure.com/') {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(url)
        WebUI.waitForPageLoad(30)

        TestObject acceptAllButton = new TestObject('acceptAllButton')
        acceptAllButton.addProperty(
            'xpath',
            ConditionType.EQUALS,
            "//button[normalize-space()='Accept All' or contains(normalize-space(.), 'Accept All')] | //*[@role='button' and (normalize-space()='Accept All' or contains(normalize-space(.), 'Accept All'))]"
        )

        // CookieYes may display the blocking overlay while its dialog text is still rendering.
        // Wait for the actual consent control and click it when available; otherwise continue.
        if (WebUI.waitForElementClickable(acceptAllButton, 10)) {
            WebUI.click(acceptAllButton)
            WebUI.waitForElementNotPresent(acceptAllButton, 10, FailureHandling.OPTIONAL)
        }
    }
}
