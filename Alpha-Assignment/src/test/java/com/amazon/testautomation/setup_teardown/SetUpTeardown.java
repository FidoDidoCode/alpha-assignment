package com.amazon.testautomation.setup_teardown;

import com.amazon.testautomation.support_library.CoreLibrary;
import com.amazon.testautomation.support_library.Log;
import constants.GlobalConstants;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;


public class SetUpTeardown {

    @BeforeClass
    public static void setUp() {
        CoreLibrary.createBrowserInstance(GlobalConstants.browserType);
    }

    @AfterClass
    public static void teardown() {
        try {
            Log.info("=> Cleaning up browser traces and system configurations, after tests ...");
            CoreLibrary.quitBrowser();
            Log.info("***********************************************************************************");
        } catch (NoSuchSessionException ex) {
            Log.info("Alert: Browser may be already dead/closed. So could not any more perform browser related clean-up");
            Log.info("**********************************************************************************");
        } catch (SessionNotCreatedException ex) {
            Log.info("Alert: Browser may be already dead/closed. So could not any more perform browser related clean-up");
            Log.info("**********************************************************************************");
        }
    }
}
