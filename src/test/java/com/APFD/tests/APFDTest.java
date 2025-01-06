package com.APFD.tests;

import java.time.Duration;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.APFD.driver.APFDFrameworkDriverManager;
import com.APFD.listners.MyScreenShotListner;
import com.APFD.pages.Enchancedfirepage;
import com.APFD.pages.LandingPage;
import com.APFD.pages.LoginPage;
import com.aventstack.extentreports.Status;

@Listeners(com.APFD.listners.MyScreenShotListner.class)
public class APFDTest extends BaseTest {

	@Test(description="This test will adds a new fire point by GIS admin",enabled=true)
	@Parameters({"browser","platform"})
	public void addFirePoint() throws InterruptedException
	{
		//To avoid socket exception
		APFDFrameworkDriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		LoginPage page1=new LoginPage();
		LandingPage page2 = new LandingPage();
		Enchancedfirepage page3 = new Enchancedfirepage();
		page1.login("apfd@2024", "gisdata");
		page2.landing();
		page3.firePageRandomValueSelect();	
        MyScreenShotListner.getTest().log(Status.INFO, "New Fire Point added successfully by the GIS Admin");
	}
	
	@Test(description="This test will inform us ground Truthing done By and Uploading the Gazette Data.",enabled=true)
	@Parameters({"browser","platform"})
	public void groundTruthing() throws InterruptedException
	{
		FirePointGroundTruthingTest scenario2= new FirePointGroundTruthingTest();
		scenario2.groundTruthing();	
        MyScreenShotListner.getTest().log(Status.INFO, "Ground Truthing completed by the officer");
		
        scenario2.uploadGazetteData();
        MyScreenShotListner.getTest().log(Status.INFO, "Gazette Data Uploaded successfully by the FRO");
	}
}