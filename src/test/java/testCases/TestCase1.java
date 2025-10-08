package testCases;
import java.util.regex.Pattern;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TestCase1 {

        @Test public void testCase1()
        {
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium()
                        .launch(new BrowserType.LaunchOptions().setHeadless(true));
                Page page = browser.newPage();
                page.navigate("https://playwright.dev");
                // Expect a title "to contain" a substring.
                assertThat(page).hasTitle(Pattern.compile("Playwright"));

                System.out.println("Title is: "+page.title());
                // create a locator
                Locator getStarted = page
                        .getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
                        .setName("Get Started"));
                // Expect an attribute "to be strictly equal" to the value.
                assertThat(getStarted).hasAttribute("href", "/docs/intro");
                // Click the get started link.
                getStarted.click();
                System.out.println("Title is: "+page.title());
                // Expects page to have a heading with the name of Installation.
                assertThat(page.getByRole(AriaRole.HEADING,
                        new Page.GetByRoleOptions().setName("Installation"))).isVisible();
            }
        }


