package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class GameStep {
    private final SelenideElement closeWindowButton = $x("//input[@value='Закрыть']");
    private final SelenideElement startGameButton = $x("//a[@id='host_start']");
    private final SelenideElement highlightWord = $x("//span[@id='typefocus']");
    private final SelenideElement inputField = $x("//input[@id='inputtext']");
    private final SelenideElement afterFocusWord = $x("//span[@id='afterfocus']");
    private final SelenideElement resultText =
            $x("//td[text()='Это вы']//ancestor-or-self::div//div[@class='stats']//div[2]/span/span");

    private String getCurrentWord() {
        return highlightWord.getText().replaceAll("c", "с").
                replaceAll("o", "о");
    }

    @When("Let's start the game")
    public void startGame() {
        closeWindowButton.click();
        if (startGameButton.isDisplayed()) {
            startGameButton.click();
        }
    }

    @And("We are waiting for the start of the game")
    public void weAreWaitingForTheStartOfTheGame() {
        highlightWord.click();
    }

    @And("Enter the highlighted word in a loop")
    public void enterTheHighlightedWordInALoop() {
        while (true) {
            String currentWord = getCurrentWord();
            String afterFocusSymbol = afterFocusWord.getText();
            inputField.sendKeys(currentWord);
            if (afterFocusSymbol.equals(".")) {
                inputField.sendKeys(".");
                break;
            }
            inputField.sendKeys(Keys.SPACE);
        }

    }

    @Then("We fix that the game is over and there are more than {int} symbols per minute")
    public void weFixThatTheGameIsOverAndThereAreMoreThanSymbolsPerMinute(int value) {
        String result = resultText.getText();
        int resultNumber = Integer.parseInt(result);
        System.out.println("Number of characters per minute: " + resultNumber);
        Assertions.assertTrue(resultNumber > value, "Actual result: " + resultNumber);

    }

}
