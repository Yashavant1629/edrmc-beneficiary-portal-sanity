package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Commons {


    public static WebElement click(WebDriver driver, By by) throws InterruptedException {
//        logger.info("Clicking " + by );
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by));
            sleep(500);
            driver.findElement(by).click();
//            sleep(500);
        } catch (StaleElementReferenceException sere) {
            sleep(500);
            driver.findElement(by).click();
        } catch (TimeoutException toe) {
            driver.findElement(by).click();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Element identified by " + by.toString() + " was not clickable after 10 seconds");
        } catch (Exception e) {

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(by));

        }
        Thread.sleep(500);
        return null;
    }

    public static WebElement enter(WebDriver driver, By by, String value) {
//        logger.info("Entering " + by +value);
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.visibilityOfElementLocated(by));
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(value);
            try {
                sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (StaleElementReferenceException sere) {
            driver.findElement(by).sendKeys(value);
        } catch (TimeoutException toe) {
            driver.findElement(by).sendKeys(value);
            System.out.println("Element identified by " + by.toString() + " was not clickable after 10 seconds");
        }
        return null;
    }

    public static boolean isEntryPresentInPaginatedTable(WebDriver driver, String tableXpath, String expectedText) {
        boolean entryFound = false;

        while (true) {
            try {
                if (isEntryPresent(driver, tableXpath, expectedText)) {
                    entryFound = true;
                    System.out.println("Found expected text: " + expectedText);
                    break;
                }

                WebElement nextPageButton = driver.findElement(By.xpath("//button[@class='oi oi-chevron-right btn btn-secondary o_pager_next px-2 rounded-end']"));
                if (nextPageButton.isEnabled()) {
                    nextPageButton.click();

                } else {
                    System.out.println("Expected text not found on any page: " + expectedText);
                    break;
                }
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale Element Reference Exception occurred. Retrying...");
                continue;
            }
        }
        return entryFound;
    }

    public static boolean isEntryPresent(WebDriver driver, String tableXpath, String expectedText) {
        WebElement table = driver.findElement(By.xpath(tableXpath));
        java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));

        boolean entryFound = false;

        for (WebElement row : rows) {
            java.util.List<WebElement> cells = row.findElements(By.tagName("td"));

            for (WebElement cell : cells) {
                String cellText = cell.getText();
                if (cellText.equals(expectedText)) {
                    entryFound = true;
                    return entryFound;
                }
            }
        }

        return entryFound;
    }

    public static void dropdown(WebDriver driver, By by)
    {
//        logger.info("Selecting DropDown Index Zero Value " + by );

        try {
            sleep(500);
            click(driver,by);//REGION
            sleep(500);

            String att= driver.findElement(by).getAttribute("aria-owns");
            String[] list=att.split(" ");
            click( driver,By.id(list[1]));
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch(Exception e)
        {
            e.getMessage();
        }
    }

    public static void clearAndEnter(WebDriver driver, By by, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

        try {
            element.clear();
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
            element.sendKeys(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropDownByValue(WebDriver driver, By dropdownLocator, String valueToSelect) {
        try {
            List<WebElement> optionList = driver.findElements(dropdownLocator);
            for (WebElement ele : optionList) {
                String currentOption = ele.getText();
                if (currentOption.equals(valueToSelect)) {
                    ele.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebElement clickEntryInTable(WebDriver driver, By tableLocator, String entryName) {
        WebElement table = driver.findElement(tableLocator);
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().trim().equals(entryName)) {
                    cell.click();
                    return table;
                }
            }
        }
        System.out.println("Entry '" + entryName + "' not found in the table.");
        return table;
    }

    public static void selectDropdownByIndex(WebDriver driver, By dropdownLocator, int index) {
        try {
            WebElement dropdownElement = driver.findElement(dropdownLocator);
            Select select = new Select(dropdownElement);
            select.selectByIndex(index);

            System.out.println("Dropdown selected at index: " + index);
        } catch (Exception e) {
            System.out.println("Failed to select dropdown option by index: " + e.getMessage());
        }
    }

    public static void selectByIndex(WebElement dropdownElement, int index) {
        try {
            Select select = new Select(dropdownElement);
            select.selectByIndex(index);
            System.out.println("Selected option at index: " + index);
        } catch (Exception e) {
            System.err.println("Failed to select option at index " + index + ": " + e.getMessage());
        }
    }

    public static void assertTableRowContainsValues(WebDriver driver, String tableId, Map<String, String> expectedData) {
        boolean matchFound = false;

        while (true) {

            Map<String, Integer> columnIndexMap = new HashMap<>();
            List<WebElement> headers = driver.findElements(By.xpath("//table[@id='" + tableId + "']//thead//th"));
            for (int i = 0; i < headers.size(); i++) {
                columnIndexMap.put(headers.get(i).getText().trim(), i + 1); // 1-based index
            }


            List<WebElement> rows = driver.findElements(By.xpath("//table[@id='" + tableId + "']//tbody/tr"));
            for (WebElement row : rows) {
                if (isRowMatching(row, expectedData, columnIndexMap)) {
                    matchFound = true;
                    break;
                }
            }

            if (matchFound) break;
            if (!goToNextPage(driver)) break;
        }

        Assert.assertTrue(matchFound, "No matching row found in table '" + tableId + "' for values: " + expectedData);
    }


    private static boolean isRowMatching(WebElement row, Map<String, String> expectedData, Map<String, Integer> columnIndexMap) {
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            Integer colIndex = columnIndexMap.get(entry.getKey());
            if (colIndex == null) {
                throw new RuntimeException("Column '" + entry.getKey() + "' not found in table");
            }

            String actualValue = row.findElement(By.xpath("./td[" + colIndex + "]")).getText().trim();
            if (!actualValue.equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }


    private static boolean goToNextPage(WebDriver driver) {
        try {
            WebElement nextBtn = driver.findElement(By.cssSelector("#page-buttons .next-button"));
            if (nextBtn.isDisplayed() && nextBtn.isEnabled()) {
                nextBtn.click();
                Thread.sleep(1000);
                return true;
            }
        } catch (NoSuchElementException | InterruptedException e) {

        }
        return false;
    }



}
