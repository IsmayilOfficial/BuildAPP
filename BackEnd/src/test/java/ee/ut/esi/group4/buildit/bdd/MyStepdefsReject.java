package ee.ut.esi.group4.buildit.bdd;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyStepdefsReject {
    WebDriver webDriver = null;
    @Given("^I open firefox browser for reject PHR$")
    public void iOpenFirefoxBrowserForRejectPHR() {
        webDriver = DriverInitializer.getDriver("firefox");
    }

    @When("^I navigate to http://localhost:(\\d+)/#/about page for reject$")
    public void iNavigateToHttpLocalhostAboutPageForReject(int arg0) {
        webDriver.get(DriverInitializer.getProperty("about"));
    }

    @And("^I click on reject button$")
    public void iClickOnRejectButton() {
        WebElement webElement = webDriver.findElement(By.id("rel"));
        webElement.click();
    }

    @Then("^Quit$")
    public void quit() {
        webDriver.quit();
    }
}
