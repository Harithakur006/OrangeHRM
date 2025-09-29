package OrangeHRM_Module;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Leave_module {
WebDriver driver;
POM_login_Page login;

@Test (priority = 2)
public void all_data() {
	
}

@Test(priority = 1)
public void Leave_page_module() throws AWTException, InterruptedException {
	//from date 
	WebElement from = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input"));
	from.clear();
	from.sendKeys("2025-08-02");

	// tab key use for selecting the 2nd date section beacuse selector unable to locate 
 Thread.sleep(1000);
	Robot rbt = new Robot();
    rbt.keyPress(KeyEvent.VK_TAB);
	
	//to date 
    Thread.sleep(2000);
	WebElement to = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input"));
	to.sendKeys("2025-8-10");
	
	//selecting the Leave status
	WebElement dropdown = driver.findElement(By.cssSelector("[class=\"oxd-select-text--after\"]"));
	dropdown.click();
	dropdown =  driver.findElement(By.xpath("//span[text()='Pending Approval']"));
	dropdown.click();
	
	// LEAVE TYPE
	WebElement Leave = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]/i"));
	Leave.click();
	driver.findElement(By.xpath("//span[text()='CAN - Personal']")).click();
	
	
	//EMPLOYEE NAME
	driver.findElement(By.cssSelector("[placeholder=\"Type for hints...\"]")).sendKeys("Mehmet Eren Ekiz");
	
	//SUB UNIT
	WebElement subunit = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[2]/div/div[2]/div/div/div[1]"));
	subunit.click();
	driver.findElement(By.xpath("//span[text()='Quality Assurance']")).click();
	
	//Radio button (past employee)
	WebElement PastEmployee = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[3]/div/label/span"));
	PastEmployee.isSelected();
	PastEmployee.click();

	// Submit button 
WebElement button = 	driver.findElement(By.xpath("//button[@type='submit']"));button.click();
System.out.println("Submit button got clicked" + button.getText());
	

	
 }
@Test
public void leave_Print() {
	
}


// close all the page after completing test
@AfterTest
public void after_test() {
	driver.close();
}



// before test launching chrome and and login the website
@BeforeTest
public void before_test() {
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
	login = new POM_login_Page(driver);
	login.login_page("Admin","admin123");
	driver.findElement(By.partialLinkText("Leave")).click();
   }
 }