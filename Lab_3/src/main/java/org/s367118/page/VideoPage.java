package org.s367118.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VideoPage extends Page{

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@data-testid='video_modal_title']")
    WebElement title;

    @FindBy(xpath = "//span[@class='_time_current']")
    WebElement currentTime;

    @FindBy(xpath = "//div[@role='button' and contains(@class, 'videoplayer_btn')][3]")
    WebElement playButton;

    @FindBy(xpath = "//span[contains(@class, 'vkuiRootComponent__host')]//a[1]")
    WebElement channelName;

    @FindBy(xpath = "//div[@data-testid='video_modal_like_button']")
    WebElement likeButton;

    @FindBy(xpath = "//div[@data-testid='video_modal_like_button']/span[2]")
    WebElement likeCount;

    @FindBy(xpath = "//div[@data-testid='content-editable-input']")
    WebElement commentInput;

    @FindBy(xpath = "//button[@data-testid='send-comment']")
    WebElement sendCommentButton;

    @FindBy(xpath = "//div[@data-testid='dropdownactionsheet-item'][1]")
    WebElement editCommentButton;

    @FindBy(xpath = "(//div[@data-testid='content-editable-input'])[2]")
    WebElement editCommentInput;

    @FindBy(xpath = "//button[@data-testid='edit-comment-save']")
    WebElement confirmEditCommentButton;

    @FindBy(xpath = "//button[@data-testid='edit-comment-cancel']")
    WebElement cancelEditCommentButton;

    @FindBy(xpath = "//div[@data-testid='dropdownactionsheet-item'][2]")
    WebElement deleteCommentButton;

    public String getTitle(){
        return title.getText();
    }

    public String getCurrentTime(){
        return currentTime.getText();
    }

    public void clickPlayButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(playButton));
        playButton.click();
    }

    public void clickVideo(int videoNumber){
        String videoXPath = String.format("(//a[@data-testid='video_card_thumb'])[%d]", videoNumber);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement video = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(videoXPath)));
        wait.until(ExpectedConditions.elementToBeClickable(video));
        video.click();
    }

    public String getChannelName(){
        return channelName.getText();
    }

    public void clickChannelName(){
        waitNotifierDisappear();
        channelName.click();
    }

    public void clickLike(){
        likeButton.click();
    }

    public String getLikesCount(){
        return likeCount.getText();
    }

    public void leaveComment(String text){
        commentInput.clear();
        commentInput.sendKeys(text);
        sendCommentButton.click();
    }

    private void hoverElementWithMouse(WebElement element){
        new Actions(driver).moveToElement(element).perform();
    }

    private String getCommentXPath(String author){
        return String.format("//div[@data-testid='comment' and .//a[@data-testid='comment-owner' and text()='%s']]", author);
    }

    private String getCommentTextXPath(String author){
        return getCommentXPath(author) + "//div[@data-testid='showmoretext-in']";
    }

    private String getOptionsCommentButtonXPath(String author){
        return getCommentXPath(author) + "//button";
    }

    public String getCommentText(String author){
        WebElement commentText = driver.findElement(By.xpath(getCommentTextXPath(author)));
        return commentText.getText();
    }

    private void clickCommentOptions(String author){
        WebElement comment = driver.findElement(By.xpath(getCommentXPath(author)));
        hoverElementWithMouse(comment);
        justWait(Duration.ofSeconds(1));

        WebElement options = driver.findElement(By.xpath(getOptionsCommentButtonXPath(author)));
        options.click();
    }

    public void editComment(String author, String text){
        clickCommentOptions(author);
        justWait(Duration.ofSeconds(1));

        editCommentButton.click();
        justWait(Duration.ofSeconds(2));

        editCommentInput.clear();
        editCommentInput.sendKeys(text);
    }

    public void clickConfirmEditCommentButton(){
        confirmEditCommentButton.click();
    }

    public void clickCancelEditCommentButton(){
        cancelEditCommentButton.click();
    }

    public void deleteComment(String author){
        clickCommentOptions(author);
        justWait(Duration.ofSeconds(1));

        deleteCommentButton.click();
    }

    public void scrollToComments(){
        scrollDown(700);
    }

}
