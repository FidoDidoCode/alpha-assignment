package com.amazon.testautomation.support_library;

import constants.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CoreLibrary {

    private static final int Default_Page_Wait_In_Seconds = 15;
    private static WebDriver driver = null;

    public static void createBrowserInstance(String typeBrowser) {

        if (typeBrowser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            Log.info("=> Creating a chrome local browser instance...");
        } else if (typeBrowser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            Log.info("=> Creating a firefox local browser instance...");
        } else {
            driver = new ChromeDriver();
            Log.info("Alert: Specified browser is unsupported. Creating a default (chrome) local browser instance...");
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public static void quitBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }

    public static void launchApplication(String AppURL) {
        driver.navigate().to(AppURL);
        waitForPageLoad();
    }

    public static void typeInControl(By locator, String inputText) {
        try {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(inputText);
            waitForTime(5000);
        } catch (StaleElementReferenceException Ex) {
            typeInControl(locator, inputText);
        }
    }

    public static void clickElement(By locator) {
        try {
            driver.findElement(locator).click();
            waitForTime(5000);
        } catch (NoSuchElementException var2) {
            Log.info("Alert: Element with the locator '" + locator + "' not found. Please check!");
        } catch (StaleElementReferenceException var3) {
            clickElement(locator);
        }
    }

    public static void clickElement(WebElement elementToBeClicked) {
        try {
            //below to be removed
            if(elementToBeClicked.isDisplayed()){
                elementToBeClicked.click();
            }
            //above to be removed
            elementToBeClicked.click();
        } catch (NoSuchElementException var2) {
            Log.info("Alert: Element '" + elementToBeClicked.getText() + "' not found. Please check!");
        } catch (StaleElementReferenceException var3) {
            elementToBeClicked.click();
        }
    }

    public static void setValueByVisibleTextFromDropdown(By locator, String textOption) {
        Select selectBox = new Select(driver.findElement(locator));
        selectBox.selectByVisibleText(textOption);
        waitForTime(6000);
    }

    private static void waitForTime(int timeWaitInMilliSecs) {
        driver.manage().timeouts().implicitlyWait((long) timeWaitInMilliSecs, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep((long) timeWaitInMilliSecs);
        } catch (InterruptedException iEx) {
            Log.info("Alert: Your wait has been interrupted");
        }
    }

    public static List<WebElement> getWebElements(By locator) {
        waitForPageLoad();
        return driver.findElements(locator);
    }

    public static String getBrowserTitle() {
        return driver.getTitle();
    }

    public static String getBrowserURL() {
        return driver.getCurrentUrl();
    }

    private static void waitForPageLoad() {
        try {
            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return Boolean.valueOf("complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState", new Object[0])));
                }
            };
            Wait<WebDriver> wait = new WebDriverWait(driver, Default_Page_Wait_In_Seconds);
            wait.until(pageLoadCondition);
        } catch (Exception var2) {
            Log.info("Alert: Page not loaded successfully");
        }
    }

    public static void verifyPageURL(String appURL) {
        CoreLibrary.waitForPageLoad();
        Assert.assertTrue("" + appURL + " - Page has not loaded successfully. Please try again!", CoreLibrary.getBrowserURL().contains(appURL));
    }

    public static String getElementText(By locator) {
        return driver.findElement(locator).getText();
    }

    private static void takeScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("target/screenshots/screenshot_" + System.currentTimeMillis() + ".png"));
        } catch (IOException iEx) {
            Log.info("Alert: Screenshot not saved");
        }
    }

    public static void assertValues(String actualValue, String expectedValue) {
        takeScreenshot();
        Assert.assertTrue(GlobalConstants.failureMessage, actualValue.contains(expectedValue));
        Log.info("Match Found");
    }
}
