import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Open the Hexure home page and handle privacy consent.
CustomKeywords.'CommonLogin.openUrlAndAcceptPrivacy'('https://hexure.com/')

// Step 2: Verify the three audience sections appear in the required order.
String order = WebUI.executeJavaScript("""
    return Array.from(document.querySelectorAll('h1,h2,h3,h4,h5,h6')).map(e => (e.innerText || '').trim()).filter(Boolean).join(' | ')
""", []) as String
assert order.indexOf('IMOs') >= 0 : 'IMOs & BGAs heading is missing'
assert order.indexOf('Financial Institutions') >= 0 : 'Financial Institutions heading is missing'
assert order.indexOf('Carriers') >= 0 : 'Carriers heading is missing'
assert order.indexOf('IMOs') < order.indexOf('Financial Institutions') : 'IMOs & BGAs is not before Financial Institutions'
assert order.indexOf('Financial Institutions') < order.indexOf('Carriers') : 'Financial Institutions is not before Carriers'

// Step 3: Verify each audience section has substantial content, an image with
// non-empty alternative text, and a CTA. Walk up to the meaningful section
// container instead of using the heading's nearest div, which may be only a
// nested text wrapper.
String sectionCheck = WebUI.executeJavaScript("""
    const headings = ['IMOs', 'Financial Institutions', 'Carriers'];
    return headings.every(name => {
      const heading = Array.from(document.querySelectorAll('h1,h2,h3,h4,h5,h6'))
        .find(e => (e.innerText || '').includes(name));
      if (!heading) return false;

      let container = heading.closest('section, article');
      if (!container) {
        let current = heading.parentElement;
        for (let i = 0; current && i < 8; i++, current = current.parentElement) {
          const text = (current.innerText || '').trim();
          if (text.length > 80 && current.querySelector('img') && current.querySelector('a, button')) {
            container = current;
            break;
          }
        }
      }
      if (!container) return false;

      const hasDescription = (container.innerText || '').trim().length > 80;
      const hasAccessibleImage = Array.from(container.querySelectorAll('img'))
        .some(img => (img.getAttribute('alt') || '').trim().length > 0);
      const hasCta = !!container.querySelector('a[href], button');
      return hasDescription && hasAccessibleImage && hasCta;
    }).toString();
""", []) as String
assert sectionCheck == 'true' : 'One or more audience sections lack required content, image alt text, or CTA'

// Step 4: Verify the expected CTA destinations are present and non-placeholder.
['/imos-bgas/', '/financial-institutions/', '/carriers/'].each { path ->
    assert WebUI.executeJavaScript("return Array.from(document.querySelectorAll('a[href]')).some(a => a.href.includes('${path}'))", []) == true : "Missing CTA destination ${path}"
}

// Step 5: Close the browser.
WebUI.closeBrowser()
