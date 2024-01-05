package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class WebInterfaceTest {

    @Test
    void shouldSendRequestV1() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Барановская Кристина");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV2() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Ю Мэй");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV3() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Барановская Анна-Кристина");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV4() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Барановская Анна Кристина");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV5() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Барановская-Мищук Анна Кристина");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV6() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Барановская-Мищук Анна-Кристина");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV7() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Барановская Мищук Анна Кристина");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV8() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Барановский Марк");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSendRequestV9() {  // баг
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Смирнова Алёна");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotSendRequestWhenFormNotWrite() {
        open("http://localhost:9999");
        $(".button").click();
        SelenideElement name = $("[data-test-id=name]");
        name.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotSendRequestWhenPhoneInvalidV1() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Кристина Мищук");
        form.$("[data-test-id=phone] input").setValue("+7921181866");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneInvalidV2() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Кристина Мищук");
        form.$("[data-test-id=phone] input").setValue("7921181866");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneInvalidV3() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Кристина Мищук");
        form.$("[data-test-id=phone] input").setValue("+0000000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneInvalidV4() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Кристина Мищук");
        form.$("[data-test-id=phone] input").setValue("мобильный");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneInvalidV5() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Кристина Мищук");
        form.$("[data-test-id=phone] input").setValue("mobile");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneInvalidV6() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Кристина Мищук");
        form.$("[data-test-id=phone] input").setValue("!@#$%^&*()_");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenPhoneInvalidV7() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Кристина Мищук");
        form.$("[data-test-id=phone] input").setValue("+792118186655");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotSendRequestWhenNameInvalidV1() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("О'Хара Скарлетт");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id=name]");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendRequestWhenNameInvalidV2() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Hemsworth Chris");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id=name]");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendRequestWhenNameInvalidV3() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("!@#$%^&*()_+");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id=name]");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotSendRequestWhenNameInvalidV4() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("1234567890");
        form.$("[data-test-id=phone] input").setValue("+79211818665");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        SelenideElement name = $("[data-test-id=name]");
        name.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}
