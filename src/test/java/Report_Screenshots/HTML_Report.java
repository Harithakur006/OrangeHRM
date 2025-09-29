package Report_Screenshots;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HTML_Report implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		//sparkReporter = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date()));
	sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/MyOrange.html");
	
	sparkReporter.config().setDocumentTitle("OrangeHRM Test");
	sparkReporter.config().setReportName("Functional test");
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	
	extent.setSystemInfo("Laptopname","Hari");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("Tester", "Harishankar");
	extent.setSystemInfo("os","windows 11");
	extent.setSystemInfo("Browser name", "google Chrome");
	
	}
	
public void onTestSuccess(ITestResult result) {
	test= extent.createTest(result.getName());
	test.log(Status.PASS,"Test case PASSED is :" + result.getName());
}

public void onTestFailure(ITestResult result) {
	
	test = extent.createTest(result.getName());	
	test.log(Status.FAIL,"test case PASSED is :" + result.getName());
	test.log(Status.FAIL,"TEST case FAILED is :" + result.getThrowable());
	
	  try {
          // call your Screenshot utility
          String screenshotPath = Screenshot.ScreenshotCapture(driver, result.getName());
          test.addScreenCaptureFromPath(screenshotPath); // attach screenshot
      } catch (Exception e) {
          e.printStackTrace();
      }
}
public void onTestSkipped(ITestResult result) {
	test = extent.createTest(result.getName());
	test.log(Status.SKIP,"Test case SKIPPED is :" + result.getName());
}

public void onFinish(ITestContext context) {
	extent.flush();
 }
}

