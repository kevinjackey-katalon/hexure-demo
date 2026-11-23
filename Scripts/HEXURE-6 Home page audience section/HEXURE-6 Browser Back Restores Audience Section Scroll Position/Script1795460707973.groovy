import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the home page and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Locate the IMOs & BGAs audience section and bring its visible CTA
// into view. The page can use responsive duplicate anchors and, in some
// Chrome viewport sizes, the section may already be within the viewport.
Boolean prepared = WebUI.executeJavaScript("""
    const heading = Array.from(document.querySelectorAll('h1,h2,h3,h4,h5,h6'))
      .find(e => (e.innerText || '').includes('IMOs'));
    const target = Array.from(document.querySelectorAll('a[href*=\"/imos-bgas/\"]')).find(element => {
      const style = window.getComputedStyle(element);
      const rect = element.getBoundingClientRect();
      return style.display !== 'none' && style.visibility !== 'hidden' &&
             rect.width > 0 && rect.height > 0;
    });
    if (!heading || !target) return false;
    target.scrollIntoView({block: 'center', inline: 'nearest', behavior: 'auto'});
    return true;
""", []) as Boolean
assert prepared : 'IMOs & BGAs audience heading or visible CTA was not found'

// Step 3: Open the visible IMOs & BGAs destination without relying on a
// hidden responsive duplicate selected by the repository XPath.
Boolean clicked = WebUI.executeJavaScript("""
    const target = Array.from(document.querySelectorAll('a[href*=\"/imos-bgas/\"]')).find(element => {
      const style = window.getComputedStyle(element);
      const rect = element.getBoundingClientRect();
      return style.display !== 'none' && style.visibility !== 'hidden' &&
             rect.width > 0 && rect.height > 0;
    });
    if (!target) return false;
    target.click();
    return true;
""", []) as Boolean
assert clicked : 'No visible IMOs & BGAs CTA was found'
WebUI.waitForPageLoad(30)
assert WebUI.getUrl().contains('/imos-bgas/') : 'The IMOs & BGAs destination did not open'

// Step 4: Use browser Back and verify that the home page returns with the
// audience section restored into the viewport. Compare semantic visibility
// rather than window.scrollY alone because the site may restore a nested
// scroll container or begin at zero when the section fits the viewport.
WebUI.back()
WebUI.waitForPageLoad(30)
assert WebUI.getUrl().replaceAll('/+$', '').equals('https://hexure.com') : 'Browser Back did not return to the home page: ' + WebUI.getUrl()
Map restored = WebUI.executeJavaScript("""
    const heading = Array.from(document.querySelectorAll('h1,h2,h3,h4,h5,h6'))
      .find(e => (e.innerText || '').includes('IMOs'));
    if (!heading) return {found: false};
    const rect = heading.getBoundingClientRect();
    const visible = rect.bottom > 0 && rect.top < window.innerHeight;
    return {found: true, visible: visible, top: Math.round(rect.top), scrollY: Math.round(window.scrollY)};
""", []) as Map
assert restored.found == true : 'IMOs & BGAs heading was not found after browser Back'
assert restored.visible == true : "Audience section was not restored into the viewport. Heading top: ${restored.top}, scrollY: ${restored.scrollY}"

// Step 5: Close the browser.
WebUI.closeBrowser()