package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PermanentResidentScenario1 extends BaseLogin {

    @Test
    public void scenario1() throws InterruptedException, IOException {
        login();
        WebElement viewMoreLink = driver.findElement(By.xpath("//a[contains(@class, 'viewprograms')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewMoreLink);
        viewMoreLink.click();
        Commons.click(driver,By.xpath(locators.getProperty("create_new")));
        Commons.enter(driver,By.id(locators.getProperty("household_head_name")),"auto test user");

        Commons.click(driver,By.id(locators.getProperty("head_dob")));
        WebElement yearInput = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));
        yearInput.clear();
        yearInput.sendKeys("1999");
        WebElement monthDropdown = driver.findElement(By.xpath("//select[@class='flatpickr-monthDropdown-months']"));
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByVisibleText("April");
        WebElement day = driver.findElement(By.xpath("//span[@class='flatpickr-day' and text()='3']"));
        day.click();

        WebElement headsexdropdown = driver.findElement(By.name(locators.getProperty("head_sex")));
        Commons.selectByIndex(headsexdropdown, 1);

        Commons.enter(driver,By.id(locators.getProperty("head_email")),"testuser@gmail.com");
        Commons.enter(driver,By.id(locators.getProperty("head_address")),"Bangalore");
        Commons.click(driver,By.id(locators.getProperty("idp?")));
        Commons.enter(driver,By.id(locators.getProperty("total_number_of_hosehold_members")),"2");
        Commons.click(driver,By.id(locators.getProperty("natioalID?")));
        Commons.enter(driver,By.id(locators.getProperty("head_uid")),"1234-5678-9012");
        Commons.enter(driver,By.id(locators.getProperty("head_confirm_uid")),"1234-5678-9012");
        Commons.enter(driver,By.id(locators.getProperty("head_phone_number")),"+251912345678");
        Commons.click(driver,By.id(locators.getProperty("administrative_location_details")));

        WebElement region = driver.findElement(By.id(locators.getProperty("region")));
        Commons.selectByIndex(region,1);
        Thread.sleep(1000);
        WebElement zone = driver.findElement(By.id(locators.getProperty("zone")));
        Commons.selectByIndex(zone,1);
        Thread.sleep(1000);
        WebElement woreda = driver.findElement(By.id(locators.getProperty("woreda")));
        Commons.selectByIndex(woreda,1);

        Commons.enter(driver,By.id(locators.getProperty("kebele")),"kebele");
        Commons.enter(driver,By.id(locators.getProperty("gote")),"gote");
        Commons.enter(driver,By.id(locators.getProperty("village")),"village");
        Commons.click(driver,By.id(locators.getProperty("member_details")));
        Commons.click(driver,By.xpath(locators.getProperty("add_member")));
        Commons.enter(driver,By.id(locators.getProperty("first_name")),"auto");
        Commons.enter(driver,By.id(locators.getProperty("father_name")),"test");
        Commons.enter(driver,By.id(locators.getProperty("grand_father_name")),"member1");
        Commons.click(driver,By.xpath(locators.getProperty("dob")));

//        WebElement memYearInput = driver.findElement(By.xpath("//div[@class='flatpickr-calendar animate arrowTop arrowLeft open']//input[@class='numInput cur-year']"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", memYearInput);
//        memYearInput.clear();
//        memYearInput.sendKeys("2006");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement memYearInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cur-year")));
////        memYearInput.clear();
//        memYearInput.sendKeys("2000");

//        WebElement memMonthDropdown = driver.findElement(By.xpath("//select[@class='flatpickr-monthDropdown-months']"));
//        Select memSelectMonth = new Select(monthDropdown);
//        memSelectMonth.selectByVisibleText("April");
        WebElement memDay = driver.findElement(By.xpath("//span[@class='flatpickr-day' and text()='3']"));
        memDay.click();
        Thread.sleep(1000);

        WebElement sex = driver.findElement(By.xpath(locators.getProperty("sex")));
        Commons.selectByIndex(sex,1);

        Commons.click(driver,By.id(locators.getProperty("national_id?")));
        Commons.enter(driver,By.id(locators.getProperty("enter_member_uid")),"1234-5678-9009");
        Commons.enter(driver,By.id(locators.getProperty("confirm_member_uid")),"1234-5678-9009");
        Commons.enter(driver,By.xpath(locators.getProperty("member_email")),"mem@gmail.com");
        WebElement relationship = driver.findElement(By.id(locators.getProperty("relationship")));
        Commons.selectByIndex(relationship,1);
        Commons.enter(driver,By.id(locators.getProperty("member_phone_number")),"+251912344321");
        Commons.enter(driver,By.xpath(locators.getProperty("member_address")),"Bangalore");
        Commons.click(driver,By.id(locators.getProperty("Add")));
        Commons.click(driver,By.id(locators.getProperty("household_data")));
        WebElement reliefProgram = driver.findElement(By.id(locators.getProperty("relief_program")));
        Commons.selectByIndex(reliefProgram,1);
        WebElement years = driver.findElement(By.id(locators.getProperty("years_in_relief_program")));
        Commons.selectByIndex(years,1);
        Commons.enter(driver,By.id(locators.getProperty("pregnant_women")),"0");
        Commons.enter(driver,By.id(locators.getProperty("breastfeeding_women")),"0");
        Commons.enter(driver,By.id(locators.getProperty("children_under_5years")),"0");
        Commons.enter(driver,By.id(locators.getProperty("malnourished_children")),"0");
        Commons.enter(driver,By.id(locators.getProperty("chronically_ill")),"0");
        Commons.click(driver,By.name(locators.getProperty("disability")));  //yes
        Commons.enter(driver, By.id(locators.getProperty("number_of_disables")),"0");
        Commons.click(driver,By.id(locators.getProperty("head_above_60")));
        Commons.click(driver,By.id(locators.getProperty("female_headed")));
        Commons.click(driver,By.id(locators.getProperty("child_headed")));
        Commons.enter(driver,By.id(locators.getProperty("transport_cost")),"123");
        Commons.click(driver,By.id(locators.getProperty("saving_experience")));
        Commons.enter(driver,By.id(locators.getProperty("non_formal_education")),"1");
        Commons.enter(driver,By.id(locators.getProperty("formal_education")),"1");

        WebElement psnp = driver.findElement(By.id(locators.getProperty("psnp")));
        Commons.selectByIndex(psnp,1);
        WebElement yearsInPSNP = driver.findElement(By.id(locators.getProperty("years_in_psnp")));
        Commons.selectByIndex(yearsInPSNP,1);
        WebElement typeOfPSNP = driver.findElement(By.id(locators.getProperty("type_of_psnp")));
        Commons.selectByIndex(typeOfPSNP,1);
        WebElement houseType = driver.findElement(By.id(locators.getProperty("house_type")));
        Commons.selectByIndex(houseType,1);

        Commons.click(driver, By.id(locators.getProperty("own_cart")));
        Commons.click(driver, By.id(locators.getProperty("returnee_household")));
        Commons.enter(driver, By.id(locators.getProperty("medical_coast")),"100");
        Commons.enter(driver, By.id(locators.getProperty("food_cost")),"100");
        Commons.enter(driver, By.id(locators.getProperty("education_cost")),"100");
        Commons.enter(driver, By.id(locators.getProperty("rooms_rented_out")),"0");
        Commons.enter(driver, By.id(locators.getProperty("rooms_rented_in")),"1");
        Commons.enter(driver, By.id(locators.getProperty("farmland_size")),"100");
        Commons.enter(driver, By.id(locators.getProperty("rented_farmland")),"100");
        Commons.enter(driver, By.id(locators.getProperty("mining_activities")),"100");
        Commons.click(driver, By.id(locators.getProperty("natural_resource")));
        Commons.enter(driver, By.id(locators.getProperty("no_of_cattle")),"10");
        Commons.enter(driver, By.id(locators.getProperty("no_of_ox")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_cows")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_sheep")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_goats")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_camels")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_horse")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_donkey")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_mule")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_beehives")),"2");
        Commons.enter(driver, By.id(locators.getProperty("no_of_chickens")),"2");
        Commons.click(driver, By.id(locators.getProperty("own_motorbike_three_leg")));
        Commons.click(driver, By.id(locators.getProperty("own_motorbike_two_leg")));
        Commons.click(driver, By.id(locators.getProperty("construction_tools")));
        Commons.click(driver, By.xpath(locators.getProperty("living_in_abroad")));
        Commons.click(driver, By.xpath(locators.getProperty("living_in_urban_city")));
        WebElement activities = driver.findElement(By.id(locators.getProperty("manufacturing_activities")));
        Commons.selectByIndex(activities,1);
        Commons.click(driver,By.xpath(locators.getProperty("micro_finance")));
        WebElement trade = driver.findElement(By.id(locators.getProperty("trade")));
        Commons.selectByIndex(trade,1);
        Commons.click(driver,By.id(locators.getProperty("submit_button")));
        Thread.sleep(2000);
        Map<String, String> expectedRow = new HashMap<>();
        expectedRow.put("Household Name", "auto test user");
        expectedRow.put("Region", "Amhara");

        Commons.assertTableRowContainsValues(driver, "newreimbursements", expectedRow);
    }
}
