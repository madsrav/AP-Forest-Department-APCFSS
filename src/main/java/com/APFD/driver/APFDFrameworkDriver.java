package com.APFD.driver;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Objects;

import org.testng.annotations.Parameters;

import com.APFD.enums.ConfigurationProperties;
import com.APFD.factories.APFDDriverFactory;
import com.APFD.utils.PropertyFileUtilities;



public class APFDFrameworkDriver {
		private APFDFrameworkDriver() {
		}

	//	@Parameters(value="browser"/*,"version","platform"}*/)
		public static void initDriver(/*String browser/*,String version,String platform*/) {
			if (Objects.isNull(APFDFrameworkDriverManager.getDriver()))// driver == null
			{
				try {
					APFDFrameworkDriverManager.setDriver(APFDDriverFactory.getDriver(/*browser/*, version, platform*/));
				} catch (MalformedURLException e) {
					throw new RuntimeException("Please check capabilites of browser");
				}
				APFDFrameworkDriverManager.getDriver().get(PropertyFileUtilities.readDataFromPropertyFile(ConfigurationProperties.URL));
				APFDFrameworkDriverManager.getDriver().manage().window().maximize();
				APFDFrameworkDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			}
		}

		/**
		 * Terminates the browser instance. Sets the thread local to default value, i.e
		 * null.
		 * 
		 * @author Sravan Kumar M
		 */
		public static void quitDriver() {
			if (Objects.nonNull(APFDFrameworkDriverManager.getDriver()))// driver!=null
			{
				APFDFrameworkDriverManager.getDriver().quit();
				APFDFrameworkDriverManager.unload(); // getDriver() = null;
			}
		}
}
