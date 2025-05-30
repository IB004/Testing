package org.s367118;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.s367118.page.*;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;
public abstract class NavigateTest {
    public NavigateTest(WebDriver webDriver){
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
    public void goBackTest(){
        MainPage mainPage = new MainPage(driver);
        String main = mainPage.getCurrentUrl();

        mainPage.clickVideo(1);
        VideoPage videoPage = new VideoPage(driver);
        mainPage.justWait(Duration.ofSeconds(5));
        String firstVideo = videoPage.getCurrentUrl();

        videoPage.clickVideo(5);

        videoPage.justWait(Duration.ofSeconds(5));
        videoPage.goBack();
        videoPage.justWait(Duration.ofSeconds(5));
        String firstVideoBack = videoPage.getCurrentUrl();

        videoPage.justWait(Duration.ofSeconds(5));
        videoPage.goBack();
        videoPage.justWait(Duration.ofSeconds(5));
        String mainBack = videoPage.getCurrentUrl();

        assertEquals(main, mainBack);
        assertEquals(firstVideo, firstVideoBack);
    }

    @Test
    public void goToMainByLogoClickTest(){
        MainPage mainPage = new MainPage(driver);
        String main = mainPage.getCurrentUrl();

        mainPage.clickVideo(2);
        VideoPage videoPage = new VideoPage(driver);
        mainPage.justWait(Duration.ofSeconds(5));
        String firstVideo = videoPage.getCurrentUrl();

        mainPage.justWait(Duration.ofSeconds(3));
        videoPage.clickVideo(8);
        videoPage.justWait(Duration.ofSeconds(5));
        String secondVideo = videoPage.getCurrentUrl();

        videoPage.clickMainLogo();
        videoPage.justWait(Duration.ofSeconds(5));
        String mainBack = videoPage.getCurrentUrl();

        assertNotEquals(main, firstVideo);
        assertNotEquals(main, secondVideo);
        assertNotEquals(firstVideo, secondVideo);
        assertEquals(main, mainBack);
    }

    @Test
    public void goToVideoChannel(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickVideo(1);

        VideoPage videoPage = new VideoPage(driver);

        videoPage.justWait(Duration.ofSeconds(3));
        String channelNameOnVideoPage = videoPage.getChannelName();
        videoPage.clickChannelName();

        ChannelPage channelPage = new ChannelPage(driver);
        String channelNameOnChannelPage = channelPage.getChannelName();

        assertEquals(channelNameOnVideoPage, channelNameOnChannelPage);
    }

    @Test
    public void searchVideoTest(){
        Page page = new Page(driver);

        page.justWait(Duration.ofSeconds(2));
        String SEARCH_TEXT = "Клименков - Это прям неплохо";
        page.doSearch(SEARCH_TEXT);

        page.justWait(Duration.ofSeconds(2));
        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickVideo(1);

        page.justWait(Duration.ofSeconds(2));
        VideoPage videoPage = new VideoPage(driver);
        assertEquals(SEARCH_TEXT, videoPage.getTitle());
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
