package ru.netology.test.purchaseOnCredit;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.DataHelper.getInvalidNumberOfMonthIfZero;

public class CreditPayNumberOfMonthFieldTest {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }


    @Test
    public void shouldFailurePaymentIfEmptyNumberOfMonth() {
        mainPage.payWithCredit();
        val cardData = getInvalidMonthIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfMore12() {
        mainPage.payWithCredit();
        val cardData = getInvalidNumberOfMonthIfMore12();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfOneDigit() {
        mainPage.payWithCredit();
        val cardData = getInvalidNumberOfMonthIfOneDigit();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthZero() {
        mainPage.payWithCredit();
        val cardData = getInvalidNumberOfMonthIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }

}
