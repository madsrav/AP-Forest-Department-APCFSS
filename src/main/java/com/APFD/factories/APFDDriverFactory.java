package com.APFD.factories;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import com.APFD.enums.ConfigurationProperties;
import com.APFD.utils.PropertyFileUtilities;

public final class APFDDriverFactory {

public static WebDriver sdriver=null;

		private APFDDriverFactory() {
		}
		//@Parameters(value={"browser","version","platform"})
		public static WebDriver getDriver(/*String browser,String version,String platform*/) throws MalformedURLException {
			WebDriver driver = null;
			String runmode = PropertyFileUtilities.readDataFromPropertyFile(ConfigurationProperties.RUNMODE);
//			String username="mammula.sravan42384";
//			String accesskey="0J0bDfS9XiINJAXT7TwO1oUHaZhdMeTuIUPZdxPVXKwAV1hlUK";
//		    String gridURL = "@hub.lambdatest.com/wd/hub";
			
				if (runmode.equalsIgnoreCase("remote")) {
//					DesiredCapabilities capabilities = new DesiredCapabilities();
//					capabilities.setCapability("browserName", browser);
//			        capabilities.setCapability("version", version);
//			        capabilities.setCapability("platform", platform);
//			        capabilities.setCapability("build", "LambdaTestTestNGAssesment_1.1");
//			        capabilities.setCapability("name", "LambdaTest_TestNG_Assesment");
//			        capabilities.setCapability("network", true);
//			        capabilities.setCapability("video", true);
//			        capabilities.setCapability("visual", true);
//			        capabilities.setCapability("devicelog", true);
//			        capabilities.setCapability("console", true);
//				driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
				} else 
			   {
					driver = new ChromeDriver();
				}

			sdriver=driver;	
			return driver;
		}
}
