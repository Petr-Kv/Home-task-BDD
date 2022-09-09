package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;

public class AfterStep {

    @After
    public void after(){
        WebDriverRunner.getWebDriver().quit();
    }
}
