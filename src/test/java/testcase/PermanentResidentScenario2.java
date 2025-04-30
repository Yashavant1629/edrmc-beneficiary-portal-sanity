package testcase;

import base.BaseLogin;
import listener.TestListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.Commons;
import utilities.ReadXLSData;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Listeners(TestListeners.class)
public class PermanentResidentScenario2 extends BaseLogin {

    @Test(dataProvider = "openg2pdata", dataProviderClass = ReadXLSData.class)
    public void scenarioPR2(String headName, String headEmail, String headRID, String headPhone,
                            String memberName, String memberRID, String memberEmail, String memberPhone,
                            String regionName, String reliefProgram, String yearsInReliefProgram, String pregnantWomen, String breastfeedingWomen,
                            String childrenUnder5, String malnourishedChildren, String chronicallyIll, String disability,
                            String numberOfDisables, String transportCost, String nonFormalEducation, String formalEducation, String medicalCost,
                            String foodCost, String educationCost, String roomsRentedOut, String roomsRentedIn,
                            String farmlandSize, String rentedFarmland, String miningActivities, String noOfCattle,
                            String noOfOx, String noOfCows, String noOfSheep, String noOfGoats, String noOfCamels,
                            String noOfHorse, String noOfDonkey, String noOfMule, String noOfBeehives, String noOfChickens
    ) throws InterruptedException, IOException {

        login();
        openProgramAndStartForm();
        fillHouseholdHeadDetails(headName, headEmail, headRID, headPhone);
        selectAdministrativeLocation();
        addHouseholdMember(memberName, memberRID, memberEmail, memberPhone);
        fillHouseholdData(reliefProgram, yearsInReliefProgram, pregnantWomen, breastfeedingWomen, childrenUnder5,
                malnourishedChildren, chronicallyIll, disability, numberOfDisables, transportCost, nonFormalEducation,
                formalEducation, medicalCost, foodCost, educationCost,
                roomsRentedOut, roomsRentedIn, farmlandSize, rentedFarmland, miningActivities, noOfCattle, noOfOx,
                noOfCows, noOfSheep, noOfGoats, noOfCamels, noOfHorse, noOfDonkey, noOfMule, noOfBeehives,
                noOfChickens);
        verifyHouseholdSaved(headName, regionName);
    }

    private void openProgramAndStartForm() throws InterruptedException {
        WebElement viewMoreLink = driver.findElement(By.xpath("//a[contains(@class, 'viewprograms')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewMoreLink);
        viewMoreLink.click();
        Commons.click(driver, By.xpath(locators.getProperty("create_new")));
    }

    private void fillHouseholdHeadDetails(String headName, String headEmail, String headRID, String headPhone) throws InterruptedException {
        Commons.enter(driver, By.id(locators.getProperty("household_head_name")), headName);
        Commons.click(driver, By.id(locators.getProperty("head_dob")));
        WebElement yearInput = driver.findElement(By.xpath("//input[@class='numInput cur-year']"));
        yearInput.clear();
        yearInput.sendKeys("1999");
        new Select(driver.findElement(By.xpath("//select[@class='flatpickr-monthDropdown-months']")))
                .selectByVisibleText("April");
        driver.findElement(By.xpath("//span[@class='flatpickr-day' and text()='3']")).click();

        WebElement sexDropdown = driver.findElement(By.name(locators.getProperty("head_sex")));
        Commons.selectByIndex(sexDropdown, 1);

        Commons.enter(driver, By.id(locators.getProperty("head_email")), headEmail);
        Commons.enter(driver, By.id(locators.getProperty("head_address")), "Bangalore");
        Commons.click(driver, By.id(locators.getProperty("idp?")));
        Commons.enter(driver, By.id(locators.getProperty("total_number_of_hosehold_members")), "2");
        Commons.click(driver,By.id(locators.getProperty("natioalID?No")));
        Commons.enter(driver,By.id(locators.getProperty("head_rid")),headRID);
        Commons.enter(driver,By.id(locators.getProperty("head_confirm_rid")),headRID);
        Commons.enter(driver, By.id(locators.getProperty("head_phone_number")), headPhone);
        Commons.click(driver, By.id(locators.getProperty("administrative_location_details")));
    }

    private void selectAdministrativeLocation() throws InterruptedException {
        WebElement region = driver.findElement(By.id(locators.getProperty("region")));
        Commons.selectByIndex(region, 1);
        wait(2);
        waitForElement(By.id(locators.getProperty("zone")));
        Commons.selectByIndex(driver.findElement(By.id(locators.getProperty("zone"))), 1);
        wait(2);
        waitForElement(By.id(locators.getProperty("woreda")));
        Commons.selectByIndex(driver.findElement(By.id(locators.getProperty("woreda"))), 1);
        Commons.enter(driver, By.id(locators.getProperty("kebele")), "kebele");
        Commons.enter(driver, By.id(locators.getProperty("gote")), "gote");
        Commons.enter(driver, By.id(locators.getProperty("village")), "village");
    }

    private void addHouseholdMember(String memberName, String memberRID, String memberEmail, String memberPhone) throws InterruptedException {
        Commons.click(driver, By.id(locators.getProperty("member_details")));
        Commons.click(driver, By.xpath(locators.getProperty("add_member")));
        Commons.enter(driver, By.id(locators.getProperty("first_name")), memberName);
        Commons.enter(driver, By.id(locators.getProperty("father_name")), "test");
        Commons.enter(driver, By.id(locators.getProperty("grand_father_name")), "member1");
        Commons.click(driver, By.xpath(locators.getProperty("dob")));
        driver.findElement(By.xpath("//span[@class='flatpickr-day' and text()='3']")).click();
        wait(1);

        Commons.selectByIndex(driver.findElement(By.xpath(locators.getProperty("sex"))), 1);
        Commons.click(driver,By.id(locators.getProperty("national_id?No")));
        Commons.enter(driver,By.id(locators.getProperty("enter_member_rid")),memberRID);
        Commons.enter(driver,By.id(locators.getProperty("confirm_member_rid")),memberRID);
        Commons.enter(driver, By.xpath(locators.getProperty("member_email")), memberEmail);
        Commons.selectByIndex(driver.findElement(By.id(locators.getProperty("relationship"))), 1);
        Commons.enter(driver, By.id(locators.getProperty("member_phone_number")), memberPhone);
        Commons.enter(driver, By.xpath(locators.getProperty("member_address")), "Bangalore");
        Commons.click(driver, By.id(locators.getProperty("Add")));
    }

    private void fillHouseholdData(String reliefProgram, String yearsInReliefProgram, String pregnantWomen, String breastfeedingWomen,
                                   String childrenUnder5, String malnourishedChildren, String chronicallyIll, String disability,
                                   String numberOfDisables, String transportCost, String nonFormalEducation, String formalEducation, String medicalCost,
                                   String foodCost, String educationCost, String roomsRentedOut, String roomsRentedIn,
                                   String farmlandSize, String rentedFarmland, String miningActivities, String noOfCattle,
                                   String noOfOx, String noOfCows, String noOfSheep, String noOfGoats, String noOfCamels,
                                   String noOfHorse, String noOfDonkey, String noOfMule, String noOfBeehives, String noOfChickens
    ) throws InterruptedException {

        Commons.click(driver, By.id(locators.getProperty("household_data")));
        WebElement reliefProgramElement = driver.findElement(By.id(locators.getProperty("relief_program")));
        Commons.selectByIndex(reliefProgramElement, Integer.parseInt(reliefProgram));
        WebElement years = driver.findElement(By.id(locators.getProperty("years_in_relief_program")));
        Commons.selectByIndex(years, Integer.parseInt(yearsInReliefProgram));
        Commons.enter(driver, By.id(locators.getProperty("pregnant_women")), pregnantWomen);
        Commons.enter(driver, By.id(locators.getProperty("breastfeeding_women")), breastfeedingWomen);
        Commons.enter(driver, By.id(locators.getProperty("children_under_5years")), childrenUnder5);
        Commons.enter(driver, By.id(locators.getProperty("malnourished_children")), malnourishedChildren);
        Commons.enter(driver, By.id(locators.getProperty("chronically_ill")), chronicallyIll);
//        if (disability.equalsIgnoreCase("yes")) {
//            Commons.click(driver, By.name(locators.getProperty("disability")));
//        }
        Commons.click(driver,By.name(locators.getProperty("disability")));
        Commons.enter(driver, By.id(locators.getProperty("number_of_disables")), numberOfDisables);
        Commons.click(driver,By.id(locators.getProperty("head_above_60")));
        Commons.click(driver,By.id(locators.getProperty("female_headed")));
        Commons.click(driver,By.id(locators.getProperty("child_headed")));
        Commons.enter(driver, By.id(locators.getProperty("transport_cost")), transportCost);
        Commons.click(driver,By.id(locators.getProperty("saving_experience")));
        Commons.enter(driver, By.id(locators.getProperty("non_formal_education")), nonFormalEducation);
        Commons.enter(driver, By.id(locators.getProperty("formal_education")), formalEducation);

        WebElement psnp = driver.findElement(By.id(locators.getProperty("psnp")));
        Commons.selectByIndex(psnp, 1);
        WebElement yearsInPSNP = driver.findElement(By.id(locators.getProperty("years_in_psnp")));
        Commons.selectByIndex(yearsInPSNP, 1);
        WebElement typeOfPSNP = driver.findElement(By.id(locators.getProperty("type_of_psnp")));
        Commons.selectByIndex(typeOfPSNP, 1);
        WebElement houseType = driver.findElement(By.id(locators.getProperty("house_type")));
        Commons.selectByIndex(houseType, 1);

        Commons.click(driver, By.id(locators.getProperty("own_cart")));
        Commons.click(driver, By.id(locators.getProperty("returnee_household")));
        Commons.enter(driver, By.id(locators.getProperty("medical_coast")), medicalCost);
        Commons.enter(driver, By.id(locators.getProperty("food_cost")), foodCost);
        Commons.enter(driver, By.id(locators.getProperty("education_cost")), educationCost);
        Commons.enter(driver, By.id(locators.getProperty("rooms_rented_out")), roomsRentedOut);
        Commons.enter(driver, By.id(locators.getProperty("rooms_rented_in")), roomsRentedIn);
        Commons.enter(driver, By.id(locators.getProperty("farmland_size")), farmlandSize);
        Commons.enter(driver, By.id(locators.getProperty("rented_farmland")), rentedFarmland);
        Commons.enter(driver, By.id(locators.getProperty("mining_activities")), miningActivities);
        Commons.click(driver, By.id(locators.getProperty("natural_resource")));
        Commons.enter(driver, By.id(locators.getProperty("no_of_cattle")), noOfCattle);
        Commons.enter(driver, By.id(locators.getProperty("no_of_ox")), noOfOx);
        Commons.enter(driver, By.id(locators.getProperty("no_of_cows")), noOfCows);
        Commons.enter(driver, By.id(locators.getProperty("no_of_sheep")), noOfSheep);
        Commons.enter(driver, By.id(locators.getProperty("no_of_goats")), noOfGoats);
        Commons.enter(driver, By.id(locators.getProperty("no_of_camels")), noOfCamels);
        Commons.enter(driver, By.id(locators.getProperty("no_of_horse")), noOfHorse);
        Commons.enter(driver, By.id(locators.getProperty("no_of_donkey")), noOfDonkey);
        Commons.enter(driver, By.id(locators.getProperty("no_of_mule")), noOfMule);
        Commons.enter(driver, By.id(locators.getProperty("no_of_beehives")), noOfBeehives);
        Commons.enter(driver, By.id(locators.getProperty("no_of_chickens")), noOfChickens);
        Commons.click(driver, By.id(locators.getProperty("own_motorbike_three_leg")));
        Commons.click(driver, By.id(locators.getProperty("own_motorbike_two_leg")));
        Commons.click(driver, By.id(locators.getProperty("construction_tools")));
        Commons.click(driver, By.xpath(locators.getProperty("living_in_abroad")));
        Commons.click(driver, By.xpath(locators.getProperty("living_in_urban_city")));
        WebElement activities = driver.findElement(By.id(locators.getProperty("manufacturing_activities")));
        Commons.selectByIndex(activities, 1);
        Commons.click(driver, By.xpath(locators.getProperty("micro_finance")));
        WebElement trade = driver.findElement(By.id(locators.getProperty("trade")));
        Commons.selectByIndex(trade, 1);
        Commons.click(driver, By.id(locators.getProperty("submit_button")));

    }


    private void verifyHouseholdSaved(String headName, String regionName) throws InterruptedException {
        wait(2);
        Map<String, String> expectedRow = new HashMap<>();
        expectedRow.put("Household Name", headName);
        expectedRow.put("Region", regionName);
        Commons.assertTableRowContainsValues(driver, "newreimbursements", expectedRow);
    }


    private void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
    }

    private void waitForElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(presenceOfElementLocated(locator));
    }
}
