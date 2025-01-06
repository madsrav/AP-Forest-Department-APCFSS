package com.APFD.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.APFD.driver.APFDFrameworkDriverManager;

public class firepage {

    public firepage() {
        PageFactory.initElements(APFDFrameworkDriverManager.getDriver(), this);
    }

    @FindBy(xpath = "//select[@name='year']")
    private WebElement SelectYear;

    @FindBy(xpath = "//input[@name='fireDataId']")
    private WebElement FireDataID;

    @FindBy(xpath = "//select[@name='stype']")
    private WebElement Stype;

    @FindBy(xpath = "//select[@name='circle']")
    private WebElement Circle;

    @FindBy(xpath = "//select[@name='division']")
    private WebElement Division;

    @FindBy(xpath = "//select[@name='range']")
    private WebElement Range;

    @FindBy(xpath = "//select[@name='section']")
    private WebElement Section;

    @FindBy(xpath = "//select[@name='beat']")
    private WebElement Beat;

    @FindBy(xpath = "//select[@name='compartment']")
    private WebElement Compartment;

    @FindBy(xpath = "//select[@name='block']")
    private WebElement Block;

    @FindBy(xpath = "//input[@name='latitude']")
    private WebElement Latitude;

    @FindBy(xpath = "//input[@name='longitude']")
    private WebElement Longitude;

    @FindBy(xpath = "//input[@name='forestFireDate']")
    private WebElement ForestFireDateandTime;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement SubmitButton;

    @FindBy(xpath = "//i[@class='fa fa-power-off']")
    private WebElement ClickPowerIcon;

    @FindBy(xpath = "//div[@id='sbmt_btn' and text()=' Logout']")
    private WebElement ClickLogoutButton;

    public WebElement getSelectYear() {
        return SelectYear;
    }

    public WebElement getFireDataID() {
        return FireDataID;
    }

    public WebElement getStype() {
        return Stype;
    }

    public WebElement getCircle() {
        return Circle;
    }

    public WebElement getDivision() {
        return Division;
    }

    public WebElement getRange() {
        return Range;
    }

    public WebElement getSection() {
        return Section;
    }

    public WebElement getBeat() {
        return Beat;
    }

    public WebElement getCompartment() {
        return Compartment;
    }

    public WebElement getBlock() {
        return Block;
    }

    public WebElement getLatitude() {
        return Latitude;
    }

    public WebElement getLongitude() {
        return Longitude;
    }

    public WebElement getForestFireDateandTime() {
        return ForestFireDateandTime;
    }

    public WebElement getSubmitButton() {
        return SubmitButton;
    }

    public WebElement getClickPowerIcon() {
        return ClickPowerIcon;
    }

    public WebElement getClickLogoutButton() {
        return ClickLogoutButton;
    }

    public static class DropdownUtils {

        // Method to select a value from a dropdown by visible text
        public void selectDropdownByVisibleText(WebElement dropdownElement, String visibleText) {
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(visibleText);
        }

        // Method to randomly select a value from a dropdown
        public static String selectRandomDropdownOption(WebElement dropdownElement) {
            Select select = new Select(dropdownElement);
            List<WebElement> options = select.getOptions();
            Random random = new Random();
            int randomIndex = random.nextInt(1, options.size());
            select.selectByIndex(randomIndex);
            return options.get(randomIndex).getText();
        }
    }

    public void firePageRandomValueSelect() throws InterruptedException {
        Random r = new Random();
        // Generate a random integer between 100 and 999
        int randomInt = r.nextInt(900) + 100; // 900 is the range (999 - 100 + 1)
        String fireDataID = Integer.toString(randomInt);


        // Generate a random date and time
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime randomDateTime = now.minusDays(r.nextInt(30)).withHour(r.nextInt(24)).withMinute(r.nextInt(60));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDateTime = randomDateTime.format(formatter);

        // Storing the Test Data in the ArrayList
        List<String> inputValues = new ArrayList<>();

        inputValues.add(DropdownUtils.selectRandomDropdownOption(getSelectYear()));
        getFireDataID().sendKeys(fireDataID);
        inputValues.add(fireDataID);
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getStype()));
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getCircle()));
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getDivision()));
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getRange()));
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getSection()));
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getBeat()));
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getCompartment()));
        inputValues.add(DropdownUtils.selectRandomDropdownOption(getBlock()));
        getLatitude().sendKeys(fireDataID);
        inputValues.add(fireDataID);
        getLongitude().sendKeys(fireDataID);
        inputValues.add(fireDataID);
      
        // Use sendKeys to set the date and time
        getForestFireDateandTime().sendKeys(formattedDateTime);
        inputValues.add(formattedDateTime);
        
        // Ensure the directory exists
        String directoryPath = System.getProperty("user.dir") + "/FirePointTestData";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Write the input values to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath+"/"+fireDataID+"TestData.txt"))) {
            for (String value : inputValues) {
                writer.write(value);
                writer.newLine();
            }
            System.out.println("File written successfully."); // Debug statement
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread.sleep(5000);
        getSubmitButton().click();
        getClickPowerIcon().click();
        getClickLogoutButton().click();
    }
}