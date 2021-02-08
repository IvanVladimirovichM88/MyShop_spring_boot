package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RoleSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser for Roles page$")
    public void iOpenBrowser() throws Throwable{
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to Roles Page$")
    public void iNavigateToRolePage() throws Throwable{
        webDriver.get(DriverInitializer.getProperty("role.url"));
    }

    @Then("^Is open Login page for Role page$")
    public void isOpenLoginPage() throws Throwable{
        assertThat(webDriver.getCurrentUrl())
                .isEqualTo(DriverInitializer.getProperty("login.url")) ;
    }

    @When("^I insert username as \"([^\"]*)\" and password as \"([^\"]*)\" for Role page$")
    public void iInsertUsernameAndPassword(String username, String password) throws Throwable{
        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        webElement.sendKeys(username);
        Thread.sleep(1000);
        webElement = webDriver.findElement(By.id("inp-password"));
        webElement.sendKeys(password);
        Thread.sleep(1000);
    }

    @When("^I click on login button for Role page$")
    public void iClickOnLoginButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @Then("^Is open role page$")
    public void isOpenRolePage()throws Throwable{
        assertThat(webDriver.getCurrentUrl())
                .isEqualTo(DriverInitializer.getProperty("role.url")) ;
    }

    @When("^I click on Add role button$")
    public void IClickAddRole() throws Throwable{
        WebElement addRoleButton = webDriver.findElement(By.id("add_role"));
        addRoleButton.click();
    }

    @Then("^is open page  Add role$")
    public void isOpenPageAddRole() throws Throwable{
        assertThat(webDriver.getCurrentUrl())
                .isEqualTo(DriverInitializer.getProperty("roleAdd.url"));
    }

    @When("^I insert new role title \"([^\"]*)\"$")
    public void iInsertNewRoleTitle(String newRole) throws Throwable {
        WebElement titleInput = webDriver.findElement(By.id("roleTitle"));
        titleInput.sendKeys(newRole);
        Thread.sleep(1000);
    }


    @After
    public void endTest(){
        if (webDriver!=null) {
            webDriver.quit();
        }
    }

    @When("^I click on Submit new Role Button$")
    public void iClickOnSubmitNewRoleButton() throws Throwable{
        WebElement submitButton = webDriver.findElement(By.id("submit"));
        submitButton.click();
        Thread.sleep(1000);
    }

    @Then("^Is the list Roles include \"([^\"]*)\"$")
    public void isTheListRolesInclude(String newRole) throws  Throwable{
        List<WebElement> webElements = webDriver.findElements(By.id("role_line"));

        Optional <WebElement> element = webElements.stream()
                .filter(webElement -> webElement
                        .findElement(By.id("role_name"))
                        .getText()
                        .equals("ROLE_TEST"))
                .findFirst();

        Thread.sleep(1000);

        if (element.isPresent()){
            element.get().findElement(By.id("remove_btn")).click();
        }

        Thread.sleep(1000);

    }



}
