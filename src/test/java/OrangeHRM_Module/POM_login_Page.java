package OrangeHRM_Module;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POM_login_Page {
 WebDriver driver ;


public POM_login_Page(WebDriver driver) {
	this.driver = driver;
}
 By username = By.name("username");
 By password = By.name("password");
 By loginBtn = By.xpath("//button[@type=\"submit\"]");
 
 public void enterusername(String user) {
	 driver.findElement(username).sendKeys(user);
 }
 public void enterpassword(String pass) {
	 driver.findElement(password).sendKeys(pass);
 }

 public void click_on_loginBTN() {
	 driver.findElement(loginBtn).click();
 }
public void login_page(String user, String pass) {
	enterusername(user);
	enterpassword(pass);
	click_on_loginBTN();
}
 
}