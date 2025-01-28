package com.APFD.tests;

import org.testng.annotations.Test;

import com.APFD.enums.ConfigurationProperties;
import com.APFD.pages.FroLandingPage;
import com.APFD.pages.GroundTruthingPage;
import com.APFD.pages.LoginPage;
import com.APFD.pages.UploadGazzetteMapPage;
import com.APFD.utils.PropertyFileUtilities;

public class FirePointGroundTruthingTest extends BaseTest{
	@Test(description="This test will display ground truthing details done by",enabled=true)
	public void groundTruthing() throws InterruptedException
	{
		LoginPage Page1=new LoginPage();
		Page1.login(PropertyFileUtilities.readDataFromPropertyFile(ConfigurationProperties.FROUSERNAME),PropertyFileUtilities.readDataFromPropertyFile(ConfigurationProperties.PASSWORD));
		FroLandingPage Page2= new FroLandingPage();
		Page2.froLanding();
		GroundTruthingPage Page3=new GroundTruthingPage();
		Page3.groundTruthingDoneBy();	
	}
	
	@Test(description="This test will uploads the Gazette data",enabled=false)
	public void uploadGazetteData() throws InterruptedException
	{
		UploadGazzetteMapPage page=new UploadGazzetteMapPage();
		page.uploadGazetteData();
	}
}