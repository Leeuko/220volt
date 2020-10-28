import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WheelOfFortune {

    public static void ClosePopup(WebDriver driver) {

        if (driver.findElements(By.xpath("//iframe[@id='fl-357854']")).size() != 0) {
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='fl-357854']")));
            driver.findElement(By.xpath("//iframe[@id='fl-357854']/html/body/div/button")).click();
            driver.switchTo().defaultContent();
        }
    }
}
