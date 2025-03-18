package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utilities.Commons;

public class PermanentResidentFormSubmit extends BaseLogin {

    @Test
    public void permanentResidentForm() throws InterruptedException {
        Commons.click(driver, By.xpath(locators.getProperty("permanent_resident")));
        Commons.click(driver,By.id(locators.getProperty("create_new")));
    }
}
