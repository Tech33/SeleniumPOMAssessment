package com.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Util.Util;

public class HomePage extends Util {

	WebDriver driver;

	public HomePage (WebDriver driver) {

		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
		
		
	}

	@FindBy(xpath = "//a[contains(text(),'OUR PRODUCTS')]")
	public WebElement ourProductsTab;

	@FindBy(xpath = "//label[text()='CHAT WITH US']")
	public WebElement chatWithUs;

	@FindBy(xpath = "//label")
	public WebElement dateOnUI;

	@FindBy(xpath = "//a[contains(text(),'CONTACT US')]")
	public WebElement contactUsTab;

	@FindBy(xpath = "//*[@name=\"categoryListboxContactUs\"]")
	public WebElement categoryListboxContactUs;

	@FindBy(xpath = "//*[@name=\"productListboxContactUs\"]")
	public WebElement productListboxContactUs;

	@FindBy(xpath = "//*[@id=\"menuSearch\"]")
	public WebElement menuSearch;
	
	@FindBy(xpath = "//*[@id=\"autoComplete\"]")
	public WebElement autoComplete;
	
	@FindBy(xpath = "//a[@class=\"productName ng-binding\"]")
	public WebElement productName;
	
	@FindBy(xpath = "//a[@class=\"productPrice ng-binding\"]")
	public WebElement productPrice;

	// Verification for Element Displayed	
	public boolean productsTabVerification() {
		
		
		waitUntilPresenceOfElementLocated(ourProductsTab);

		List<WebElement> list = driver.findElements(By.xpath("//a[text()='OUR PRODUCTS']"));
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

	public String dateVerify() throws Exception
	{

		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window	
		chatWithUs.click();

		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		String dateUI = dateOnUI.getText();
		// Close the new window, if that window no more required
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		// Continue with original browser (first window)

		return dateUI;
	}


	public boolean sortContactUsVerify()
	{
		waitUntilPresenceOfElementLocated(contactUsTab);

		Select categoryListbox = new Select(categoryListboxContactUs);
		categoryListbox.selectByVisibleText("Headphones");

		Select productList = new Select(productListboxContactUs);
		productList.selectByVisibleText("Bose SoundLink Around-ear Wireless Headphones II");

		List<String> originalList = new ArrayList<String>();

		for (WebElement e : productList.getOptions()) {
			originalList.add(e.getText());
		}

		List<String> tempList= originalList;
		Collections.sort(tempList);

		System.out.println("originalList:" + originalList);
		System.out.println("tempList:" + tempList);

		if (originalList == tempList) {
			return true;
		} else {
			return false;
		}
	}

	public String searchAndPriceCheck(String searchValue)
	{

		checkPageIsReady();
		waitUntilPresenceOfElementLocated(menuSearch);
		menuSearch.click();
		autoComplete.sendKeys(searchValue);
		checkPageIsReady();
		autoComplete.sendKeys(Keys.ENTER);
		checkPageIsReady();
		return productName.getText();
		
	}

}