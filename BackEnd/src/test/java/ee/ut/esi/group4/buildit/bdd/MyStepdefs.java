package ee.ut.esi.group4.buildit.bdd;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;

public class MyStepdefs {
    WebDriver webDriver = null;
    @Given("^I open firefox browser$")
    public void iOpenFirefoxBrowser() {
        webDriver = DriverInitializer.getDriver("firefox");
    }

    @When("^I navigate to http://localhost:(\\d+)/#/ page$")
    public void iNavigateToHttpLocalhostPage(int arg0) {
        webDriver.get(DriverInitializer.getProperty("path"));
    }

    @And("^I provide name as \"([^\"]*)\" and startDate as \"([^\"]*)\" and endDate \"([^\"]*)\"$")
    public void iProvideNameAsAndStartDateAsAndEndDate(String arg0, String arg1, String arg2) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("Name"));
        webElement.sendKeys("mini");
        webElement = webDriver.findElement(By.id("Start date"));
        webElement.sendKeys("23.04.2019");
        webElement = webDriver.findElement(By.id("End date"));
        webElement.sendKeys("30.04.2019");
    }

    @And("^I click on submit button$")
    public void iClickOnSubmitButton() {
        WebElement webElement = webDriver.findElement(By.id("submit"));
        webElement.click();

    }


    @And("^I click on Select plant button$")
    public void iClickOnSelectPlantButton() {
        WebElement webElement = webDriver.findElement(By.id("submit(plant)"));
        webElement.click();
    }

    @And("^I click on Create Plant hire request button$")
    public void iClickOnCreatePlantHireRequestButton() {
        WebElement webElement = webDriver.findElement(By.id("submit"));
        webElement.click();
        try {
            assertThat(webElement.getText()).isEqualTo("Hire Request submitted. Waiting for confirmation.");
        } finally {
            webDriver.quit();
        }

    }


}
