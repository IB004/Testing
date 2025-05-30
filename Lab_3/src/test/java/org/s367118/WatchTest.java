package org.s367118;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.s367118.page.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class WatchTest {
    public WatchTest(WebDriver webDriver){
        this.driver = webDriver;
    }
    WebDriver driver;

    private final static String MAIN_URL = "https://vkvideo.ru";

    @BeforeEach
    public void setUp(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(MAIN_URL);
    }

    @Test
    public void playOnOpenTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickVideo(4);

        VideoPage videoPage = new VideoPage(driver);
        String initialTime = videoPage.getCurrentTime();

        videoPage.justWait(Duration.ofSeconds(5));
        String afterWaitTime = videoPage.getCurrentTime();

        assertNotEquals(initialTime, afterWaitTime);
    }

    @Test
    public void pauseTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickVideo(4);

        VideoPage videoPage = new VideoPage(driver);
        videoPage.justWait(Duration.ofSeconds(8));

        videoPage.clickPlayButton();
        videoPage.justWait(Duration.ofSeconds(1));
        String pauseStartTime = videoPage.getCurrentTime();
        videoPage.justWait(Duration.ofSeconds(4));
        String pauseEndTime = videoPage.getCurrentTime();

        assertEquals(pauseStartTime, pauseEndTime);
    }

    @Test
    public void playPauseTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickVideo(4);

        VideoPage videoPage = new VideoPage(driver);
        videoPage.justWait(Duration.ofSeconds(4));
        String initialTime = videoPage.getCurrentTime();

        videoPage.justWait(Duration.ofSeconds(4));
        String afterWaitTime = videoPage.getCurrentTime();

        videoPage.clickPlayButton();
        videoPage.justWait(Duration.ofSeconds(1));
        String pauseStartTime = videoPage.getCurrentTime();
        videoPage.justWait(Duration.ofSeconds(4));
        String pauseEndTime = videoPage.getCurrentTime();

        videoPage.clickPlayButton();
        videoPage.justWait(Duration.ofSeconds(4));
        String afterPauseResetTime = videoPage.getCurrentTime();

        assertNotEquals(initialTime, afterWaitTime);
        assertEquals(pauseStartTime, pauseEndTime);
        assertNotEquals(pauseEndTime, afterPauseResetTime);
        assertNotEquals(initialTime, afterPauseResetTime);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
