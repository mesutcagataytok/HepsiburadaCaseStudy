import driverSetup.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.LandingPage;
import pageobject.LoginPage;
import pageobject.SearchPage;

public class TestWithLogin {

    String productNameAfterClicked="";

    @Test(priority = 1)
    public void IsHepsiBuradaPageCorrect(){
        BaseClass.SetUpBrowser();
        Assert.assertEquals(LandingPage.GetPageLogoLabel().getAttribute("title"),"Hepsiburada");
    }

    @Test(priority = 2)
    public void HepsiburadaLoginValidation() throws InterruptedException {
        Assert.assertTrue(LoginPage.LoginHepsiburada());
    }

    @Test(priority = 3)
    public void IsSearchWordSame() throws InterruptedException {
        String searchWord = "iphone" ;
        SearchPage.MakeProductSearch(searchWord);
        String wordIsAfterSearch = SearchPage.GetSearchResult();
        Assert.assertEquals(searchWord, wordIsAfterSearch);
    }

    @Test(priority = 4)
    public void IsMyProductNameTrue() throws InterruptedException {
        String firstSelectedProductName = SearchPage.GetSearchResultList().get(0).getText();
        SearchPage.ClickFirstProduct();
        productNameAfterClicked = SearchPage.GetClickedItemProductName();
        Assert.assertEquals(firstSelectedProductName, productNameAfterClicked);
    }

    @Test(priority = 5)
    public void IsVerifiedToAddedProductCart(){
        SearchPage.ClickProductsAddToCart();
        SearchPage.ClickAndVerifyCart(productNameAfterClicked);
    }
}
