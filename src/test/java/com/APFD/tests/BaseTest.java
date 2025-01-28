package com.APFD.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.APFD.driver.APFDFrameworkDriver;
	
	public class BaseTest {

		protected BaseTest() {
		}
		
		//@Parameters(value="browser"/*,"version","platform"}*/)
		@BeforeMethod
		protected void setUp(/*String browser/*,String version,String platform*/){
			APFDFrameworkDriver.initDriver(/*browser/*, version, platform*/);
		}

		@AfterMethod
		protected void tearDown() {
			APFDFrameworkDriver.quitDriver();
		}
}