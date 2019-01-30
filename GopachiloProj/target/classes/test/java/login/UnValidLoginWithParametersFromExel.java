package login;

import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class UnValidLoginWithParametersFromExel extends ParentTest {
    String login="89898";
    String password="090";

    public UnValidLoginWithParametersFromExel(String login, String password) {
        this.login = login;
        this.password = password;
    }
//    @Parameterized.Parameters(name="Parameters are {0} and {1}")
//    public  static Collection testData(){
//        return Arrays.asList(new Object[][]{
//                {"student","906090"},
//                {"Login","909090"},
//                {"Login","Pass"}
//
//        });
//
//
//    }
    @Parameterized.Parameters(name="Parameters are {0} and {1}")
  public static Collection testDAta()throws IOException{
        InputStream spreadSheet=new FileInputStream(configProperties.DATA_FILE_PATH()+"testDataSuit.xls");
        return  new SpreadsheetData(spreadSheet,"InvalidLogOn").getData();
    }
    @Test
    public void unValidLoginWithParameters(){
        loginPage.loginInToApp(login,password);
        checkExpectedresult("Avatar shoud not be present",homePage.isAvatarDisplayed(),false);

    }
}
