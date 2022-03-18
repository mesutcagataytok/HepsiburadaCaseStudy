package pageobject;

import driverSetup.BaseClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class LandingPage extends BaseClass {
    public static WebElement element;
    public static List<WebElement> elements;
    static Logger log = Logger.getLogger(LandingPage.class.getName());

    public static By hepsiburadaLogo = By.xpath("//div[@class='containerFullWidth']/div/div/div/div/div/div/div/a");

    public static WebElement GetPageLogoLabel() {
        element = driver.findElement(hepsiburadaLogo);
        if(element==null){
            log.debug("Hepsiburada title alınamadı");
        }
        else{
            log.debug("Hepsiburada title alındı: "+ element.getAttribute("title"));
        }
        return element;
    }
}
