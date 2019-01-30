package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(name = "_username")
    WebElement inputLogin;
    @FindBy(name = "_password")
    WebElement inputpassWord;
    @FindBy(xpath = ".//button[@type='submit']")
    WebElement buttonVhod;



    public LoginPage(WebDriver webDriver) {
        super(webDriver,"/login");
    }

    public void openLoginPage(){
        try {
            webDriver.get("http://v3.test.itpmgroup.com");


            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not open login page " + e);

            Assert.fail("Can not open login page" + e);
        }
    }

    public void enterLogin(String login) {
        actionWithOurElements.enterTextIntoElement(inputLogin,login);
    }
    public void enterPassword(String passWord) {
//        try {
//            inputpassWord.clear();
//            inputpassWord.sendKeys(passWord);
//            logger.info(passWord+ "was inputted into Input");
//
//        }catch (Exception e){
//            logger.error("Cannot work with element ");
//            Assert.fail("Cannot work with element");
//        }
        actionWithOurElements.enterTextIntoElement(inputpassWord,passWord);
    }

    public void clickButtonVhod() {
//        try {
//            buttonVhod.click();
//            logger.info("Was click botton Vhod");
//
//        }catch (Exception e){
//            logger.error("Cannot work with element");
//            Assert.fail("Cannot work with element");
//        }
        actionWithOurElements.clickOnElement(buttonVhod);

    }
  @Step
    public void loginInToApp(String login, String password) {
        openLoginPage();
        enterLogin(login);
        enterPassword(password);
        clickButtonVhod();

    }
    public  boolean isButtonVhodDisplayed(){
        return  actionWithOurElements.isElemetDisplayed(buttonVhod);
    }

    public void validLoginInTOApp(){
        loginInToApp("Student","909090");
        HomePage homePage=new HomePage(webDriver);
        homePage.isAvatarDisplayed();
    }
}
