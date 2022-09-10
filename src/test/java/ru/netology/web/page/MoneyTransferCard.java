package ru.netology.web.page;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferCard {
  private SelenideElement amountTransfer = $("[data-test-id=amount] input");
  private SelenideElement fromTransfer = $("[data-test-id=from] input");
  private SelenideElement transferButton = $("[data-test-id=action-transfer] .button__text");

  public DashboardPage valueTransfer (DataHelper.CardName info, int value) {
    amountTransfer.setValue(String.valueOf(value));
    fromTransfer.setValue(info.getNumber());
    transferButton.click();
    return new DashboardPage();
  }
}
