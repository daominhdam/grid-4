package grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class BaseTest {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	public Platform platform;

	protected WebDriver getBrowserDriver(String browserName, String osName, String ipAddress, String portNumber) {

		if (osName.contains("windows")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.MAC;
		}

		Capabilities capability = null;

		switch (browserName) {
		case "firefox":
			capability = new FirefoxOptions();
			break;
		case "chrome":
			capability = new ChromeOptions();
			break;
		case "edge":
			capability = new EdgeOptions();
			break;
		case "safari":
			capability = new SafariOptions();
			break;
		default:
			throw new RuntimeException("Browser is not valid!");
		}
		
		// Set platform
		//...

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();

		return driver;
	}

}
