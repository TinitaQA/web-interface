package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class WebInterfaceTest {

    @BeforeEach
    void openPage() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendRequestWhenAllDataIsValidV1() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenAllDataIsValidV2() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановский Марк");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenAllDataIsValidV3() {  // баг
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Смирнова Алёна");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenFirstNameContainsOneLetter() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Ю Мэй");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenLastNameWithDash() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская Анна-Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenLastNameWithSpace() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская Анна Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenFirstNameWithDashAndLastNameWithSpace() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская-Мищук Анна Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenFirstAndLastNameWithDash() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская-Мищук Анна-Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestWhenFirstAndLastNameWithSpace() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская Мищук Анна Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSendRequestWhenFormNotWriteFully() {
        $(".button").click();
        SelenideElement name = $("[data-test-id='name'].input_invalid");
        name.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendRequestWhenFirstAndLastNameNotWrite() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();$(".button").click();
        SelenideElement name = $("[data-test-id='name'].input_invalid");
        name.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendRequestWhenPhoneNotWrite() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская Кристина");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendRequestWhenCheckBoxNotMark() { // баг
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Барановская Кристина");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$(".button").click();$(".button").click();
        SelenideElement checkbox = $("[data-test-id='agreement'].input_invalid");
        checkbox.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendRequestWhenWriteOnlyCheckBox() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();$(".button").click();
        SelenideElement name = $("[data-test-id='name'].input_invalid");
        name.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendRequestWhenPhoneContains10Numbers() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Кристина Мищук");
        form.$("[data-test-id='phone'] input").setValue("+7921181866");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneWriteWithoutPlus() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Кристина Мищук");
        form.$("[data-test-id='phone'] input").setValue("7921181866");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneContainsPlusAnd10Zero() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Кристина Мищук");
        form.$("[data-test-id='phone'] input").setValue("+0000000000");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneWriteRus() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Кристина Мищук");
        form.$("[data-test-id='phone'] input").setValue("мобильный");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneWriteEng() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Кристина Мищук");
        form.$("[data-test-id='phone'] input").setValue("mobile");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneContainsOnlySymbols() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Кристина Мищук");
        form.$("[data-test-id='phone'] input").setValue("!@#$%^&*()_");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneContains12Symbols() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Кристина Мищук");
        form.$("[data-test-id='phone'] input").setValue("+792118186655");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id='phone'].input_invalid");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenNameWriteWithApostrophe() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("О'Хара Скарлетт");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id='name'].input_invalid");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendRequestWhenNameWriteEng() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("Hemsworth Chris");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id='name'].input_invalid");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendRequestWhenNameContainsOnlySpecialSymbols() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("!@#$%^&*()_+");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id='name'].input_invalid");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendRequestWhenNameWriteOnlyNumbers() {
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='name'] input").setValue("1234567890");
        form.$("[data-test-id='phone'] input").setValue("+79211818665");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id='name'].input_invalid");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
