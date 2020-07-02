package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

  private WebDriver driver;
  private By productLinkToClick;
  private By addToCart;
  private By proceedToCheckout;
  private By announceCart;
  private By wholePrice;

  public ShoppingCartPage(WebDriver driver) {
    this.driver = driver;
    productLinkToClick = By.cssSelector(".a-size-base-plus.a-color-base.a-text-normal");
    addToCart = By.id("add-to-cart-button");
    proceedToCheckout = By.id("hlb-ptc-btn-native");
    announceCart = By.id("hlb-view-cart-announce");
    wholePrice = By.cssSelector(".a-price-whole");
  }

  public void navigateAndAddProductToCart() {
    new WebDriverWait(driver, 10L).until(ExpectedConditions.elementToBeClickable(productLinkToClick));
    driver.findElement(productLinkToClick).click();
    new WebDriverWait(driver, 10L).until(ExpectedConditions.elementToBeClickable(addToCart));
    driver.findElement(addToCart).click();
  }

  public void viewShoppingCart() {
    new WebDriverWait(driver, 10L).until(ExpectedConditions.elementToBeClickable(announceCart));
    driver.findElement(announceCart).click();
  }

  public String getWholePrice(){
    new WebDriverWait(driver, 10L).until(ExpectedConditions.visibilityOfElementLocated(wholePrice));
    return driver.findElement(wholePrice).getText();
  }

  public void proceedToCheckout() {
    new WebDriverWait(driver, 10L).until(ExpectedConditions.elementToBeClickable(proceedToCheckout));
    driver.findElement(proceedToCheckout).click();
  }
}
