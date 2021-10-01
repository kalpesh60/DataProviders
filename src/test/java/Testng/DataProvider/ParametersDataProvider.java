package Testng.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersDataProvider {
	public String baseUrl = "https://en-gb.facebook.com/";
    String driverPath = "/Users/kalpe/eclipse-workspace/TestNGSeleniumDemo/Driver/chromedriver.exe";
    public WebDriver driver ;
    
    @Test(groups = { "group1", "group2" },priority = 0)
    public void launchBrowser() {
        System.out.println("launching chrome browser"); 
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    @Test(priority = 1)
    @Parameters({"firstName", "lastName"})
	  public void firstName_lastName(String firstName, String lastName) throws InterruptedException {
    	WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Create New')]"));
        element.click();
        Thread.sleep(3000);
		  driver.findElement(By.name("firstname")).clear();
		  driver.findElement(By.name("firstname")).sendKeys(firstName);
		  System.out.println("fname Executed");
		  driver.findElement(By.name("lastname")).clear();
		  driver.findElement(By.name("lastname")).sendKeys(lastName);
		  System.out.println("lname Executed");
}
    
    @Test(priority = 2)
    @Parameters({"MobNo", "pass"})
	  public void mobNo_password(String MobNo, String pass) throws InterruptedException {
		  driver.findElement(By.name("reg_email__")).clear();
		  driver.findElement(By.name("reg_email__")).sendKeys(MobNo);
		  System.out.println("MobNo Executed");
		  driver.findElement(By.name("reg_passwd__")).clear();
		  driver.findElement(By.name("reg_passwd__")).sendKeys(pass);
		  System.out.println("pass Executed");
		  Thread.sleep(2000);
		  }
}
