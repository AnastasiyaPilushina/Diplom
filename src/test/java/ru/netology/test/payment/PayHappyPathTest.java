package ru.netology.test.payment;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DbHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.DataHelper.getApprovedCard;
import static ru.netology.data.DataHelper.getDeclinedCard;
import static ru.netology.data.DbHelper.*;

public class PayHappyPathTest {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUp() {

        open("http://localhost:8080/");
    }

    @BeforeEach
    void setUpForPayWithCard() {

        mainPage.payWithCard();
    }

    @Test
    public void shouldSuccessPayIfValidApprovedCards() {
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldSuccessNotification();

        assertEquals("APPROVED", new DbHelper().getPaymentStatus());
        assertEquals(4500000, new DbHelper().getPaymentAmount());
        assertNull(new DbHelper().getCreditId());
    }

        @Test
        public void shouldFailurePayIfValidDeclinedCards () {
            val cardData = getDeclinedCard();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldFailureNotification();

            assertEquals("DECLINED", new DbHelper().getPaymentStatus());
            assertNull(new DbHelper().getCreditId());
        }
    }
