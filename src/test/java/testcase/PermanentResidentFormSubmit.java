package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;
import java.time.Duration;

public class PermanentResidentFormSubmit extends BaseLogin {

    @Test
    public void permanentResidentForm(String househodlHeadName,String headDob, String headSex, String headEmail, String headAddress, String noOfHouseholdMembers, String headPhoneNumber) throws InterruptedException, IOException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Commons.click(driver, By.xpath(locators.getProperty("permanent_resident")));
        Commons.click(driver,By.xpath(locators.getProperty("create_new")));
        Commons.enter(driver,By.id(locators.getProperty("household_head_name")),househodlHeadName);
        Commons.enter(driver,By.id(locators.getProperty("head_dob")),headDob);
        Commons.enter(driver,By.xpath(locators.getProperty("head_sex")),headSex);
        Commons.enter(driver,By.xpath(locators.getProperty("head+email")),headEmail);
        Commons.enter(driver,By.xpath(locators.getProperty("head_address")),headAddress);
        Commons.click(driver,By.xpath(locators.getProperty("idp?")));
        Commons.enter(driver,By.xpath(locators.getProperty("total_number_of_hosehold_members")),noOfHouseholdMembers);
        Commons.click(driver,By.xpath(locators.getProperty("natioalID?")));
        Commons.enter(driver,By.xpath(locators.getProperty("head_phone_number")),headPhoneNumber);

    }
}
