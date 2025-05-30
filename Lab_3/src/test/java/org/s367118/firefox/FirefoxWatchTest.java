package org.s367118.firefox;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.s367118.WatchTest;

public class FirefoxWatchTest extends WatchTest {
    public FirefoxWatchTest() {
        super(new FirefoxDriver());
    }
}
