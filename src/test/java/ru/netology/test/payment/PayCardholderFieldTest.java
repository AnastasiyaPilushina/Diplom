package ru.netology.test.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;
import ru.netology.test.TestBase;


import static ru.netology.data.DataHelper.*;

public class PayCardholderFieldTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCardholderName() {
        val cardData = getInvalidCardOwnerNameIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameNumeric() {
        val cardData = getInvalidCardOwnerNameIfNumericAndSpecialCharacters();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameRussianLetters() {
        val cardData = getInvalidCardOwnerNameIfRussianLetters();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }



}


