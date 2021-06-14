package ru.netology.test.payment;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;
import ru.netology.test.TestBase;

import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.DataHelper.*;

public class PayCvcFieldTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }



    @Test
    public void shouldFailurePaymentIfEmptyCvc() {
        val cardData = getInvalidCvcIfEmpty();
        paymentPage.fillCardData(cardData);
        final ElementsCollection fieldSub = $$(".input__sub");
        final SelenideElement cvvFieldSub = fieldSub.get(2);
        cvvFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFailurePaymentIfCvcOneDigit() {
        val cardData = getInvalidCvcIfOneDigit();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }
    @Test
    public void shouldFailurePaymentIfCvcTwoDigits() {
        val cardData = getInvalidCvcIfTwoDigits();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCvvThreeZero() {
        val cardData = getInvalidCvvIfThreeZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldImproperFormatNotification();
    }


}
