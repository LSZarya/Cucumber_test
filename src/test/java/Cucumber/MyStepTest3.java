package Cucumber;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Затем;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static Cucumber.WebDriverFactory.setupDriver;

public class MyStepTest3 {

    public static EventFiringWebDriver driver;
    protected WebDriverWait wait;

    @FindBy(className = "lessons__new-item-price")
    private List<WebElement> price;

    @FindBy(css = ".header2-menu__item.header2-menu__item_dropdown")
    private List<WebElement> courses;

    @FindBy(linkText = "Подготовительные курсы")
    private WebElement preparatoryCourse;

    @FindBy(className = "lessons__new-item-title")
    private List<WebElement> nameCourse;


    @Допустим("Я открываю {string}, перехожу в раздел подготовительные курсы")
    public void openSite(String browser) {
        driver = new EventFiringWebDriver(setupDriver(browser));
        driver.register(new MarkBeforeClickListener());

        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://otus.ru/");
        PageFactory.initElements(driver, this);

        courses.get(1).click();
        Actions actions = new Actions(driver);
        actions
                .moveToElement(courses.get(1))
                .pause(1000)
                .moveToElement(preparatoryCourse)
                .pause(1000)
                .click(preparatoryCourse)
                .build()
                .perform();
    }

    @Затем("выбрать самый дорогой и самый дешевый курс")
    public void filter() {
        int max = 0;
        int courseMax = 0;
        int min = 1000000;
        int courseMin = 0;

        for (int i = 0; i < 9; i++) {
            String many = price.get(i).getText();

            int manyMany = Integer.parseInt(many.replaceAll("[^\\d.]", ""));

            if (max <= manyMany){
                courseMax = i;
            }else {
                courseMin = i;
            }
            max = Math.max(max, manyMany);

            min = Math.min(min, manyMany);

        }
        System.out.println("Самый дорогой курс стоит:" + max + " р.\n" + nameCourse.get(courseMax).getText());
        System.out.println("Самый дешевый курс стоит:" + min + " р.\n" + nameCourse.get(courseMin).getText());
        driver.quit();
    }
}
