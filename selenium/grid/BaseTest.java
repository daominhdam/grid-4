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
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class BaseTest {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	public Platform platform;

	protected WebDriver getBrowserDriver(String browserName, String url, String osName, String ipAddress, String portNumber) {
		Capabilities capability = null;

		if (osName.toLowerCase().contains("windows")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.MAC;
		}

		switch (browserName) {
		case "firefox":
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = fOptions;
			break;
		case "chrome":
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = cOptions;
			break;
		case "edge":
			EdgeOptions eOptions = new EdgeOptions();
			eOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = eOptions;
			break;
		case "safari":
			SafariOptions sOptions = new SafariOptions();
			sOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = sOptions;
			break;
		default:
			throw new RuntimeException("Browser is not valid!");
		}

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

}
