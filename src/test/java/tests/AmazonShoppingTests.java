package tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.BaseDriver;
import pages.LandingPage;
import pages.ShoppingCartPage;

import static org.junit.jupiter.api.Assertions.*;

public class AmazonShoppingTests {

  private LandingPage page;
  private ShoppingCartPage cartPage;
  private WebDriver driver;

  @BeforeEach
  public void setup() {
    String url = "https://amazon.com.au";
    driver = new BaseDriver().getChromeDriver();
    page = new LandingPage(driver, url);
    cartPage = new ShoppingCartPage(driver);
  }

  @Test
  public void shouldAddItemsToCartSuccessfully() {
    String searchTerm = "toilet paper";
    this.page.searchProducts(searchTerm);
    this.cartPage.navigateAndAddProductToCart();
    this.cartPage.viewShoppingCart();
    assertFalse(this.cartPage.getWholePrice().isEmpty());
  }

  @Test
  public void shouldSuccessfullySearchForItems() {
    String searchTerm = "toilet paper";
    this.page.searchProducts(searchTerm);
    this.cartPage.navigateAndAddProductToCart();
    this.cartPage.proceedToCheckout();
    assertTrue(driver.getCurrentUrl().startsWith("https://www.amazon.com.au/ap/signin"));
  }

  @AfterEach
  public void tearDown() {
      driver.quit();
  }
}
