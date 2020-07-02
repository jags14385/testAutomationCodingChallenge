package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
  private WebDriver driver;
  private By searchTextBox;
  private By submitSearch;
  private String url;

  public LandingPage(WebDriver driver, String url) {
    this.url = url;
    this.driver = driver;
    String searchTextBoxLocator = "twotabsearchtextbox";
    String submitSearchLocator = ".nav-input";
    searchTextBox = By.id(searchTextBoxLocator);
    submitSearch = By.cssSelector(submitSearchLocator);
  }

  public void searchProducts(String searchTerm) {
    driver.get(this.url);
    driver.findElement(searchTextBox).sendKeys(searchTerm);
    driver.findElement(submitSearch).click();
  }
}
