package org.s367118;

import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.s367118.page.Page;
import org.s367118.page.VideoPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class LoggedInTest {
    public LoggedInTest(WebDriver webDriver){
        this.driver = webDriver;
    }
    WebDriver driver;

    private final static String MAIN_URL = "https://vkvideo.ru";
    private final static String COMMENT_AUTHOR = "Тестостерон Корнеев";

    @BeforeAll
    public void setUp(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(MAIN_URL);
        Page page = new Page(driver);
        page.loginManually();
    }

    @BeforeEach
    public void openVideo(){
        driver.get("https://vkvideo.ru/video-227233132_456239034");
    }

    @Test
    @Order(1)
    public void likeUnlikeTest(){
        VideoPage videoPage = new VideoPage(driver);

        videoPage.justWait(Duration.ofSeconds(1));
        String initialCount = videoPage.getLikesCount();

        videoPage.clickLike();

        videoPage.justWait(Duration.ofSeconds(1));
        String afterLikeCount = videoPage.getLikesCount();

        assertNotEquals(initialCount, afterLikeCount);

        videoPage.clickLike();

        videoPage.justWait(Duration.ofSeconds(1));
        String afterUnlikeCount = videoPage.getLikesCount();

        assertNotEquals(initialCount, afterUnlikeCount);
    }

    @Test
    @Order(2)
    public void commentTest() {
        String text = "Привет с лабы по ТПО";
        VideoPage videoPage = new VideoPage(driver);
        videoPage.justWait(Duration.ofSeconds(2));

        videoPage.scrollToComments();
        videoPage.justWait(Duration.ofSeconds(2));

        videoPage.leaveComment(text);

        videoPage.justWait(Duration.ofSeconds(2));
        assertEquals(text, videoPage.getCommentText(COMMENT_AUTHOR));
    }

    @Test
    @Order(3)
    public void editCommentTest() {
        String newText = "Измененный текст";
        VideoPage videoPage = new VideoPage(driver);
        videoPage.justWait(Duration.ofSeconds(2));

        videoPage.scrollToComments();
        videoPage.justWait(Duration.ofSeconds(2));

        videoPage.editComment(COMMENT_AUTHOR, newText);
        videoPage.clickConfirmEditCommentButton();

        videoPage.justWait(Duration.ofSeconds(2));
        assertEquals(newText, videoPage.getCommentText(COMMENT_AUTHOR));
    }

    @Test
    @Order(4)
    public void editCommentCancelTest() {
        String newText = "Новый текст";
        VideoPage videoPage = new VideoPage(driver);
        videoPage.justWait(Duration.ofSeconds(2));

        videoPage.scrollToComments();
        videoPage.justWait(Duration.ofSeconds(2));

        String initialText = videoPage.getCommentText(COMMENT_AUTHOR);

        videoPage.editComment(COMMENT_AUTHOR, newText);
        videoPage.clickCancelEditCommentButton();

        videoPage.justWait(Duration.ofSeconds(2));

        String finalText = videoPage.getCommentText(COMMENT_AUTHOR);
        assertNotEquals(newText, finalText);
        assertEquals(newText, initialText);
    }

    @Test
    @Order(5)
    public void deleteCommentTest() {
        VideoPage videoPage = new VideoPage(driver);
        videoPage.justWait(Duration.ofSeconds(2));

        videoPage.scrollToComments();
        videoPage.justWait(Duration.ofSeconds(2));

        videoPage.deleteComment(COMMENT_AUTHOR);

        assertThrows(NoSuchElementException.class, () -> videoPage.getCommentText(COMMENT_AUTHOR));
    }



    @AfterAll
    public void tearDown(){
        driver.quit();
    }
}
