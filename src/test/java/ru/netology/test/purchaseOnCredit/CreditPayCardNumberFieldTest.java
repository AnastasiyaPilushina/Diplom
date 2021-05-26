package ru.netology.test.purchaseOnCredit;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class CreditPayCardNumberFieldTest {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }


    @Test
    public void shouldFailurePaymentIfEmptyCardNumber() {
        mainPage.payWithCredit();
        val cardData = getInvalidCardNumberIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }
    @Test
    public void shouldFailurePaymentIfCardNumberIfLess16Sym() {
        mainPage.payWithCredit();
        val cardData = getInvalidCardNumberIfLess16Sym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCardNumberIfOutOfBase() {
        mainPage.payWithCredit();
        val cardData =getInvalidCardNumberIfOutOfDatabase();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();
    }

}
