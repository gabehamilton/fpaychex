#!/usr/bin/groovy

@Grapes([
@Grab("org.codehaus.geb:geb-core:0.7.2"),
//@Grab("org.hamcrest:hamcrest-core:1.3"),
@Grab("org.seleniumhq.selenium:selenium-firefox-driver:2.31.0"),
@Grab("org.seleniumhq.selenium:selenium-support:2.31.0")
]
)
import geb.Browser

Browser.drive {
	go "http://google.com/ncr"

	// make sure we actually got to the page
	assert title == "Google"

	// enter wikipedia into the search field
	$("input", name: "q").value("wikipedia")

	// wait for the change to results page to happen
	// (google updates the page dynamically without a new request)
	waitFor { title.endsWith("Google Search") }

	// is the first link to wikipedia?
	def firstLink = $("li.g", 0).find("a.l")
	assert firstLink.text() == "Wikipedia"

	// click the link
	firstLink.click()

	// wait for Google's javascript to redirect to Wikipedia
	waitFor { title == "Wikipedia" }
}

//import java.util.regex.Pattern
//import java.util.concurrent.TimeUnit
//import static org.junit.Assert.*
//import org.hamcrest.Description
//import org.openqa.selenium.*
//import org.openqa.selenium.firefox.FirefoxDriver
//import org.openqa.selenium.support.ui.Select
//
//public class Paychex {
//	WebDriver driver
//	String baseUrl
//	StringBuffer verificationErrors = new StringBuffer()
//
//	void setUp() throws Exception {
//		driver = new FirefoxDriver()
//		baseUrl = "https://landing.paychex.com/"
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
//	}
//
//	void testPaychex() throws Exception {
//		driver.get(baseUrl + "/ssologin/login.aspx?TYPE=33554433&REALMOID=06-e74be48c-b042-1010-b9a3-841d880e0cb3&GUID=&SMAUTHREASON=0&METHOD=GET&SMAGENTNAME=ThSwob9IQTBgYoEstJrFG07DeK1IYxusNT3c3IGgBJ7xUR7bt2eyWI849VLDEHpe&TARGET=-SM-https%3a%2f%2flanding%2epaychex%2ecom%2f")
//		driver.findElement(By.id("Username")).clear()
//		driver.findElement(By.id("Username")).sendKeys(username)
//		driver.findElement(By.id("ContinueButton")).click()
//		driver.findElement(By.id("Password")).clear()
//		driver.findElement(By.id("Password")).sendKeys(password)
//		driver.findElement(By.id("LoginButton")).click()
//		driver.findElement(By.linkText("Submit Timesheet")).click()
//		driver.findElement(By.linkText("Timesheet Express")).click()
//		driver.findElement(By.linkText("Current Pay Period")).click()
//
//// Friday March 1st
//		driver.findElement(By.id("txtDuration")).clear()
//		driver.findElement(By.id("txtDuration")).sendKeys("8")
//// Weekend
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[4]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[4]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[5]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[5]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[6]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[6]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[7]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[7]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[8]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[8]")).sendKeys("8")
//// Weekend
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[11]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[11]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[12]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[12]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[13]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[13]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[14]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[14]")).sendKeys("8")
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[15]")).clear()
//		driver.findElement(By.xpath("(//input[@id='txtDuration'])[15]")).sendKeys("8")
//		driver.findElement(By.id("btnSubmitTimesheet2")).click()
//	}
//
//	void tearDown() throws Exception {
//		driver.quit()
//		String verificationErrorString = verificationErrors.toString()
//		if (!"".equals(verificationErrorString)) {
//			fail(verificationErrorString)
//		}
//	}
//
//	private boolean isElementPresent(By by) {
//		try {
//			driver.findElement(by)
//			return true
//		} catch (NoSuchElementException e) {
//			return false
//		}
//	}
//}
//println "Success"
