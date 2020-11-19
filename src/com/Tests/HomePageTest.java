package com.Tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Pages.HomePage;
import com.Util.Util;


public class HomePageTest extends BaseTest {
	
	HomePage homePage;
	// Scenario steps begins below

	@BeforeTest
	public void setupenvi() throws IOException {
		setup();
		init();
	}

	@Test
	public void HeaderTest() {

		homePage = new HomePage(driver);
		boolean headerResult = homePage.productsTabVerification(); 
		
		assertEquals(headerResult, true, "HeaderTab Found");
		Reporter.log(" HeaderTab Not Found ");
	}

	@Test
	public void checkForChatAndDate() throws Exception {

		homePage = new HomePage(driver);
		
		String strDate_actual, strDate_expected;
		
		strDate_actual = homePage.dateVerify();
		strDate_expected = Util.systemDate();
		
		assertEquals(strDate_actual.trim(), strDate_expected.trim(), "Website is not showing Current Date");
		Reporter.log("Website is showing Current Date " + strDate_expected);
	}
	
	@Test
	public void checkForContactUsSorting() throws Exception {

		homePage = new HomePage(driver);
		
		homePage.sortContactUsVerify();
		boolean sortingResult = homePage.sortContactUsVerify();
		
		assertEquals(sortingResult, true, "Product List box is not in Sorted Order");
		Reporter.log("Product List box is not in Sorted Order");
	}
	
	@Test
	public void checkProductFromSearch() throws Exception {

		homePage = new HomePage(driver);
		String searchValue = "HP Pavilion 15z Touch Laptop";		
		String uiProductName = homePage.searchAndPriceCheck(searchValue);
		
		assertEquals(searchValue, uiProductName.trim(), "UI Product Name Mismatch");
	}
	
	@AfterTest
	public void Tearenvir() {
		tearDown();
	}
}
