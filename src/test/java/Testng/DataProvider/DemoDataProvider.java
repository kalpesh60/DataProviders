package Testng.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoDataProvider {
public String baseUrl = "https://en-gb.facebook.com/";
String driverPath = "/Users/kalpe/eclipse-workspace/TestNGSeleniumDemo/Driver/chromedriver.exe";
public WebDriver driver ;

@Test(priority = 0)
public void launchBrowser() {
    System.out.println("launching chrome browser"); 
    System.setProperty("webdriver.chrome.driver", driverPath);
    driver = new ChromeDriver();
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Create New')]"));
    element.click();
}

  @Test(dataProvider = "dp",priority = 1)
  public void firstName_lastName(String firstName, String lastName) throws InterruptedException {
	  driver.findElement(By.name("firstname")).clear();
	  driver.findElement(By.name("firstname")).sendKeys(firstName);
	  System.out.println("fname Executed");
	  driver.findElement(By.name("lastname")).clear();
	  driver.findElement(By.name("lastname")).sendKeys(lastName);
	  System.out.println("lname Executed");
	  Thread.sleep(2000);
	  driver.findElement(By.name("websubmit")).click();
	  }

  	//corresponding testcase will be invoked 3 times with 2 parameters each time
  	//
    @DataProvider
	  public Object[][] dp() {
	    Object[][] data = new Object[3][2];
	      data[0][0] ="ajay"; 
	      data[0][1] = "dhal";
	      data[1][0] ="vijay"; 
	      data[1][1] = "dole";
	      data[2][0] ="sanjay"; 
	      data[2][1] = "abchh";
	      return data;
    };
  }
