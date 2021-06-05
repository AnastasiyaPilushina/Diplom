package ru.netology.test.payment;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    public void shouldSuccessPayIfValidApprovedCards() {
        mainPage.payWithCard();
        val cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldSuccessNotification();

        assertEquals("APPROVED", new DbHelper().getPaymentStatus());
        assertEquals(4500000, new DbHelper().getPaymentAmount());
        assertNull(new DbHelper().getCreditId());
    }

        @Test
        public void shouldFailurePayIfValidDeclinedCards () {
            mainPage.payWithCard();
            val cardData = getDeclinedCard();
            paymentPage.fillCardData(cardData);
            paymentPage.shouldFailureNotification();

            assertEquals("DECLINED", new DbHelper().getPaymentStatus());
            assertNull(new DbHelper().getCreditId());
        }
    }
