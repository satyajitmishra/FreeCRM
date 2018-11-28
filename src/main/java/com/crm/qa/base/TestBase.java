package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.utilites.TestUtil;
import com.crm.qa.utilites.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	//public static Logger log = Logger.getLogger(TestBase.class);
	public TestBase() {
		
		prop = new Properties();
		try {
			FileInputStream fip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(fip);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void initialization() {
		
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			
			System.setProperty("webdriver.chrome.driver","D:\\NEW\\MyTestNGProject\\crm\\Resources\\chromedriver.exe" );
	        driver = new ChromeDriver();
	      // log.info("Launching chrome browser"); 
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","D:\\NEW\\MyTestNGProject\\crm\\Resources\\geckodriver.exe" );
	        driver = new FirefoxDriver();
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
	
		driver.manage().deleteAllCookies();
	
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicit_wait, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		//log.info("Navigating to url"+prop.getProperty("url"));
		
		
	}
}