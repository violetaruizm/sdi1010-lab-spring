package com.uniovi.tests;

import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {

	// EnWindows (Debe ser la versión 65.0.1 y
	// desactivar las actualizacioens automáticas)):
	static String PathFirefox65 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver022 = "C:\\Users\\uo258454\\Desktop\\S5material\\PL-SDI-S5-material\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1y desactivar las actualizacioens
	// automáticas):
	// staticString
	// PathFirefox65="/Applications/Firefox.app/Contents/MacOS/firefox-­‐bin";
	// staticString Geckdriver024= "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver022);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {

		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		
		
		driver.manage().deleteAllCookies();
	}
	
	@BeforeClass
	public void begin() {}
	
	@AfterClass
	public void end() {
		driver.quit();
	}

	@Test
	public void test() {
		fail("Not  yet  implemented");
	}

}
