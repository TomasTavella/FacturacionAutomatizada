

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	
	private WebDriver driver;
	
	
	
	public Base(WebDriver driver) {
		
		this.driver = driver;
	}

	
	public WebDriver chromeDriverConnection (String location) {
		
		System.setProperty("webdriver.chrome.driver", location);
		driver = new ChromeDriver();
		return driver;
	}
	
	public WebElement findElement(By locator) {
		
		return driver.findElement(locator);
		
	}
	
	public ArrayList<String> getWindowHandles(){
		
		ArrayList<String> a = new ArrayList<String> (driver.getWindowHandles());
		
		return a;
	}
	
	public void switchToWindow(ArrayList<String> a, int i) {
		
		driver.switchTo().window(a.get(i));
	}
	
	public void type(String inputText, By locator) {
		
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(inputText);

	}
	
	
	public void click(By locator) {
		
		driver.findElement(locator).click();
	
	}
	
	public void click(WebElement e) {
		
		
		e.click();
	}

	
	public boolean isDisplayed(By locator) {
		
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	
	public void visit(String url) {
		driver.get(url);
	}
	
	public String getText(WebElement element) {
		return element.getText();
	}
	
	public void AcceptAlert() {
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}

	public void submit(By locator) {
		driver.findElement(locator).submit();
	}
	
	public WebDriverWait newWait() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); 
		return wait;
	}
	
	public String dropDownList(By locator, String s) {
		
		Select selectList = new Select (findElement(locator));
		selectList.selectByVisibleText(s);
		return getText(selectList.getFirstSelectedOption());
	}
	
	
	
}
