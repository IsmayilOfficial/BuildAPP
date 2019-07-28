package ee.ut.esi.group4.buildit.bdd;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyStepdefsAccept {
    WebDriver webDriver = null;
    @Given("^I open firefox browser for accept PHR$")
    public void iOpenFirefoxBrowserForAcceptPHR() {
        webDriver = DriverInitializer.getDriver("firefox");
    }

    @When("^I navigate to http://localhost:(\\d+)/#/about page$")
    public void iNavigateToHttpLocalhostAboutPage(int arg0) {
        webDriver.get(DriverInitializer.getProperty("about"));
    }
    @And("^I click on accept button$")
    public void iClickOnAcceptButton() {
        WebElement webElement = webDriver.findElement(By.id("link"));
        webElement.click();



    }

    @Then("^I should see notfication about acception$")
    public void Quit() {
        webDriver.quit();
    }


}
