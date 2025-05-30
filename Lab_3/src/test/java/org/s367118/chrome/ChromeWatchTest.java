package org.s367118.chrome;

import org.openqa.selenium.chrome.ChromeDriver;
import org.s367118.WatchTest;

public class ChromeWatchTest extends WatchTest {
    public ChromeWatchTest() {
        super(new ChromeDriver());
    }
}
