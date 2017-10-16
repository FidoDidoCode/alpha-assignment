package constants;

import org.openqa.selenium.By;


public class GlobalConstants {

    public static final String browserType = "chrome";

    // Amazon homepage locators
    public static final By searchBox = By.id("twotabsearchtextbox");
    public static final By searchButton = By.xpath("//input[@type = 'submit']");
    public static final By sortDropdown = By.id("sort");
    public static final By sortedResults = By.xpath("//a[contains(@class, 'access-detail-page')]");
    public static final By productTitle = By.xpath("//div[@id='centerCol']//span[@id='productTitle']");
    public static final String failureMessage = "*** Model not matched ***";
}
