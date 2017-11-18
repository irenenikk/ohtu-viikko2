package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    }

    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_is_created(String username, String password) throws Throwable {
        new_user_selected();
        createNewUserWith(username, password);
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void tried_to_create_user(String username, String password) throws Throwable{
        user_is_created(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_password_given(String username, String password) throws Throwable {
        createNewUserWith(username, password);
    }

    @When("^a taken username \"([^\"]*)\" and a valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void taken_username_and_valid_password_given(String username, String password) throws Throwable {
        createNewUserWith(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and a non-matching password confirmation  \"([^\"]*)\" are entered$")
    public void valid_username_but_passwords_dont_match(String username, String password, String passwordConfirmation) throws  Throwable {
        createNewUserWithConfirmation(username, password, passwordConfirmation);
    }

    @When("^a non-valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void non_valid_username_and_password_given(String username, String password) throws Throwable {
        createNewUserWith(username, password);
    }

    @When("^a valid username \"([^\"]*)\" and non-valid password \"([^\"]*)\" and matching password confirmation are entered$")
    public void valid_username_and_non_valid_password_given(String username, String password) throws Throwable {
        createNewUserWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^nonexistent username \"([^\"]*)\" and any password \"([^\"]*)\" are given$")
    public void nonexistent_username_and_any_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^a non-valid username \"([^\"]*)\" and a non-valid password \"([^\"]*)\" are given$")
    public void non_valid_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^a new user is created$")
    public void user_is_created() throws  Throwable {
        pageHasContent("info for newbie");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String error) throws Throwable {
        pageHasContent(error);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    private void createNewUserWith(String username, String password) {
        createUser(username, password, password);
    }

    private void createNewUserWithConfirmation(String username, String password, String confirmation) {
        createUser(username, password, confirmation);
    }

    private void createUser(String username, String password, String confirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
