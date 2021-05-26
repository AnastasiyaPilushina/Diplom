package ru.netology.test.purchaseOnCredit;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class CreditPayYearFieldTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }


    @Test
    public void shouldFailurePaymentIfYearZero() {
        mainPage.payWithCredit();
        val cardData = getInvalidYearIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldExpiredDatePassNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearInTheFarFuture() {
        mainPage.payWithCredit();
        val cardData = getInvalidYearIfInTheFarFuture();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearOneDigit() {
        mainPage.payWithCredit();
        val cardData = getInvalidNumberOfYearIfOneDigit();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearBeforeCurrentYear() {
        mainPage.payWithCredit();
        val cardData = getInvalidYearIfBeforeCurrentYear();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldExpiredDatePassNotification();
    }


    @Test
    public void shouldFailurePaymentIfEmptyYear() {
        mainPage.payWithCredit();
        val cardData = getInvalidYearIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

}



