package OrangeHRM_Testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v128.page.model.Screenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class OrangeHRM_modules {
WebDriver driver;


@Test
public void Admin_page() throws IOException {
	
	
   /* for getting  table headers Headers */
	WebElement header = driver.findElement(By.xpath("//div[@class=\"oxd-table-header\"]"));
    System.out.print("headers"+ header.getText());
   
   
   /* All table Data */
    List<WebElement>rows = driver.findElements(By.xpath("//div[@class=\"oxd-table\"]"));
    
   
    for(WebElement web : rows) {
	   System.out.println("All Record :" + web.getText());
	   System.out.println("All Print all rows " + rows.size());
	   
	   File screenshot_caputured = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   FileHandler.copy(screenshot_caputured, new File("D:\\Hari_selenium demo\\OranageHRM\\src\\test\\resources\\table.png"));
   }
   
}
	@BeforeTest 
	public void before_test() {
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
		//click for that Admin 
	    WebElement Admin = driver.findElement(By.partialLinkText("Admin"));
	    Admin.click();
		
	}
	
	@AfterTest
	public void aftertest() {
		driver.quit();
	}
}
