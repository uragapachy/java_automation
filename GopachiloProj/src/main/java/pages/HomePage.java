package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy (xpath = ".//*[@class='pull-left image']")
    WebElement avatar;
    @FindBy (xpath = " .//*[@id='dictionary']/a")
    private WebElement menuDictionary;

    @FindBy(xpath = " .//*[@id='spares']")
    private WebElement subMenuSpare;


    public HomePage(WebDriver webDriver) {
        super(webDriver,"/");
    }


@Step
   public boolean isAvatarDisplayed(){
//        try {
//            return avatar.isDisplayed();
//
//
//        }catch (Exception e){
//            return false;
//        }
       return actionWithOurElements.isElemetDisplayed(avatar);
    }

    public void clickMenuDictionary() {
        actionWithOurElements.clickOnElement(menuDictionary);
    }

    public void clickOnSubMenuSpare() {
        actionWithOurElements.clickOnElement(subMenuSpare);
    }
}
