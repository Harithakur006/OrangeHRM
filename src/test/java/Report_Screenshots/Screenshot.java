package Report_Screenshots;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
public class Screenshot{

public static  String ScreenshotCapture(WebDriver driver ,String screenshotName) throws IOException {
	String datename  = new SimpleDateFormat("yyyyddmm").format(new Date());
	TakesScreenshot sc = (TakesScreenshot)driver;
	 
	File source = sc.getScreenshotAs(OutputType.FILE);
	String destination = System.getProperty("user.dir")+ "/OranageHRM/Reports" + screenshotName + datename + ".png";

	File finaldestination = new File(destination);
	FileUtils.copyFile(source, finaldestination);
	return destination;
	
  
}

}

