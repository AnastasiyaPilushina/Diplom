package ru.netology.test.payment;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class PayCardholderFieldTest {
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
    public void shouldFailurePaymentIfEmptyCardholderName() {
        mainPage.payWithCard();
        val cardData = getInvalidCardOwnerNameIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameNumeric() {
        mainPage.payWithCard();
        val cardData = getInvalidCardOwnerNameIfNumericAndSpecialCharacters();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameRussianLetters() {
        mainPage.payWithCard();
        val cardData = getInvalidCardOwnerNameIfRussianLetters();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }



}


