package commonLibs.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class CommonDriver {
	
	private WebDriver driver;
	private int pageLoadTimeout;
	private int elementDetectionTimeout;
//	private String currentWorkingDirectory;
	
	public CommonDriver(String browserType)throws Exception {		
		
		pageLoadTimeout = 60;
		elementDetectionTimeout = 10;
	
		
		if(browserType.equalsIgnoreCase("chrome")) {
			
			System.out.println("Trying to initialize browser : ");
//			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "ChromeDriver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("initialization : " + driver);
		}else if (browserType.equalsIgnoreCase("firefox")){
			
			System.out.println("Trying to initialize browser : ");
			FirefoxOptions options = new FirefoxOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_settings.popups", 0);
			options.setCapability("prefs", prefs);
			System.setProperty("webdriver.gecko.driver", "FirefoxDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("initialization : " + driver);
		} else if (browserType.equalsIgnoreCase("edge")) {
			
			System.setProperty("webdriver.edge.driver", "EdgeDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
			System.out.println("initialization : " + driver);
		}else {
			throw new Exception("Invalid Browser Type" + browserType);
		}
	
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	public void navigateToUrl(String url)
	{
		
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
		url = url.trim();
		driver.get(url);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout) {
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}

	public void closeAllBrower() {
		//driver.quit();
	}
	
	public String getTitleOfThePage() {
		return driver.getTitle();
	}
}
