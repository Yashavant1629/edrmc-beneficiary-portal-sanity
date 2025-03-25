package base;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseLogin extends DriverCreator{
    private static final Logger logger = LoggerFactory.getLogger(BaseLogin.class);

public static Properties properties = new Properties();

public static Properties locators = new Properties();

public static FileReader fileReader1;

public static FileReader fileReader2;

    @Test
    public static void login() throws IOException, InterruptedException {
        logger.info("Baselogin has been initiated");
        fileReader1 = new FileReader("testconfigs/configfile/config.properties");
        fileReader2 = new FileReader("src/main/resources/configfiles/locators.properties");
        properties.load(fileReader1);
        locators.load(fileReader2);
        driver.findElement(By.name(locators.getProperty("username_field"))).sendKeys(properties.getProperty("username"));
        logger.debug("Username Entered");
        driver.findElement(By.name(locators.getProperty("password_field"))).sendKeys(properties.getProperty("password"));
        logger.debug("Password Entered");
        driver.findElement(By.xpath(locators.getProperty("login_button"))).click();
        logger.info("BaseLogin Successful");
        Thread.sleep(2000);
    }
}
