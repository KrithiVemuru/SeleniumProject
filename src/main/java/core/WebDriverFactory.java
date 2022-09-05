package core;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

	public static WebDriver initializeChromeDriver(String environment) {
		WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator+"chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.navigate().to(environment);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
		return driver;
	}
	
	public static WebDriver initializeFireFoxDriver(String environment) {
		WebDriver driver;
		FirefoxOptions options = new FirefoxOptions();
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+File.separator+"geckodriver.exe");
		driver = new FirefoxDriver(options);
		driver.manage().deleteAllCookies();
		driver.navigate().to(environment);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
		return driver;
	}
}
