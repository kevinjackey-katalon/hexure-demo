import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the home page and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Select the visible desktop CTA. The page contains duplicate responsive links;
// the first matching XPath target can be hidden, so filter by rendered geometry.
def clickVisibleCta = { String path ->
    Boolean clicked = WebUI.executeJavaScript("""
        const candidates = Array.from(document.querySelectorAll('a[href*="${path}"]'));
        const target = candidates.find(element => {
          const style = window.getComputedStyle(element);
          const rect = element.getBoundingClientRect();
          return style.display !== 'none' && style.visibility !== 'hidden' &&
                 rect.width > 0 && rect.height > 0;
        });
        if (!target) return false;
        target.scrollIntoView({block: 'center', inline: 'nearest'});
        target.click();
        return true;
    """, []) as Boolean
    assert clicked : "No visible CTA was found for ${path}"
    WebUI.waitForPageLoad(30)
    assert WebUI.getUrl().contains(path) : "CTA ${path} opened an unexpected URL: " + WebUI.getUrl()
}

// Step 2: Verify IMOs & BGAs CTA navigation.
clickVisibleCta('/imos-bgas/')
WebUI.navigateToUrl('https://hexure.com/')
WebUI.waitForPageLoad(30)

// Step 3: Verify Financial Institutions CTA navigation.
clickVisibleCta('/financial-institutions/')
WebUI.navigateToUrl('https://hexure.com/')
WebUI.waitForPageLoad(30)

// Step 4: Verify Carriers CTA navigation.
clickVisibleCta('/carriers/')

// Step 5: Close the browser.
WebUI.closeBrowser()
