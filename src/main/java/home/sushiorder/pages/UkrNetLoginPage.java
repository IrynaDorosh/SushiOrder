package home.sushiorder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UkrNetLoginPage {
    public WebDriver driver;

    public UkrNetLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"id-1\"]")
    public  WebElement loginField;

    @FindBy(xpath = "//*[@id=\"id-2\"]")
    public  WebElement passField;



}
