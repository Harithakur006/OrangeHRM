package OrangeHRM_Module;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Time {
	POM_login_Page login;
	WebDriver driver ;
	

	@Test(priority = 0)
	
	public void time_Timesheets() {
		
	WebElement ename  = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
	ename.sendKeys("manda akhil user");
	
	WebElement button   = driver.findElement(By.xpath("//button[@type='submit']"));
	button.click();
	
	 WebElement  timesheets = driver.findElement(By.xpath("//span[normalize-space()='Timesheets']"));
	timesheets.click();
	//My TimeSheets
	driver.findElement(By.linkText("My Timesheets")).click();
	
	}
	
	
	 @Test(priority = 1)
	public void time_Attendance() throws InterruptedException, AWTException {
		driver.findElement(By.xpath("//span[normalize-space()='Attendance']")).click();
		
		WebElement My_Records = driver.findElement(By.linkText("My Records"));
		My_Records.click();
		
		Thread.sleep(1000);
		WebElement Date = driver.findElement(By.cssSelector("[placeholder=\"yyyy-dd-mm\"]"));
		
		Date.click();
		for (int i = 0; i < 10; i++) {
		    Date.sendKeys(Keys.BACK_SPACE);
		}
		Date.sendKeys("2025-15-08");
		 WebElement view = driver.findElement(By.xpath("//button[@type='submit']"));
		 view.click();
	} 
	 
	@Test(priority=2)
	 public void Punch_Attendence() throws InterruptedException, AWTException {
		 driver.findElement(By.xpath("//span[normalize-space()='Attendance']")).click();
		 WebElement Punch = driver.findElement(By.linkText("Punch In/Out"));
		 Punch.click();
		 Thread.sleep(2000);
		 WebElement Date2 = driver.findElement(By.xpath("//input[@placeholder='yyyy-dd-mm']"));
		 Date2.click();
		 for(int i =0; i<10; i++) {
			 Date2.sendKeys(Keys.BACK_SPACE);
		 }
		 Date2.sendKeys("2025-14-09");
		 
		 WebElement time = driver.findElement(By.xpath("//input[@placeholder='hh:mm']"));
		 time.click();
		 Robot rbt = new Robot();
		 rbt.keyPress(KeyEvent.VK_CONTROL);
		 rbt.keyPress(KeyEvent.VK_A);
		 time.clear();
		 time.sendKeys("09:30 AM");
	
		 WebElement note = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/form/div[2]/div/div/div/div[2]/textarea"));
		 note.sendKeys("Good Morning");
		 
	  WebElement button= driver.findElement(By.xpath("//button[@type='submit']"));
	  button.click();
	
	}
	
	
	
	
	@AfterTest
	public void after_test() {
		driver.quit();
	}
	
	@BeforeTest
	public void before_test() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		login = new POM_login_Page(driver);
		login.login_page("Admin", "admin123");
		
		driver.findElement(By.partialLinkText("Time")).click();
		
		
	}
	
}
