package com.APFD.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.APFD.factories.APFDDriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyScreenShotListner implements ITestListener {

	private static ExtentReports report;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static String reportPath;


	@Override
	public void onStart(ITestContext context) {
		if (report == null) {
			String timeanddate= new Date().toString().replace(":","_").replace(" ","_");
			reportPath = System.getProperty("user.dir")+"/extent-test-output/extent_report_" +timeanddate+".html";

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("AP Forest Department @APCFSS");
			sparkReporter.config().setReportName("Test Report");

			report= new ExtentReports();
			report.attachReporter(sparkReporter);
			report.setSystemInfo("Environment", "QA");
			report.setSystemInfo("Tester","Sravan Kumar M" );
		}
	}
	@Override
	public void onTestStart( ITestResult result) {

		String description = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
		String platform = result.getTestContext().getCurrentXmlTest().getParameter("platform");
		String testName = result.getMethod().getMethodName() + " [" + browser + "] [" + platform + "]";

		ExtentTest extentTest = report.createTest(testName);
		extentTest.info(description);
		test.set(extentTest);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
		
//	     test.get().log(Status.PASS, "Test passed");
//	        logMethodName(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String timeanddate= new Date().toString().replace(":","_").replace(" ","_");
		String testname=result.getMethod().getMethodName();

		TakesScreenshot scrShot = ((TakesScreenshot)APFDDriverFactory.sdriver);

		// Capture screenshot as File
		File filesrc = scrShot.getScreenshotAs(OutputType.FILE);
		File dest= new File("./FailedTestScreenShot/"+testname+" "+timeanddate+".png");
		try {
			FileUtils.copyFile(filesrc, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.get().fail("<b>Screenshot of Failed Test for reference:</b><br><img src='" + dest.getAbsolutePath()+ "' height='400' width='600' />");
		// Log the exception
		test.get().fail(result.getThrowable());	
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped: " + result.getMethod().getMethodName());
		test.get().skip(result.getThrowable());
	}
	@Override
	public void onFinish(ITestContext context) {
		if (report != null)
		{
			report.flush();
		}
		try {
			Desktop.getDesktop().browse(new File(reportPath).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
//	 private void logMethodName(ITestResult result) {
//	        test.get().log(Status.INFO, result.getMethod().getMethodName() + " executed successfully");
//	    }
		 public static ExtentTest getTest() {
		        return test.get();
		    }
	}