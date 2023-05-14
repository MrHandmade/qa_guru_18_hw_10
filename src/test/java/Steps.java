import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class Steps {
    @Step("Открываем главную старницу ГитХаб")
    public Steps openGithub() {
        Selenide.open("https://github.com/");
        return this;
    }

    @Step("Ищем репозиторий селенида")
    public Steps searchRepo() {
        $x("//input[@placeholder='Search GitHub']").setValue("Selenide").sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Нажимаем на репозиторий селенида")
    public Steps clickRepoLink() {
        $x("//a[contains(text(),'selenide')]").click();
        return this;
    }

    @Step("Открываем issue таб")
    public Steps openIssueTab() {
        $x("//a[@id='issues-tab']").click();
        return this;
    }

    @Step("Проверяем issue с названием Condition with")
    public Steps checkFirstResult() {
        $x("//a[@id='issue_2268_link']").shouldHave(Condition.text("Condition with "));
        return this;
    }
}
