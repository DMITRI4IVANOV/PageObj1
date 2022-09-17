package ru.netology.web.test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransferCard;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

  @BeforeEach
  void from() {
    open("http://localhost:9999");
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    loginPage.validLogin(authInfo).validVerify(verificationCode);
  }
  @Test
  void shouldTransferMoneyBetweenOwnCards1() {
    int value = 1000;
    var donorInfo = DataHelper.getSecondCartNumber();

    var dashboardPage = new DashboardPage();
    int firstBalanceFirstCard = dashboardPage.getCardBalance("0");
    int firstBalanceSecondCard = dashboardPage.getCardBalance("1");

    dashboardPage.changeCard(0).valueTransfer(donorInfo, value);
    int secondBalanceFirstCard = dashboardPage.getCardBalance("0");
    int secondBalanceSecondCard = dashboardPage.getCardBalance("1");

    assertEquals(secondBalanceFirstCard, firstBalanceFirstCard + value);
    assertEquals(secondBalanceSecondCard, firstBalanceSecondCard - value);
  }

  @Test
  void shouldTransferMoneyBetweenOwnCards2() {
    int value = 3000;
    var donorInfo = DataHelper.getFirstCartNumber();

    var dashboardPage = new DashboardPage();
    int firstBalanceFirstCard = dashboardPage.getCardBalance("0");
    int firstBalanceSecondCard = dashboardPage.getCardBalance("1");

    dashboardPage.changeCard(1).valueTransfer(donorInfo, value);
    int secondBalanceFirstCard = dashboardPage.getCardBalance("0");
    int secondBalanceSecondCard = dashboardPage.getCardBalance("1");

    assertEquals(secondBalanceFirstCard, firstBalanceFirstCard - value);
    assertEquals(secondBalanceSecondCard, firstBalanceSecondCard + value);
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsNoValid() {
    int value = 40000;
    var donorInfo = DataHelper.getFirstCartNumber();

    var dashboardPage = new DashboardPage();
    int firstBalanceFirstCard = dashboardPage.getCardBalance("0");
    int firstBalanceSecondCard = dashboardPage.getCardBalance("1");

    dashboardPage.changeCard(1).valueTransfer(donorInfo, value);
    int secondBalanceFirstCard = dashboardPage.getCardBalance("0");
    int secondBalanceSecondCard = dashboardPage.getCardBalance("1");

    var moneyTransferCard = new MoneyTransferCard();
    moneyTransferCard.messageError();
  }

}

