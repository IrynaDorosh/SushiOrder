package home.sushiorder.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FoodmilesPages {
    WebDriver driver;

    public FoodmilesPages(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="white-saas-generator-btn-cancel")
    public static WebElement closeButton;

    @FindBy(name = "add-to-cart")
    public static WebElement orderButton;

    @FindBy(className = "flycartcount")
    public static WebElement orderNumberBusketPicktogram;

    @FindBy(xpath = "//*[@class='flycartblock']")
    public static WebElement orderBusketPicktogram;

    @FindBy(xpath = "//*[@class='checkout-button button alt wc-forward']")
    public static WebElement finalOrderButton;

    @FindBy(xpath = "//a[@href='https://foodmiles.com.ua/checkout/' and  @class='checkout-button button alt wc-forward']")
    public static WebElement createOrder;

    @FindBy(xpath = "//input[@id='billing_first_name']")
    public static WebElement  obligatoryNameField;

    @FindBy(xpath = "//input[@id='billing_phone']")
    public static WebElement  obligatoryTelephoneField;

    @FindBy(xpath = "//input[@id='billing_Street']")
    public static WebElement  obligatoryStreetField;

    @FindBy(xpath = "//input[@id='billing_building']")
    public static WebElement  obligatoryBlockField;

    @FindBy(xpath = "//input[@id='place_order']")
    public static WebElement  confirmOrderButton;

    @FindBy(xpath = "//*[text() = 'Корзина']//parent::a")
    public static WebElement  bucketButton;




}
