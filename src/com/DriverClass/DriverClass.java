package com.DriverClass;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverClass  {

                

                public static WebDriver getDriver(String browser,String URL) throws IOException

                {               
                	 WebDriver driver=null;
                                switch (browser) {
                                case "FF":
                                                driver = new FirefoxDriver();
                                                driver.manage().deleteAllCookies();
                                                
                                                break;

                                case "IE":
                                                System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
                                                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                                                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                                                capabilities.setCapability("ignoreZoomSetting", true);
                                                capabilities.setCapability("ignoreProtectedModeSettings" , true);
                                                driver = new InternetExplorerDriver(capabilities);
                                
                                                break;
                                                
                                case "CH":
			                                	String chromepath = System.getProperty("user.dir")+ "/chromedriver/mac64/chromedriver";
			
			                                	System.setProperty("webdriver.chrome.driver", chromepath);
			                                	driver = new ChromeDriver();
                                    break;
                                                
                                                
                                case "default":
                                                System.out.println("driver is not initialised ");
                                                break;
                                }
                                
                                driver.get(URL);
                                driver.manage().window().maximize();
                                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                                return driver;
                }
                
                
}


