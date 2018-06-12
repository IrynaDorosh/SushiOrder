package home.sushiorder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UkrNetMailboxPage {

    public WebDriver driver;

    public UkrNetMailboxPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"content\"]/aside/button")
    public  WebElement createLetter;

    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[2]/section[1]/div[1]/div[4]/input[2]")
    public  WebElement toField;

    @FindBy(xpath = "//*[@id=\"screens\"]/div/div[2]/section[1]/div[4]/div[2]/input")
    public  WebElement subjectField;

    @FindBy (xpath = "//*[@id='tinymce'/div]")
    public  WebElement letterField;

    @FindBy (xpath = "//*[@id=\"screens\"]/div/div[1]/div/button")
    public  WebElement sendButton;

    @FindBy(xpath = "//*[starts-with(@class,'msglist__row ') and contains(@class, 'unread')]")
    public  WebElement unreadLetterFromParticipants;


    @FindBy(className = "readmsg__body")
    public  WebElement answerField;

    @FindBy(xpath = "//*[starts-with(@class,'msglist__row ') and contains(@class, 'unread')]/td[4]")
    public  WebElement titleOfUnreadLetter;

    @FindBy(xpath = "//*[starts-with(@class, 'msglist__row ')]")
    public static WebElement anyLetter;




}
