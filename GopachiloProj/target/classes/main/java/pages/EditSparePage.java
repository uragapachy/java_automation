package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditSparePage extends ParentPage{
    @FindBy(id="spares_spareName")
    private  WebElement spareNameInput;

    @FindBy(id="spares_spareType")
    private WebElement spareTypeDD;

    @FindBy(name="delete")
    private WebElement buttonDelete;

    @FindBy(name="add")
    private WebElement buttonCreate;

    @FindBy(xpath = ".//option[text()='Механика1']")
    private  WebElement optionText;








    public EditSparePage(WebDriver webDriver ) {
        super(webDriver,"/dictionary/spares/edit" );
    }

    public  boolean checkSpareNameInIntup(String spareName){
        return spareNameInput.getText().equals(spareName);
    }

     public  void clickButtonDelete(){
        actionWithOurElements.clickOnElement(buttonDelete);
     }

    public void enterSpareNameInTOInput(String spareName) {
        actionWithOurElements.enterTextIntoElement(spareNameInput,spareName);
    }

    public void clickButtonCreate() {
        actionWithOurElements.clickOnElement(buttonCreate);
    }

    public void selectSpareTypeInDD(String spareType) {
        actionWithOurElements.selectTextInDD(spareTypeDD,spareType);
    }
    public void imitationUserSelectSpareTypeInDD(String text)
    {
        logger.info("imitation User Select Spare Type In DD ");
        actionWithOurElements.imitationUserWorkWithDD(spareTypeDD,text,optionText);
    }
}
