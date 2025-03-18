package testcase;

import base.DriverCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;
import utilities.TestDataProvider;

import java.time.Duration;

public class LoginPage extends DriverCreator {


@Test(dataProvider = "testDataProvider", dataProviderClass = TestDataProvider.class)
public void resetPassword(String email) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Commons.click(driver, By.xpath(locators.getProperty("reset_link")));
    Commons.enter(driver, By.id(locators.getProperty("reset_email_field")), email);
    Commons.click(driver, By.xpath(locators.getProperty("reset_password_button")));

    try {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(locators.getProperty("confirmation_message") + " | " + locators.getProperty("reset_password_error_message"))
        ));
        String messageText = message.getText();
        System.out.println("Captured message: " + messageText);
        if (messageText.contains(locators.getProperty("reset_message"))) {
            Assert.assertEquals(messageText, locators.getProperty("reset_message"));
            Commons.click(driver, By.xpath(locators.getProperty("back_to_login")));
        } else if (messageText.contains(locators.getProperty("non_registered_mail_message"))) {
            Assert.assertEquals(messageText, locators.getProperty("non_registered_mail_message"));
        } else {
            Assert.fail("Unexpected message displayed: " + messageText);
        }
    } catch (Exception e) {
        Assert.fail("Neither success nor error message was found after attempting to reset password.");
    }
}







    @Test(dataProvider = "testDataProvider", dataProviderClass = TestDataProvider.class)
    public void login(String email, String password) throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Commons.enter(driver, By.name(locators.getProperty("username_field")), email);
            Commons.enter(driver, By.name(locators.getProperty("password_field")), password);
            Commons.click(driver, By.xpath(locators.getProperty("login_button")));

            if(isElementPresent(By.xpath(locators.getProperty("dashboard")))) {
                WebElement dashElement  = driver.findElement(By.xpath(locators.getProperty("dashboard")));
                Assert.assertNotNull(dashElement,"Login Successfull");
                Commons.click(driver,By.xpath(locators.getProperty("Menu_button")));
                Commons.click(driver,By.xpath(locators.getProperty("logout_button")));
            }else{
                WebElement errorElement = driver.findElement(By.xpath(locators.getProperty("invalid_message")));
                String errorMSg = errorElement.getText();
                Assert.assertEquals(errorMSg,locators.getProperty("invalid_message_text"));
            }

    }

    private boolean isElementPresent(By xpath) {
        return false;
    }
}
