package OrangeHRM_Module;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Features_PIM {
WebDriver driver ; 
POM_login_Page login;


 @Test
public void PIM_Moduel() throws InterruptedException {	
	 Thread.sleep(1000);
	WebElement PIM =  driver.findElement(By.xpath("//span[text()='PIM']"));
	PIM.click();
	
	
	List<WebElement> list_data = driver.findElements(By.xpath("//div[@class='oxd-table-body']"));
        
		for(WebElement list :list_data) {
		System.out.print(list.getText() + "|");
	}
	
     } 

@BeforeTest
public void beforetest() {
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
	login = new POM_login_Page(driver);
	login.login_page("Admin","admin123");
	
	}


@AfterTest
public void Close_allTabs() {
	driver.quit();
}
}
