package test.cashFlow;

import helper.CashFlowHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.CashFlowPath;
import payload.CashFlowPostRequest;

public class DeleteCashFlowById {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();
    protected CashFlowPath c = new CashFlowPath();

    private Long id = 0L;

    @BeforeEach
    public void preparationForTest() {
        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(
                cashFlowHelper.getRandomAmount(), cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate());

        Long id = Long.parseLong(c.postCreateCashFlow(cashFlowPostRequest).getHeader("Location").substring(35));

        this.id = id;
    }

    @Test
    public void successDeleteCashFlowById() {
        c.deleteCashFlowById(id);

        c.getCashFlowById(id).then().statusCode(404);
    }
}
