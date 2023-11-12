import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    @Test
    void shouldApplicationIsCompletedCorrectly(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ставрополь");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys("24.11.2023");
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79200000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }


    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(12);
    SelenideElement notification = $x("//div[@data-test-id='notification']");

    @Test
    void shouldTest(){
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Ст");
        $(withText("Ставрополь")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME),Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79200000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

}
