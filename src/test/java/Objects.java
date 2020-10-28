import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Objects {

    public static WebElement General_Element_for_All_Functions = null;

    public static WebElement Aldan(WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//label[contains(text(),'Алдан')]"));
        return General_Element_for_All_Functions;
    }
    public static WebElement Tools(WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//a[@title='Электроинструменты']"));
        return General_Element_for_All_Functions;
    }
    public static WebElement Perfor (WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//a[@title='Перфораторы']/span"));
        return General_Element_for_All_Functions;
    }
    public static WebElement Makita (WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//input[@title='MAKITA']"));
        return General_Element_for_All_Functions;
    }
    public static WebElement Zybr (WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//input[@title='ЗУБР']"));
        return General_Element_for_All_Functions;
    }
    public static WebElement Quantity (WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//div[contains(@class, 'notifyQuantity')]/a"));
        return General_Element_for_All_Functions;
    }
    public static WebElement ClosePopup (WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//section[contains(@class,'js-widget')]/button[@class='close']"));
        return General_Element_for_All_Functions;
    }
    public static WebElement FirstAvailableZubr (WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//div[contains(@class,'new-item-list-btn')]/a[contains(@data-product-title,'ЗУБР')]"));
        return General_Element_for_All_Functions;
    }
    public static WebElement FirstAvailableMakita (WebDriver driver) {
        General_Element_for_All_Functions = driver.findElement(By.xpath("//div[contains(@class,'new-item-list-btn')]/a[@data-product-vendor='MAKITA']"));
        return General_Element_for_All_Functions;
    }
}