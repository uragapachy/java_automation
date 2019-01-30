package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SparePage extends ParentPage {
    EditSparePage editSparePage;

    @FindBy(xpath = "//table//tr")
    List<WebElement> listRows;
    @FindBy(xpath = ".//*[@class='fa fa-plus']")
    WebElement buttonAdd;



    public SparePage(WebDriver webDriver) {
        super(webDriver, "/dictionary/spares");
        editSparePage=new EditSparePage(webDriver);
    }

    @Deprecated
    public void checkAndDeleteSpare(String spareName) {
        EditSparePage editSparePage=new EditSparePage(webDriver);
        if (listRows.size() > 0) {


        for (WebElement line:listRows){
            WebElement cellsWithSpare=line.findElement(By.xpath("//*[1]"));
        if (cellsWithSpare.getText().equals(spareName)){
            actionWithOurElements.clickOnElement(cellsWithSpare);
            logger.info("Spare Page");
            logger.info("Element was delete");
            Assert.assertTrue("Diff Spare",editSparePage.checkSpareNameInIntup(spareName));
            editSparePage.clickButtonDelete();

        }

      }

        }
}


    public void clickOnAddButton() {
        actionWithOurElements.clickOnElement(buttonAdd);
    }

    public void deletingSpareUntilPresent(String spareName) {
     int counter=0;
     while (isSperInList(spareName)){
         clickOnSpareName(spareName);
         editSparePage.clickButtonDelete();
         counter++;
         if(counter>100){
             Assert.fail("There are more than 100 spare in list "+
                     " or deleting does not work , so test does not go further");
         }
     }
    }

    public boolean isSperInList(String spareName) {
        logger.info(" Method isSperInList");
        return actionWithOurElements.isElemetDisplayed(By.xpath(".//*[text()='"+spareName+"']"));
    }

    private void clickOnSpareName(String spareName) {
        logger.info(" Method clickOnSpareName");
        actionWithOurElements.clickOnElement(getSpareWithName(spareName));
    }

    private WebElement getSpareWithName(String spareName) {
        return webDriver.findElement(By.xpath(".//*[text()='"+spareName+"']"));
    }
}
