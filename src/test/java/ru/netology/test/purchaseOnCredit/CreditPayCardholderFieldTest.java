package ru.netology.test.purchaseOnCredit;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class CreditPayCardholderFieldTest {

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUp() {

        open("http://localhost:8080/");
    }



    @Test
    public void shouldFailurePaymentIfEmptyCardholderName() {
        mainPage.payWithCredit();
        val cardData = getInvalidСardownerNameIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameNumeric() {
        mainPage.payWithCredit();
        val cardData = getInvalidCardownerNameIfNumericAndSpecialCharacters();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameRussianLetters() {
        mainPage.payWithCredit();
        val cardData = getInvalidСardownerNameIfRussianLetters();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }


}
