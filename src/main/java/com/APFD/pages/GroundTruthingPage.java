package com.APFD.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.APFD.driver.APFDFrameworkDriverManager;
import com.APFD.utils.WebDriverUtilities;

public class GroundTruthingPage {

	public GroundTruthingPage() {
		PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);
	}
	WebDriverUtilities utils= new WebDriverUtilities();
	JavascriptExecutor js = (JavascriptExecutor) APFDFrameworkDriverManager.getDriver();

	@FindBy (xpath="//select[@name='fireDataId']") 
	private WebElement FireDataIDFRO;

	@FindAll({@FindBy(xpath="//input[@name='groundTruthingName']"),
		@FindBy(xpath="//input[@maxlength='30']")
	})
	private WebElement name;

	@FindBy(xpath="//select[@name='groundTruthingDesig']")
	private WebElement designation;

	@FindAll({@FindBy(xpath="//input[@name='groundTruthingDate']"),
		@FindBy(xpath="//input[@type='datetime-local']")
	})
	private WebElement dateandTime;

	@FindBy(xpath="//div[contains(text(),'Date & Time must be between Forest Fire Date')]")
	private WebElement incorrectDateAndTime;

	@FindBy(xpath="//input[@value='Y']")
	private WebElement confirmationofFireIncidenceYES ;

	@FindBy(xpath="//input[@value='N']")
	private WebElement confirmationofFireIncidenceNO;

	@FindBy(xpath="//select[@name='typeForestFire']")
	private WebElement typeofForestFire;

	@FindAll({@FindBy(xpath="//input[@name='areaEffectedByFire']"),
		@FindBy(xpath="//input[@maxlength='4']")
	})
	private WebElement areaEffectedByFire;

	@FindBy(xpath="//select[@name='reasonsofFire']")
	private WebElement reasonOfFire;

	@FindBy(xpath="//select[@name='extinguishtheFire']")
	private WebElement actionTakenToExtinguish;

	@FindAll({@FindBy(xpath="//input[@name='noofPersonsToStopFire']"),
		@FindBy(xpath="//input[@maxlength='3']")
	})
	private WebElement noofPersonsInvolvedtoStopFire;

	@FindAll({@FindBy(xpath="//textarea[@name='filedStaffRemarks']"),
		@FindBy(xpath="//textarea[@data-qb-tmp-id='lt-150951']")
	})
	private WebElement fieldStaffRemarks;

	@FindAll({@FindBy(xpath="//textarea[@name='fsifeedback']"),
		@FindBy(xpath="//textarea[@data-qb-tmp-id='lt-350756']")
	})
	private WebElement FSIFeedbackUpdated;

	@FindAll({@FindBy(xpath="//button[@type='submit']"),
		@FindBy(xpath="//button[@class='btn btn-success float-right btn btn-primary']")
	})
	private WebElement SubmitButton;


	public WebElement getFireDataIDFRO() {
		return FireDataIDFRO;
	}
	public WebElement getName() {
		return name;
	}

	public WebElement getDesignation() {
		return designation;
	}

	public WebElement getDateandTime() {
		return dateandTime;
	}

	public WebElement getIncorrectDateAndTime() {
		return incorrectDateAndTime;
	}
	public WebElement getConfirmationofFireIncidenceYES() {
		return confirmationofFireIncidenceYES;
	}

	public WebElement getConfirmationofFireIncidenceNO() {
		return confirmationofFireIncidenceNO;
	}

	public WebElement getTypeofForestFire() {
		return typeofForestFire;
	}

	public WebElement getAreaEffectedByFire() {
		return areaEffectedByFire;
	}

	public WebElement getReasonOfFire() {
		return reasonOfFire;
	}

	public WebElement getActionTakenToExtinguish() {
		return actionTakenToExtinguish;
	}

	public WebElement getNoofPersonsInvolvedtoStopFire() {
		return noofPersonsInvolvedtoStopFire;
	}

	public WebElement getFieldStaffRemarks() {
		return fieldStaffRemarks;
	}

	public WebElement getFSIFeedbackUpdated() {
		return FSIFeedbackUpdated;
	}

	public WebElement getSubmitButton() {
		return SubmitButton;
	}


	public void groundTruthingDoneBy() throws InterruptedException
	{	
		Random r = new Random();
		List<String> inputValues = new ArrayList<>();
		String timeanddate= new Date().toString().replace(":","_").replace(" ","_");

		// Get the current date and time
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String currentDate = currentDateTime.format(customFormatter);

		// Generate a random integer between 100 and 999
		int lowerBound = 100;
		int upperBound = 1000; // upper bound is exclusive in nextInt
		int randomInt = r.nextInt(upperBound - lowerBound) + lowerBound;
		String areaEffectedbyFire = Integer.toString(randomInt);
		String NoofPersonsInvolvedtoStopFire= Integer.toString(randomInt);

		/*Fetching the FireDataID from the new fire point 
		 *and selecting the same fire id from the drop-down to proceed further Ground Truthing*/
		Select select= new Select(FireDataIDFRO);
		select.selectByVisibleText(Enchancedfirepage.fireDataID);
		String expectedFireID = select.getFirstSelectedOption().getText();

		//Verifying Fetched FireID and Current selected FireID both are same or not.
		Assert.assertEquals(Enchancedfirepage.fireDataID, expectedFireID);

		// Generate random officer name, fieldStaffRemarks and fsiFeedback using Faker Class.
		String officerName = utils.generateRandomText(10);
		String fieldStaffRemarks = utils.generateRandomText(100);
		String fsiFeedback = utils.generateRandomText(20);

		name.sendKeys(officerName);
		inputValues.add(officerName);
		inputValues.add(utils.selectRandomDropdownOption(designation));

		// Use sendKeys to set the date and time
		getDateandTime().sendKeys(currentDate);

		//If groundTruthing Date and time is incorrect then clearing the date and again entering new date
//		if(incorrectDateAndTime.isDisplayed())
//		{		
//			js.executeScript("arguments[0].value = '';", getDateandTime());
//			System.out.println("date entered is out of date and not in between forest fire date and current date. So the Calendar field cleared and entered new date again.");
//			getDateandTime().sendKeys(currentDate);
//		}
		
		inputValues.add(currentDate);

		/*Selecting the radio buttons randomly either "YES" or "NO".
		 *based on the selection respective fields will be displayed.*/
		boolean selectYes = r.nextBoolean();

		//If the user clicks on "YES" under confirmation of Fire Incidence.
		if (selectYes) 
		{
			confirmationofFireIncidenceYES.click();
			inputValues.add(utils.selectRandomDropdownOption(typeofForestFire));
			getAreaEffectedByFire().sendKeys(areaEffectedbyFire);
			inputValues.add(areaEffectedbyFire);
			inputValues.add(utils.selectRandomDropdownOption(reasonOfFire));
			inputValues.add(utils.selectRandomDropdownOption(actionTakenToExtinguish));
			getNoofPersonsInvolvedtoStopFire().sendKeys(NoofPersonsInvolvedtoStopFire);
			inputValues.add(NoofPersonsInvolvedtoStopFire);
			getFieldStaffRemarks().sendKeys(fieldStaffRemarks);
			inputValues.add(fieldStaffRemarks);		
			getFSIFeedbackUpdated().sendKeys(fsiFeedback);
			inputValues.add(fsiFeedback);

			// Ensure the directory exists
			String directoryPath2 = System.getProperty("user.dir") + "/FirePointGroundTruthingTestDataifYes";
			File directory2 = new File(directoryPath2);
			if (!directory2.exists()) {
				directory2.mkdirs();
			}

			// Write the input values to a file
			try (BufferedWriter writer = new BufferedWriter(new FileWriter((directoryPath2 + "/"+expectedFireID+" "+timeanddate+" "+"TestData.txt")))) {
				for (String value : inputValues) {
					writer.write(value);
					writer.newLine();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			getSubmitButton().click();
		}

		//If the user clicks on "NO" under confirmation of Fire Incidence.
		else
		{
			confirmationofFireIncidenceNO.click();
			getNoofPersonsInvolvedtoStopFire().sendKeys(NoofPersonsInvolvedtoStopFire);
			inputValues.add(NoofPersonsInvolvedtoStopFire);
			getFieldStaffRemarks().sendKeys(fieldStaffRemarks);
			inputValues.add(fieldStaffRemarks);		
			getFSIFeedbackUpdated().sendKeys(fsiFeedback);
			inputValues.add(fsiFeedback);

			// Ensure the directory exists
			String directoryPath1 = System.getProperty("user.dir") + "/FirePointGroundTruthingTestDataifNo";
			File directory1 = new File(directoryPath1);
			if (!directory1.exists()) {
				directory1.mkdirs();
			}

			// Write the input values to a file
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath1 + "/"+expectedFireID+" "+timeanddate+" "+"TestData.txt"))) {
				for (String value : inputValues) {
					writer.write(value);
					writer.newLine();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			getSubmitButton().click();
		}
	}
}