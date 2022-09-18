package ru.netology.web.page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferCard {
  private SelenideElement amountTransfer = $("[data-test-id=amount] input");
  private SelenideElement fromTransfer = $("[data-test-id=from] input");
  private SelenideElement transferButton = $("[data-test-id=action-transfer] .button__text");
  private SelenideElement errorMessage = $("[data-test-id=error-notification]");

  public DashboardPage valueTransfer(DataHelper.CardName info, int value) {
    amountTransfer.setValue(String.valueOf(value));
    fromTransfer.setValue(info.getNumber());
    transferButton.click();
    return new DashboardPage();
  }
//  public void messageError() {
//    errorMessage.shouldHave(exactText("Недостаточно средств на счете")).shouldBe(visible);
//  }
}
