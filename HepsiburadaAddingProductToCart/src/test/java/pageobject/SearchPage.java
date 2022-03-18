package pageobject;
import driverSetup.BaseClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.util.Set;

public class SearchPage extends BaseClass {
    public static WebElement element;
    public static List<WebElement> elements;
    static Logger log = Logger.getLogger(SearchPage.class.getName());

    public static By searchBoxText = By.xpath("//div[@id='SearchBoxOld']/div/div[1]/div[1]");
    public static By searchButton = By.xpath("//div[contains(text(),'ARA')]");
    public static By productList = By.xpath("//li[@class='productListContent-item']/div/a/div/h3");
    public static By productSearchText = By.xpath("//h1[contains(text(),'iphone')]");
    public static By selectedProductNameText = By.xpath("//h1[@id='product-name']");
    public static By addToCartButton = By.xpath("//div[@class='addToCart']");
    public static By shoppingCartButton = By.id("shoppingCart");
    public static By closePopUpButton = By.xpath("//*[@class='close']");
    public static By basketItemCountText = By.id("basket-item-count");
    public static By goToBasketButton = By.xpath("//button[contains(text(),'Sepete git')]");

    //Iphone kelimesi aranır.
    public static void MakeProductSearch(String searchKeyWord) throws InterruptedException {
        element = driver.findElement(searchBoxText);
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxText));
        driver.findElement((searchBoxText)).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        actions.sendKeys(searchKeyWord).build().perform();
        //search box ara butonu
        element = driver.findElement(searchButton);
        element.click();
    }

    //Iphone kelimesi aratıldığında kontrol edilir.
    public static List<WebElement> GetSearchResultList() {
        elements = driver.findElements(productList);
        if(elements.size()>=1){
            log.debug("Aranan ürünle ilgili sonuç bulundu.");
        }
        else{
            log.debug("Aranan ürünle ilgili sonuç bulunamadı.");
        }
        return elements;
    }

    public static String GetSearchResult() {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(productSearchText));
        //arama yaptıktan sonra yapılan aramanın yazdığı text element
        element = driver.findElement(productSearchText);
        String iphone = element.getText();
        if(iphone.equals("iphone")){
            log.debug("iphone araması yapıldığı doğrulanıyor");
        }
        else{
            log.debug("iphone araması yapılamadı");
        }
        return iphone;
    }

    public static void SwitchTab(){
        String currentHandle= driver.getWindowHandle();
        Set<String> handles=driver.getWindowHandles();
        for(String actual: handles) {
            System.out.println(actual);
            if(!actual.equalsIgnoreCase(currentHandle)) {
                //Switch to the opened tab
                driver.switchTo().window(actual);
            }
        }
    }

    public static String GetClickedItemProductName() {
        // Yeni taba geçer
        SwitchTab();
        //search sonucunda listeden ilk ürüne tıkladıktan sonra açılan pencerede ürünün ismini alan element
        element = driver.findElement(selectedProductNameText);
        return element.getText();
    }

    public static void ClickFirstProduct() {
        elements = driver.findElements(By.xpath("//li[@class='productListContent-item']/div/a/div/h3"));
       try{
           elements.get(0).click();

       }catch (Exception e){
           JavascriptExecutor executor = (JavascriptExecutor) driver;
           executor.executeScript("arguments[0].click();", elements.get(0));

       }
    }

    public static void ClickProductsAddToCart() {
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        elements = driver.findElements(addToCartButton);
        elements.get(0).click();
        ClickClosePopUp();
    }

    public static void ClickProductsAddToCartWithoutLogin() {
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        elements = driver.findElements(addToCartButton);
        elements.get(0).click();
        ClickClosePopUp();
        elements.get(1).click();
        ClickClosePopUp();
    }

    public static void ClickClosePopUp() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(closePopUpButton));
    }

    public static void ClickAndVerifyCart (String productNameAfterClicked) {
        System.out.println(productNameAfterClicked);
        wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartButton));
        driver.findElement(shoppingCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(basketItemCountText));
        Assert.assertEquals(driver.findElement(basketItemCountText).getText(), "1");
        List<WebElement> productsInCart =driver.findElements
                (By.xpath("//*[contains(text(),'"+productNameAfterClicked+"')]"));
        System.out.println(productsInCart.get(0).getText());
        Assert.assertEquals(productsInCart.size(),1);
        Assert.assertEquals(productsInCart.get(0).getText(),productNameAfterClicked);
    }

    public static void ClickAndVerifyCartWithoutLogin (String productNameAfterClicked) {
        System.out.println(productNameAfterClicked);
        wait = new WebDriverWait(driver,10);
        driver.findElement(shoppingCartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(basketItemCountText));
        Assert.assertEquals(driver.findElement(basketItemCountText).getText(), "1");
        List<WebElement> productsInCart =driver.findElements
                (By.xpath("//*[contains(text(),'"+productNameAfterClicked+"')]"));
        System.out.println(productsInCart.get(0).getText());
        System.out.println(productsInCart.get(1).getText());
        Assert.assertEquals(productsInCart.size(),2);
        Assert.assertEquals(productsInCart.get(0).getText(),productNameAfterClicked);
        Assert.assertEquals(productsInCart.get(1).getText(),productNameAfterClicked);
    }
}
