package com.APFD.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.APFD.pages.FroLandingPage;
import com.APFD.pages.GroundTruthingPage;
import com.APFD.pages.LoginPage;
import com.APFD.pages.UploadGazzetteMapPage;

public class FirePointGroundTruthingTest extends BaseTest{
	@Test(description="This test will display ground truthing details done by",enabled=true)
	@Parameters({"browser","platform"})
	public void groundTruthing() throws InterruptedException
	{
		LoginPage Page1=new LoginPage();
		Page1.login("apfd@2024","fro_cntr");
		FroLandingPage Page2= new FroLandingPage();
		Page2.froLanding();
		GroundTruthingPage Page3=new GroundTruthingPage();
		Page3.groundTruthingDoneBy();	
	}
	
	@Test(description="This test will uploads the Gazette data",enabled=false)
	@Parameters({"browser","platform"})
	public void uploadGazetteData() throws InterruptedException
	{
//		LoginPage Page1=new LoginPage();
//		Page1.login("apfd@2024","fro_cntr");
		UploadGazzetteMapPage page=new UploadGazzetteMapPage();
		page.uploadGazetteData();
	}
}