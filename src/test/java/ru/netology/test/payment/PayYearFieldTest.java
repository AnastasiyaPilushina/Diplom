package ru.netology.test.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;
import ru.netology.test.TestBase;

import static ru.netology.data.DataHelper.*;

public class PayYearFieldTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }


        @Test
        public void shouldFailurePaymentIfYearZero () {
            val cardData = getInvalidYearIfZero();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldExpiredDatePassNotification();
        }

        @Test
        public void shouldFailurePaymentIfYearInTheFarFuture () {
            val cardData = getInvalidYearIfInTheFarFuture();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldInvalidExpiredDateNotification();
        }

        @Test
        public void shouldFailurePaymentIfYearOneDigit () {
            val cardData = getInvalidNumberOfYearIfOneDigit();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldImproperFormatNotification();
        }

        @Test
        public void shouldFailurePaymentIfYearBeforeCurrentYear () {
            val cardData = getInvalidYearIfBeforeCurrentYear();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldExpiredDatePassNotification();
        }


        @Test
        public void shouldFailurePaymentIfEmptyYear () {
            val cardData = getInvalidYearIfEmpty();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldEmptyFieldNotification();
        }

    }

