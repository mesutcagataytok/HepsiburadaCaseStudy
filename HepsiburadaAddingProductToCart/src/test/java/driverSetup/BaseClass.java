package driverSetup;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	static Logger log = Logger.getLogger(BaseClass.class.getName());

	public static void SetUpBrowser() {
		PropertyConfigurator.configure("C:\\Users\\USER\\Desktop\\HepsiBuradaTestCaseStudy\\src\\log4j.properties");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hepsiburada.com");
	}

	public static void CloseBrowser() {
		driver.quit();
	}
}
