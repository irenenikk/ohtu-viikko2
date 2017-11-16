package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Tester {
    private static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        Random r = new Random();

        checkLogin("pekka", "akkep");

        checkLogin("pekka", "pekka");

        checkLogin("nonexistent", "password");

        checkNewUser("eero"+ r.nextInt(), "salasana1");

        checkNewUser("eero" + r.nextInt(), "salasana1");
        WebElement mainPageLink = driver.findElement(By.linkText("continue to application mainpage"));
        mainPageLink.click();

        WebElement logoutLink = driver.findElement(By.linkText("logout"));
        logoutLink.click();

        driver.quit();
    }

    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static void checkLogin(String username, String password) {
        driver.get("http://localhost:4567");
        sleep(2);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(3);
    }

    private static void checkNewUser(String username, String password) {
        driver.get("http://localhost:4567");
        sleep(2);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(3);
    }
}
