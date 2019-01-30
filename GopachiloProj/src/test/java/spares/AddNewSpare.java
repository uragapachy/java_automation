package spares;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import parentTest.ParentTest;

public class AddNewSpare extends ParentTest {
    String spareName="gapachiloSpare";

    @Test

    public  void addNewSpare(){
      loginPage.validLoginInTOApp();
      homePage.checkUrl();
      homePage.clickMenuDictionary();
      homePage.clickOnSubMenuSpare();
      sparePage.checkUrl();
//      sparePage.checkAndDeleteSpare(spareName);
      sparePage.deletingSpareUntilPresent(spareName);
      sparePage.clickOnAddButton();

      editSparePage.clickButtonCreate();



      editSparePage.enterSpareNameInTOInput(spareName);
      editSparePage.selectSpareTypeInDD("Механика1");
      editSparePage.clickButtonCreate();

        checkExpectedresult("Spare was not added",sparePage.isSperInList(spareName));

    }
    @After
    public void spareDeleting(){
        sparePage.deletingSpareUntilPresent(spareName);
    }
}
