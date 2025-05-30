package org.s367118.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

public class Page {
    public Page(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected WebDriver driver;

    @FindBy(xpath = "//input[@type='search']")
    private WebElement search;

    @FindBy(xpath = "//header//li//a[@href='/' and contains(@class, 'VKVideoLogo')]")
    private WebElement mainLogo;

    @FindBy(xpath = "//header//li//a[@id='top_reg_link']")
    private WebElement login;

    public void login(){
        login.click();

        WebElement phoneInput = driver.findElement(By.xpath("//input[@type='tel']"));
        phoneInput.clear();
        phoneInput.sendKeys("+79803911390");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter code: ");
        String code = scanner.nextLine();

        WebElement codeInput = driver.findElement(By.xpath("//input"));
        codeInput.sendKeys(code);

        justWait(Duration.ofSeconds(1));
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        justWait(Duration.ofSeconds(1));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void loginManually(){
        login.click();

        WebElement phoneInput = driver.findElement(By.xpath("//input[@type='tel']"));
        phoneInput.clear();
        phoneInput.sendKeys("+79803911390");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        try {
             System.in.read();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void doSearch(String searchText){
        search.click();
        search.sendKeys(searchText);
        search.sendKeys(Keys.RETURN);
    }

    public void clickMainLogo(){
        mainLogo.click();
    }

    public void goBack(){
        driver.navigate().back();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void scrollDown(int deltaY){
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
    }

    public void waitNotifierDisappear(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'notifier_baloon_body')]")));
    }

    public void justWait(Duration timeout){
        try {
            new FluentWait<WebDriver>(driver)
                    .withTimeout(timeout)
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(NoSuchElementException.class)
                    .until(driver -> false);
        }catch(TimeoutException e){
            return;
        }
    }
}
