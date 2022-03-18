package pageobject;

import driverSetup.BaseClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPage extends BaseClass {

    public static WebElement element;
    public static List<WebElement> elements;
    static Logger log = Logger.getLogger(LandingPage.class.getName());

    public static By myAccountButton = By.xpath("//div[@id='myAccount']");
    public static By accountLoginButton = By.xpath("//a[@id='login']");
    public static By userNameText = By.id("txtUserName");
    public static By loginEmailButton = By.xpath("//button[@id='btnLogin']");
    public static By passwordText = By.xpath("//input[@id='txtPassword']");
    public static By loginPasswordButton = By.xpath("//button[@id='btnEmailSelect']");
    public static By myAccountText = By.xpath("//span[contains(text(),'Mesut Çağatay Tok')]");

    public static Boolean LoginHepsiburada() throws InterruptedException {
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountButton));
        driver.findElement((myAccountButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountLoginButton));
        driver.findElement(accountLoginButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameText));
        driver.findElement(userNameText).click();
        driver.findElement(userNameText).sendKeys("cagataytok@windowslive.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailButton));
        driver.findElement(loginEmailButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordText));
        driver.findElement(passwordText).sendKeys("Sac02191.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPasswordButton));
        driver.findElement(loginPasswordButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(myAccountText));
        // login olduktan sonra kullanıcının ismini alan element
        element = driver.findElement(By.xpath("//span[contains(text(),'Mesut Çağatay Tok')]"));
        if (element.getText().equals("Mesut Çağatay Tok")) {
            log.debug("Hesaba başarılı giriş yapıldı.");
            return true;
        }else{
            log.debug("Hesaba başarılı giriş yapılamadı.");
        }
        return false;
    }
}
