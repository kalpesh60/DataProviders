package Testng.DataProvider;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MethodDataProvider {
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
	  public void firstName_lastName(String firstName, String lastName, String MobNo) throws InterruptedException {
		  driver.findElement(By.name("firstname")).clear();
		  driver.findElement(By.name("firstname")).sendKeys(firstName);
		  System.out.println("fname Executed");
		  driver.findElement(By.name("lastname")).clear();
		  driver.findElement(By.name("lastname")).sendKeys(lastName);
		  System.out.println("lname Executed");
		  driver.findElement(By.name("reg_email__")).clear();
		  driver.findElement(By.name("reg_email__")).sendKeys(MobNo);
		  System.out.println("MobNo Executed");
		  Thread.sleep(2000);
		  }
	  
	  @Test(dataProvider = "dp",priority = 2)
	  public void mobNo_password(String MobNo, String pass) throws InterruptedException {
		  driver.findElement(By.name("reg_email__")).clear();
		  driver.findElement(By.name("reg_email__")).sendKeys(MobNo);
		  System.out.println("MobNo Executed");
		  driver.findElement(By.name("reg_passwd__")).clear();
		  driver.findElement(By.name("reg_passwd__")).sendKeys(pass);
		  System.out.println("pass Executed");
		  Thread.sleep(2000);
		  }
	
	  //If the SAME DataProvider should behave differently with different test method , use Method parameter.
	  @DataProvider
	  public Object[][] dp(Method m) {
		  if (m.getName().equalsIgnoreCase("firstName_lastName")) {
		  return new Object[][]{
			                  {"Ajay","Dhal","asdffffff"},{"Vijay","dole","567yggggg"},{"Sanjay","More","yttghhjjkk"}};}
			                  else {
		return new Object[][]{
				                  {"Ajay60@gmail.com","Abc@!123"},{"Vijay70@gmail.com","Bcd@!456"},{"Sanjay80@gmail.com","Cde@!789"}};}
	  }
}
