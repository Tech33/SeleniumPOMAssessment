package com.Tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.DriverClass.DriverClass;

public class BaseTest {

	protected WebDriver driver = null;
	protected String username;
	protected String password;
	protected String initialName;
	protected String surName;
	protected String mobileNumber;
	protected String fromPort;
	protected String toPort;
	protected String url;
	protected String browsertype;

	@BeforeClass
	public void setupenvi() throws IOException {
		setup();
		init();
	}
	
	public Properties OR = new Properties();

	public void setup() throws IOException {

		FileInputStream fis = new FileInputStream("Config.properties");
		OR.load(fis);

	}

	public void init() throws IOException {
		browsertype = OR.getProperty("browser");
		url = OR.getProperty("URL");
		username = OR.getProperty("Username");
		password = OR.getProperty("Password");
		initialName = OR.getProperty("initialName");
		surName = OR.getProperty("surName");
		mobileNumber = OR.getProperty("mobileNumber");
		fromPort = OR.getProperty("fromPort");
		toPort = OR.getProperty("toPort");

		driver = DriverClass.getDriver(browsertype, url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	
	public void tearDown() {
		driver.quit();

	}
	
	@AfterClass
	public void Tearenvir() {
		tearDown();
	}

}
