package OrangeHRM_Testcases;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Orange_HRM_login_DATA {
String fPath = "d:\\Users\\OrangeHRMLoginDataFile.xlsx";
File file;
FileOutputStream fos;
XSSFWorkbook wb;
XSSFSheet sheet;
XSSFRow row;
XSSFCell cell;
int index = 0;
WebDriver driver;

@Test (dataProvider = "getloginData")
public void writeData(String un , String ps) {
	row = sheet.createRow(index);
	cell = row.createCell(0);
	cell.setCellValue(un);
	
	cell = row.createCell(1);
	cell.setCellValue(ps);
	
	cell =  row.createCell(2);
	cell.setCellValue("not run");
	
	index ++;
	
	
}
@DataProvider 
public Object [][] getloginData(){
	return new Object[][] {
		new Object [] {"anup" , "anup123"},
		new Object [] {"hari" , "hari123"},
		new Object [] {"gopi" , "gopi123"},
		new Object [] {"shiv" , "shiv123"},
		new Object [] {"rahul" , "rahul123"},
		new Object [] {"ram" , "ram123"},
		new Object [] {"raju" , "raju123"},
		new Object [] {"varma" , "varma123"},
		new Object [] {"sharma" , "sharma123"},
		new Object [] {"jayesh" , "jayesh123"},
		new Object [] {"hero" , "hero123"},
		new Object [] {"Admin" , "admin123"}
	};
	
}
@BeforeTest
public void beforeTest() throws IOException  {
	file = new File(fPath);
	fos = new FileOutputStream(file);
	wb = new XSSFWorkbook();
	sheet = wb.createSheet("logindata");
	
	
	sheet.createRow(index).createCell(0).setCellValue("user name");
	sheet.getRow(index).createCell(1).setCellValue("password");
	sheet.getRow(index).createCell(2).setCellValue("result");
index ++;

}

@AfterTest
public void aftertest() throws IOException {
	
	wb.write(fos);
	wb.close();
	fos.close();
  }
}

