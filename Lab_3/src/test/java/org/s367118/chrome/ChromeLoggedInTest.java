package org.s367118.chrome;

import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.chrome.ChromeDriver;
import org.s367118.LoggedInTest;
import org.s367118.WatchTest;

@Disabled
public class ChromeLoggedInTest extends LoggedInTest {
    public ChromeLoggedInTest() {
        super(new ChromeDriver());
    }
}
