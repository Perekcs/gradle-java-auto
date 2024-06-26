import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SeleniumTest {


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchPerekcsTest() {
        driver.get("https://gh-users-search.netlify.app/");

        WebElement searchBox = driver.findElement(By.cssSelector("input[data-testid='search-bar']"));
        searchBox.sendKeys("Perekcs");
        searchBox.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.cssSelector("header div h4"), "Andrii Perekupka"));
        WebElement userName = driver.findElement(By.cssSelector("header div h4"));
        assertThat(userName.getText()).isEqualTo("Andrii Perekupka","User name does not match 'Andrii Perekupka'");
    }


    @Test
    public void addToCartAndVerifyTest() {
        driver.get("https://www.saucedemo.com/");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item")));

        WebElement firstProductAddToCartButton = driver.findElement(By.cssSelector(".inventory_item:first-of-type button"));
        WebElement firstProductNameElement = driver.findElement(By.cssSelector(".inventory_item:first-of-type .inventory_item_name"));
        String firstProductName = firstProductNameElement.getText();

        firstProductAddToCartButton.click();

        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));

        WebElement cartProductNameElement = driver.findElement(By.className("inventory_item_name"));
        String cartProductName = cartProductNameElement.getText();

        assertThat(firstProductName).isEqualTo(cartProductName,"Product name in the cart does not match the added product name.");
    }

    @Test
    public void testInventoryItemCount() {
        driver.get("https://www.saucedemo.com/");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));

        assertThat(inventoryItems.size()).isEqualTo(6);

        for (WebElement item : inventoryItems) {
            WebElement itemNameElement = item.findElement(By.className("inventory_item_name"));
            System.out.println(itemNameElement.getText());
        }
    }

}
