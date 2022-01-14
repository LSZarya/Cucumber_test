package Leson_cucumber_tho;


import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;


@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = "Leson_cucumber_tho"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    public static EventFiringWebDriver driver;
    protected WebDriverWait wait;


    @BeforeTest
    public void setUp(ITestContext context) {
        driver = new EventFiringWebDriver(setupDriver("CHROME"));
        driver.register(new MarkBeforeClickListener());

        context.setAttribute("driver", driver);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void teamDown() {
        driver.quit();
    }

    public static WebDriver setupDriver(String driverType) {
        switch (driverType) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "OPERA":
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            default:
                throw new WebDriverException("тип драйвера не верный");
        }
    }
}
