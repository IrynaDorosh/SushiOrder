package test;

import home.sushiorder.libs.Constants;
import home.sushiorder.libs.Helpers;
import home.sushiorder.pages.FoodmilesPages;
import home.sushiorder.pages.UkrNetLoginPage;
import home.sushiorder.pages.UkrNetMailboxPage;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LetsOrder {

    private static WebDriver driver;
    private static UkrNetLoginPage ukrNetLoginPage;
    private static UkrNetMailboxPage ukrNetMailboxPage;
    private static FoodmilesPages foodmilesPages;
    private static String orderURL;
    private static Actions actions;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ukrNetLoginPage = new UkrNetLoginPage(driver);
        ukrNetMailboxPage = new UkrNetMailboxPage(driver);
        foodmilesPages = new FoodmilesPages(driver);
        actions = new Actions(driver);

    }

    @Test
    public void sendMailToParticipants() throws InterruptedException {


            loginToMailbox();

        //send mails to participants and ask to send back links with food for order
        actions.doubleClick(ukrNetMailboxPage.createLetter)
                .perform();

        actions.sendKeys(ukrNetMailboxPage.toField, Constants.recepient1)
                .sendKeys(ukrNetMailboxPage.subjectField, Constants.subjectLetter)
                .sendKeys(Keys.TAB)
                .sendKeys(Constants.letterItself)
                .click(ukrNetMailboxPage.sendButton)
                .perform();
        WebDriverWait waitSend = new WebDriverWait(driver, 10);
        waitSend.until(ExpectedConditions.titleIs("Лиcт надіслано • sssushi@ukr.net"));
    }

    @Test
    public void createOrder() throws InterruptedException {
        loginToMailbox();

        //find unreadable letters to obtain links from them
        ArrayList<String> listOfAnswers = new ArrayList<String>();
        if (ukrNetMailboxPage.unreadLetterFromParticipants.isEnabled()) {
            int n = amountOfUnreadLetters();
            for (int i = 0; i < n; i++) {
                String a = obtainAnswersText();
                listOfAnswers.add(a);
            }
        }
        //refine URLs from the answers
        ArrayList<String> listOfURLs = new ArrayList<String>();
        for (int i = 0; i < listOfAnswers.size(); i++) {
            orderURL = Helpers.getURLfromLetter(listOfAnswers.get(i));
            listOfURLs.add(orderURL);
            System.out.println("URL is: " + listOfURLs.get(i));
        }

        //navigate to sushi site
        driver.get(Constants.sushiURL);
        driver.manage().window().maximize();
        if (FoodmilesPages.closeButton.isEnabled()) {
            FoodmilesPages.closeButton.click();
        }

        //create order (using all links)
        for (int i = 0; i < listOfURLs.size(); i++) {
            driver.get(listOfURLs.get(i));
            FoodmilesPages.orderButton.click();
            WebDriverWait wait1 = new WebDriverWait(driver, 6);
            wait1.until(ExpectedConditions.elementToBeClickable(FoodmilesPages.bucketButton));
        }
        FoodmilesPages.bucketButton.click();

        //order confirmation
        FoodmilesPages.finalOrderButton.click();
        actions.sendKeys(FoodmilesPages.obligatoryNameField, Constants.nameForOrder)
                .sendKeys(FoodmilesPages.obligatoryTelephoneField, Constants.telephoneForOrder)
                .sendKeys(FoodmilesPages.obligatoryStreetField, Constants.streetForOrder)
                .sendKeys(FoodmilesPages.obligatoryBlockField, Constants.blockForOrder)
                .contextClick(FoodmilesPages.confirmOrderButton)
//                .click(FoodmilesPages.confirmOrderButton)
                .perform();
        Thread.sleep(5000);
    }


    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    private void loginToMailbox() {
        driver.get(Constants.mailURL);
        actions.sendKeys(ukrNetLoginPage.loginField, Constants.loginValue)
                .sendKeys(ukrNetLoginPage.passField, Constants.passValue)
                .sendKeys(Keys.ENTER).perform();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(ukrNetMailboxPage.createLetter));
    }

    private int amountOfUnreadLetters() {
        List<WebElement> unreadLetters = driver.findElements(By.xpath("//*[starts-with(@class,'msglist__row ') and contains(@class, 'unread')]"));
        int a = unreadLetters.size();
        return a;
    }

    //obtain whole answer from the letter
    private String obtainAnswersText() {
        actions.click(ukrNetMailboxPage.titleOfUnreadLetter)
                .perform();
        String result = ukrNetMailboxPage.answerField.getText();
        driver.navigate().back();
        WebDriverWait waitTillReturn = new WebDriverWait(driver, 10);
        waitTillReturn.until(ExpectedConditions.visibilityOf(UkrNetMailboxPage.anyLetter));
        return result;
    }


}
