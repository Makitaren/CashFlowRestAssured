package test;

import helper.CashFlowHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payload.CashFlowPostRequest;

public class DeleteCashFlowById {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    private Long id = 0L;

    @BeforeEach
    public void preparationForTest() {
        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(
                cashFlowHelper.getRandomAmount(), cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate());

        Long id = Long.parseLong(cashFlowHelper.postCreateCashFlow(cashFlowPostRequest).getHeader("Location").substring(35));

        this.id = id;
    }

    @Test
    public void successDeleteCashFlowById() {
        cashFlowHelper.deleteCashFlowById(id);

        cashFlowHelper.getCashFlowById(id).then().statusCode(404);
    }
}
