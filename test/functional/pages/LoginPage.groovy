package pages

import org.codehaus.groovy.grails.plugins.webdriver.ButtonElement

/**
 * User: gabe
 * Date: 3/15/13
 * Time: 4:43 PM
 */
class LoginPage {
	static expectedTitle = "Login"
	static expectedURL = ~"/login/.*"

	String username
	String password
	String message

	ButtonElement<HomePage> loginButton

	static elements = {
		message(By.xpath("//div[@class='messages']"))
	}
}
