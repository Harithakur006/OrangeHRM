package OrangeHRM_Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;



public class Login {
	
	WebDriver driver;
	File file;
	FileInputStream fis;
	Properties pro;
	
	
  @Test
 public void login(String un , String ps) {
	driver.findElement(By.name(pro.getProperty("username"))).sendKeys(un);
	driver.findElement(By.name(pro.getProperty("password"))).sendKeys(ps);
	driver.findElement(By.xpath("type=\"submit\"")).click();
  }
  

  @BeforeClass
  public void beforeTest() throws IOException {
	  file = new File("D:\\Users\\OrangeHRMLoginDataFile.xlsx");
	  fis = new FileInputStream(file);
	  pro = new Properties();
	  pro.load(fis);
	  
	  driver =new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
	  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");	  
  }
  
  @AfterClass
  public void AfterClass() {
	  driver.close();
  }
  
}
