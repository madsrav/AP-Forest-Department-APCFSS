package com.APFD.utils;	

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.APFD.driver.APFDFrameworkDriverManager;
import com.github.javafaker.Faker;

public class WebDriverUtilities {


	// Method for page load wait --> Implicit wait
	public void implicitWait(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	// Method to maximize the browser window
	public void maximizeWindow(WebDriver driver) 
	{
		driver.manage().window().maximize();
	}

	// Method to close the browser
	public void closingBrowser(WebDriver driver)
	{
		if (driver != null) 
			driver.quit();
	}

	//Method to scroll to the particular element using "scrollIntoView". 
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) APFDFrameworkDriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", element);
	}

	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}

	// Method to randomly select a value from a drop-down
	public String selectRandomDropdownOption(WebElement dropdownElement) {
		Select select = new Select(dropdownElement);
		List<WebElement> options = select.getOptions();

		Random random = new Random();
		int randomIndex = random.nextInt(1, options.size());
		select.selectByIndex(randomIndex);
		return options.get(randomIndex).getText();
	}

	    public String generateRandomText(int length)
	    {
		    final Faker faker = new Faker();
	        return faker.lorem().characters(length);
	    }

public String readIdFromFile(String filePath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String id = reader.readLine();
    reader.close();
    return id;
}
}