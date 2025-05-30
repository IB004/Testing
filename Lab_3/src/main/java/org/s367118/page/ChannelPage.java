package org.s367118.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChannelPage extends Page{

    public ChannelPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h2")
    WebElement channelName;

    public String getChannelName(){
        return channelName.getText();
    }

}
