package OrangeHRM_Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginWithdata {

	String fPath = "D:\\Users\\OrangeHRMLoginDataFile.xlsx";
	File file;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	XSSFCellStyle style;
	XSSFFont font;
	
	FileOutputStream fos;
	int index = 1;
	
	
	WebDriver driver;
	
	@Test (dataProvider = "getLoginData")
public void OrangeHRM_login(String un, String ps) {
		driver.findElement(By.xpath("//input[@placeholder=\"Username\"]")).sendKeys(un);
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(ps);
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		
	}
@AfterMethod
public void aftermethod() {
 row = sheet.getRow(index);
 cell = row.getCell(2);
 
 
 if(driver.getCurrentUrl().contains("dashboard")) {
	 System.out.println("login Pass");
	 
	 driver.findElement(By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-userdropdown-icon\"]")).click();
	 driver.findElement(By.partialLinkText("Logout")).click();

	 //for report purpose
	 
 }
 else {
	 System.out.println("login fail" );
	
 }
 index++;
}

@DataProvider
public Object[][] getLoginData(){
	int totalRow = sheet.getPhysicalNumberOfRows();
	
	String [][] logindata = new String[totalRow - 1][2];
	
	for(int i = 0; i<totalRow - 1; i++) {
		
		for(int j = 0; j<2; j++) {
			
		logindata[i][j]= sheet.getRow(i+1).getCell(j).getStringCellValue();	
		}
	}
return logindata;
}

@BeforeTest
public void beforeTest() throws IOException {
	file = new File(fPath);
	fis = new FileInputStream(file);
	wb = new XSSFWorkbook(fis);
	
	sheet = wb.getSheetAt(0);
	fos = new FileOutputStream(file);
	
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
}
@AfterTest
public void aftertest() throws IOException {
	wb.write(fos);
	wb.close();
	fis.close();
	driver.close();
	
}
}
