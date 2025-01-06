package com.APFD.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.APFD.listners.MyScreenShotListner;
import com.APFD.pages.Enchancedfirepage;
import com.APFD.pages.LandingPage;
import com.APFD.pages.LoginPage;
import com.aventstack.extentreports.Status;

@Listeners(com.APFD.listners.MyScreenShotListner.class)
public class AddFirePointTest extends BaseTest{
	
	@Test(description="This test will adds a new fire point by GIS admin", enabled=true)
	@Parameters({"browser","platform"})
	public void addFirePoint() throws InterruptedException
	{
		LoginPage page1= new LoginPage();
		LandingPage page2= new LandingPage();
		Enchancedfirepage page3= new Enchancedfirepage();
		page1.login("apfd@2024", "gisdata");
		page2.landing();
		page3.firePageRandomValueSelect();
		
        MyScreenShotListner.getTest().log(Status.INFO, "New Fire Point added successfully by the GIS Admin");
        
        FirePointGroundTruthingTest Scenario2= new FirePointGroundTruthingTest();
        Scenario2.groundTruthing();
		MyScreenShotListner.getTest().log(Status.INFO, "Ground Truthing completed by the officer");

		Scenario2.uploadGazetteData();
		MyScreenShotListner.getTest().log(Status.INFO, "Gazette Data Uploaded successfully by the FRO");	
	}
}
