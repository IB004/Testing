package org.s367118.firefox;

import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.s367118.LoggedInTest;
import org.s367118.WatchTest;

@Disabled
public class FirefoxLoggedInTest extends LoggedInTest {
    public FirefoxLoggedInTest() {
        super(new FirefoxDriver());
    }
}
