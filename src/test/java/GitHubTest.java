import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class GitHubTest {
    Steps steps = new Steps();

    @Test
    public void checkIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.open("https://github.com/");
        $x("//input[@placeholder='Search GitHub']").setValue("Selenide").sendKeys(Keys.ENTER);
        $x("//a[contains(text(),'selenide')]").click();
        $x("//a[@id='issues-tab']").click();
        $x("//a[@id='issue_2268_link']").shouldHave(Condition.text("Condition with "));
    }

    @Test
    public void checkIssueLambda() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            Selenide.open("https://github.com/");
        });

        step("Ищем репозиторий", () -> {
            $x("//input[@placeholder='Search GitHub']").setValue("Selenide").sendKeys(Keys.ENTER);
        });

        step("Кликаем на репозиторий", () -> {
            $x("//a[contains(text(),'selenide')]").click();
        });

        step("Открываем вклдаку issue", () -> {
            $x("//a[@id='issues-tab']").click();
        });

        step("Проверяем issue с названием Condition with", () -> {
            $x("//a[@id='issue_2268_link']").shouldHave(Condition.text("Condition with "));
        });

    }

    @Test
    public void checkIssueSteps() {
        steps.openGithub()
                .searchRepo()
                .clickRepoLink()
                .openIssueTab()
                .checkFirstResult();
    }
}
