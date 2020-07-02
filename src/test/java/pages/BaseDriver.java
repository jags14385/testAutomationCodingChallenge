package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver {

  public WebDriver getChromeDriver() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
    return new ChromeDriver();
  }
}
