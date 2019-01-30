package libs;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionWithOurElements {
    WebDriver webDriver;
    Logger logger=Logger.getLogger(getClass());
    WebDriverWait wait5,wait10;



    public ActionWithOurElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait5=new WebDriverWait(webDriver,5);
        wait10=new WebDriverWait(webDriver,10);
    }

    public void enterTextIntoElement(WebElement element,String text){
        try {
            element.clear();
            element.sendKeys(text);
            logger.info(text+ " was inputted into Input");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    public void clickOnElement(WebElement element) {

        try {
            wait5.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Element was clicked" +element);
        }catch (Exception e){
//            logger.error("Cannot work with element " +e);
//            Assert.fail("Cannot work with element " +e);
            printErrorAndStopTest(e);
        }



    }
    public boolean isElemetDisplayed(WebElement element){
        try {
            return element.isDisplayed();


        }catch (Exception e){
            return false;
        }
    }

    public void selectTextInDD(WebElement element, String Text) {
        try {
            //Select open DD
            Select select=new Select(element);
            select.selectByVisibleText(Text);
            logger.info(Text+" was select in DD");
        }catch (Exception e){
//            logger.error("Cannot work with element " +e);
//            Assert.fail("Cannot work with element " +e);
            printErrorAndStopTest(e);
        }


    }
    /**
     * Method for work with drop down.
     * Finding necessary using SelectValue
     * @param webElement
     * @param value
     */
    public void selectValueInDD(WebElement webElement,String value){
        try{
            Select select=new Select(webElement);
            select.selectByValue(value);

        }catch (Exception e){
//            logger.error("Cannot work with element " +e);
//            Assert.fail("Cannot work with element " +e);
            printErrorAndStopTest(e);
        }
    }

    /**
     * Method which imitation work drop down from user position
     * @param webElement
     * @param text
     * @param optionText
     */
    public void imitationUserWorkWithDD(WebElement webElement, String text, WebElement optionText){
        try{
           webElement.click();
           logger.info("element was found "+webElement+" and click ");
           findTextInDD(optionText,text);
           logger.info(text+"Element was selected from DD List ");
           clickOnElement(webElement);

        }
        catch (Exception e){
            printErrorAndStopTest(e);
        }


    }

    private void findTextInDD(WebElement webElement,String text) {
        try{
            if (webElement.getText().equals(text)){
                webElement.click();
                logger.info("");
            }
        }
        catch (Exception e){printErrorAndStopTest(e);}
    }

    private boolean findWebElement(WebElement webElement) {
       try {
            logger.info("block findWebElement ");
           Assert.assertTrue(webElement.isDisplayed());
         return true;
       }
       catch (Exception e){
           printErrorAndStopTest(e);
    }return false;

    }

    public void printErrorAndStopTest(Exception exception){
        logger.error("Cannot work with element  " +exception);
        Assert.fail("Cannot work with element  " +exception);
    }

    public boolean isElemetDisplayed(By by) {
        try{
            logger.info("Method : isElementDisplayed");
       return isElemetDisplayed(webDriver.findElement(by));
        }catch (Exception e){}
        return false;
    }

    /**
     * Set needed state
     * @param element
     * @param state (Only !!!!check or uncheck)
     */
    public void setNeededStateToCheckBox(WebElement element,String state){
        boolean checkState=state.toLowerCase().equals("check");
        boolean unCheckState=state.toLowerCase().equals("uncheck");
        if (checkState|| unCheckState){
            if(element.isSelected()&&checkState){
                logger.info("Checkbox is already checked");
            }else if(element.isSelected() && unCheckState){
                clickOnElement(element);

            }else if(!element.isSelected() && checkState){
                clickOnElement(element);
            }else  if (!element.isSelected()&& unCheckState){
                logger.info("Checkbox is already unchecked");
            }


        }else {
            logger.error("State shoud be check or uncheck");
            Assert.fail("State shoud be check or uncheck");
        }

    }
}
