package login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parentTest.ParentTest;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class UnValidLoginWithParameters extends ParentTest {
    String login="89898";
    String password="090";

    public UnValidLoginWithParameters(String login, String password) {
        this.login = login;
        this.password = password;
    }
    @Parameterized.Parameters(name="Parameters are {0} and {1}")
    public  static Collection testData(){
        return Arrays.asList(new Object[][]{
                {"student","906090"},
                {"Login","909090"},
                {"Login","Pass"}

        });


    }

    @Test
    public void unValidLoginWithParameters(){
        loginPage.loginInToApp(login,password);
        checkExpectedresult("Avatar shoud not be present",homePage.isAvatarDisplayed(),false);

    }
}
