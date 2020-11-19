package com.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	
	WebDriver driver;
	
	public Util(WebDriver driver) {

		this.driver= driver;
	}

	public static String systemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
		Date date = new Date();
		// System.out.println(dateFormat.format(date));
		String Expected_date = dateFormat.format(date);
		return Expected_date;
	}
	
	//Define Wait
	protected void waitUntilPresenceOfElementLocated(WebElement locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, 240, 1000);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	
	protected void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")){ 
			//System.out.println("Page Is loaded..........");
			return; 
		} 

		//This loop will rotate for 25 times to check If page Is ready after every 1 second.
		//You can replace your value with 25 If you wants to Increase or decrease wait time.
		for (int i=0; i<25; i++){ 
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {} 
			//To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")){ 
				break; 
			}   
		}
	} 
}
