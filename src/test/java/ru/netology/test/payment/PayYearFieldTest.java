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

public class PayYearFieldTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");}

        @BeforeEach
        void setUp () {
            open("http://localhost:8080/");
        }


        @Test
        public void shouldFailurePaymentIfYearZero () {
            mainPage.payWithCard();
            val cardData = getInvalidYearIfZero();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldExpiredDatePassNotification();
        }

        @Test
        public void shouldFailurePaymentIfYearInTheFarFuture () {
            mainPage.payWithCard();
            val cardData = getInvalidYearIfInTheFarFuture();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldInvalidExpiredDateNotification();
        }

        @Test
        public void shouldFailurePaymentIfYearOneDigit () {
            mainPage.payWithCard();
            val cardData = getInvalidNumberOfYearIfOneDigit();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldImproperFormatNotification();
        }

        @Test
        public void shouldFailurePaymentIfYearBeforeCurrentYear () {
            mainPage.payWithCard();
            val cardData = getInvalidYearIfBeforeCurrentYear();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldExpiredDatePassNotification();
        }


        @Test
        public void shouldFailurePaymentIfEmptyYear () {
            mainPage.payWithCard();
            val cardData = getInvalidYearIfEmpty();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldEmptyFieldNotification();
        }

    }

