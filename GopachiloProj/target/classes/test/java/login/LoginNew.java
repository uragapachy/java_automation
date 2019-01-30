package login;

import org.junit.Assert;
import org.junit.Test;
import parentTest.ParentTest;

public class LoginNew  extends ParentTest {
    @Test

    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLogin("Student");
        loginPage.enterPassword("909090");
        loginPage.clickButtonVhod();

        checkExpectedresult("Avatar is not present",homePage.isAvatarDisplayed());


    }

    @Test
    public void inValidLogin(){
        loginPage.loginInToApp("Studend","909090");

        checkExpectedresult("Avatar is not present",homePage.isAvatarDisplayed());

    }

}
