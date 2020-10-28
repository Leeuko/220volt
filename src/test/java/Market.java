import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Market {

    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(Market.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Driver is up");
        driver.manage().window().maximize();
    }

    @Test
    public void Tools(){

        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.get(cfg.url());
        logger.info(String.format("Page is opened %s", cfg.url()));

        //Select the town in the popup
        WebElement Welcomepopup = wait.until(ExpectedConditions.elementToBeClickable(Objects.Aldan(driver)));
        Welcomepopup.click();
        logger.info("Popup is closed");

        //wait for WheelOfFortune
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WheelOfFortune.ClosePopup(driver);

        //Open 'Электроинструменты'
        WebElement PowerTools = wait.until(ExpectedConditions.elementToBeClickable(Objects.Tools(driver)));
        PowerTools.click();
        logger.info("Power Tools section is selected");

        //wait for WheelOfFortune
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WheelOfFortune.ClosePopup(driver);

        //Open 'Перфораторы'
        WebElement Perforators = wait.until(ExpectedConditions.elementToBeClickable(Objects.Perfor(driver)));
        Perforators.click();
        logger.info("Perforators section is selected");

        //Wait for WheelOfFortune
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WheelOfFortune.ClosePopup(driver);

        //Select Products for Makita and for ЗУБР
        WebElement Makita = wait.until(ExpectedConditions.elementToBeClickable(Objects.Makita(driver)));
        Makita.click();
        logger.info("Makita checkbox is ticked");
        Objects.Zybr(driver).click();
        logger.info("ЗУБР checkbox is ticked");

        //Select Sorting by price
        WebElement Select2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("select2-selection__arrow")));
        Select2.click();
        driver.findElement(By.xpath("//span[@class='select2-results']/ul/li")).click();
        logger.info("Prise sorting from min to max is selected");

        //Wait for WheelOfFortune
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WheelOfFortune.ClosePopup(driver);

        //Select first available ЗУБР
        WebElement FirstZybr = wait.until(ExpectedConditions.elementToBeClickable(Objects.FirstAvailableZubr(driver)));
        String ZubrToolSelected = driver.findElement(By.xpath("//div[contains(@class,'new-item-list-btn')]/a[contains(@data-product-title,'ЗУБР')]")).getAttribute("data-product-title");
        FirstZybr.click();
        logger.info(String.format("ЗУБР selected is - %s", ZubrToolSelected));

        //Wait till basket is loaded
        WebElement CloseBasket = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Close']")));

        //Close Secret Sale popup
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver.findElements(By.xpath("//iframe[@id='fl-368822']")).size() != 0) {
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='fl-368822']")));
            driver.findElement(By.xpath("/html/body/section/button")).click();
            driver.switchTo().defaultContent();
        }

        //Close basket
        CloseBasket.click();

        //Select first available Makita
        WebElement FirstMakita = wait.until(ExpectedConditions.elementToBeClickable(Objects.FirstAvailableMakita(driver)));
        String MakitaToolSelected = driver.findElement(By.xpath("//div[contains(@class,'new-item-list-btn')]/a[contains(@data-product-title,'MAKITA')]")).getAttribute("data-product-title");
        FirstMakita.click();
        logger.info(String.format("Makita selected is - %s", MakitaToolSelected));

        //Close Secret Sale popup
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (driver.findElements(By.xpath("//iframe[@id='fl-368822']")).size() != 0) {
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='fl-368822']")));
            driver.findElement(By.xpath("/html/body/section/button")).click();
            driver.switchTo().defaultContent();
        }

        //Close basket
        CloseBasket.click();

        //Open Basket from main menu
        WebElement Card = wait.until(ExpectedConditions.elementToBeClickable(By.id("bCartLink")));
        Card.click();

        //Verify how many products are in the basket
        WebElement ProductFromBasket = driver.findElement(By.className("table-body"));
        List<WebElement> rows = ProductFromBasket.findElements(By.xpath("//tbody[@class='table-body']/tr"));
        if (rows.size() == 2) {
            logger.info("Only two products are shown in the basket");
        } else {
            logger.info(String.format("Only two products are shown in the basket %s", rows.size()));
        }

        //Verify product's name
            for (WebElement option1 : rows) {
                String BasketItem = option1.getAttribute("data-product-title");
                if (BasketItem.equals(ZubrToolSelected) || BasketItem.equals(MakitaToolSelected)) {
                    logger.info(String.format("Correct product is shown in the basket %s", BasketItem));
                } else
                    logger.info(String.format("Invalid product is shown in the basket %s", BasketItem));
            }
        }

    @After
    public  void setDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
