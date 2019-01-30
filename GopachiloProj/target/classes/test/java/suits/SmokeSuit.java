package suits;

import login.LoginNew;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import spares.AddNewSpare;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginNew.class,
                AddNewSpare.class
        }
        )

public class SmokeSuit {
}
