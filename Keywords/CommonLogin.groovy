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
     */
    @Keyword
    def openUrlAndAcceptPrivacy(String url = 'https://hexure.com/') {
        WebUI.openBrowser('')
        WebUI.navigateToUrl(url)
        WebUI.waitForPageLoad(30, FailureHandling.STOP_ON_FAILURE)

        TestObject pageBody = new TestObject('pageBody')
        pageBody.addProperty('xpath', ConditionType.EQUALS, '//body')
        WebUI.waitForElementPresent(pageBody, 30, FailureHandling.STOP_ON_FAILURE)

        TestObject acceptAllButton = new TestObject('acceptAllButton')
        acceptAllButton.addProperty(
            'xpath',
            ConditionType.EQUALS,
            "//button[normalize-space()='Accept All' or contains(normalize-space(.), 'Accept All')] | //*[@role='button' and (normalize-space()='Accept All' or contains(normalize-space(.), 'Accept All'))]"
        )

        // CookieYes may render asynchronously or cover the page with its overlay.
        if (WebUI.waitForElementClickable(acceptAllButton, 15, FailureHandling.OPTIONAL)) {
            WebUI.click(acceptAllButton, FailureHandling.OPTIONAL)
        } else {
            // Fallback for TestCloud sessions where the overlay is present before the
            // consent control becomes Selenium-clickable.
            WebUI.executeJavaScript('''
                const buttons = Array.from(document.querySelectorAll('button, [role="button"]'));
                const button = buttons.find(element => /accept all/i.test((element.innerText || element.textContent || '').trim()));
                if (button) button.click();
            ''', [])
        }

        WebUI.waitForElementNotPresent(acceptAllButton, 15, FailureHandling.OPTIONAL)
        WebUI.executeJavaScript('''
            const overlay = document.querySelector('.cky-overlay');
            if (overlay && !document.querySelector('.cky-consent-bar')) overlay.remove();
        ''', [])
    }
}
