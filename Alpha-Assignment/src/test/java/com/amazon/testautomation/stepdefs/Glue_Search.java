package com.amazon.testautomation.stepdefs;

import com.amazon.testautomation.support_library.CoreLibrary;
import com.amazon.testautomation.support_library.Log;
import constants.GlobalConstants;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Glue_Search {

    @Given("Navigate to Amazon online shopping portal homepage using (.*)")
    public static void navigateToApplication(String appURL) {
        Log.info("=> Navigating to the application using the URL - " + appURL);
        CoreLibrary.launchApplication(appURL);
        CoreLibrary.verifyPageURL(appURL);
    }

    @When("Search for product (.*)")
    public static void searchProduct(String product) {
        Log.info("=> Searching for the product - " + product);
        CoreLibrary.typeInControl(GlobalConstants.searchBox, product);
        CoreLibrary.clickElement(GlobalConstants.searchButton);
    }

    @And("Sort the search results with (.*)")
    public static void sortProducts(String optionSort) {
        Log.info("=> Sorting the search results with - " + optionSort);
        CoreLibrary.setValueByVisibleTextFromDropdown(GlobalConstants.sortDropdown, optionSort);
    }

    @And("Select (.*)nd product from the sorted results")
    public static void selectProduct(int productToVerify) throws InterruptedException {
        Log.info("=> Selecting " + productToVerify + "nd product for look in for more details");
        CoreLibrary.clickElement(CoreLibrary.getWebElements(GlobalConstants.sortedResults).get(productToVerify-1));
    }

    @Then("Verify if chosen product is of the model (.*)")
    public static void verifyProduct(String productToVerify) {
        Log.info("=> Expected Product :" + productToVerify);
        Log.info("=> Actual Product :" + CoreLibrary.getBrowserTitle());
        CoreLibrary.assertValues(CoreLibrary.getElementText(GlobalConstants.productTitle), productToVerify);
    }
}
