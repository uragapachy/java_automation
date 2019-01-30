package parentTest;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.EditSparePage;
import pages.HomePage;
import pages.LoginPage;
import pages.SparePage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ParentTest {

    WebDriver webDriver;
    String browser=System.getProperty("browser");
    protected static ConfigProperties configProperties= ConfigFactory.create(ConfigProperties.class);
    Logger logger=Logger.getLogger(getClass());
     protected LoginPage loginPage;
     protected HomePage homePage;
     protected SparePage sparePage;
    protected EditSparePage editSparePage;


    @Before
    public void setUp(){
        if ("chrome".equals(browser) || browser == null) {
            File file = new File("./src/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            webDriver = new ChromeDriver();
        }else if ("firefox".equals(browser)) {
            File file = new File("./src/drivers/geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
            FirefoxOptions profile = new FirefoxOptions();
            profile.addPreference("browser.stertup.page",0);
            profile.addPreference("browser.startup.homepage_override.mstone", "ignore"); // Suppress the "What's new" page

            webDriver = new FirefoxDriver();
        }
        else if ("remote".equals(browser)){
            logger.info("Remote Driver will be started");
            try {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.chrome();
//              desiredCapabilities.setVersion("68");
//              desiredCapabilities.setPlatform(Platform.IOS);

                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            Assert.fail("Wrong browser name");
        }


        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        sparePage = new SparePage(webDriver);
        editSparePage= new EditSparePage(webDriver);

    }

    @After
    public void tearDown() {
//        webDriver.quit();

    }
   @Step
    public void checkExpectedresult(String message,
                                    boolean actualResalt,boolean expectedResalt){
        Assert.assertEquals(message,expectedResalt,actualResalt);
    }
    public void checkExpectedresult(String message, boolean actualResalt){
        checkExpectedresult(message,actualResalt,true);
    }
    @Rule
    public TestWatcher watchman = new TestWatcher() {
        String fileName;
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }
        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }
        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        }
        @Override
        protected void finished(Description description) {
            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    };
}
