package login;

import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.util.Map;

public class LoginNewWithDataFromExel extends ParentTest {
    @Test
    public void validLogin() throws IOException {
        ExcelDriver excelDriver = new ExcelDriver();
        Map dataForValidLogin = excelDriver.getData(configProperties.DATA_FILE(),"validLogOn");

        loginPage.openLoginPage();
        loginPage.enterLogin(dataForValidLogin.get("login").toString());
        loginPage.enterPassword(dataForValidLogin.get("pass").toString());
        loginPage.clickButtonVhod();

        Assert.assertTrue("Avatar is not present", homePage.isAvatarDisplayed());
    }

}
