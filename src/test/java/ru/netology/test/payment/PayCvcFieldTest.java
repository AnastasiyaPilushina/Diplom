package ru.netology.test.payment;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class PayCvcFieldTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {

        open("http://localhost:8080/");
    }



    @Test
    public void shouldFailurePaymentIfEmptyCvc() {
        mainPage.payWithCard();
        val cardData = getInvalidCvcIfEmpty();
        paymentPage.fillCardData(cardData);
        final ElementsCollection fieldSub = $$(".input__sub");
        final SelenideElement cvvFieldSub = fieldSub.get(2);
        cvvFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFailurePaymentIfCvcOneDigit() {
        mainPage.payWithCard();
        val cardData = getInvalidCvcIfOneDigit();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }
    @Test
    public void shouldFailurePaymentIfCvcTwoDigits() {
        mainPage.payWithCard();
        val cardData = getInvalidCvcIfTwoDigits();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCvvThreeZero() {
        mainPage.payWithCard();
        val cardData = getInvalidCvvIfThreeZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }


}
