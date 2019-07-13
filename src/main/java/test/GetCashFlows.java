package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import payload.CashFlowPostRequest;

public class GetCashFlows {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    private Long id = 0L;
    private Long id2 = 0L;
    private Double amount = 0D;
    private String description = "";
    private String date = "";

    @BeforeEach
    public void preparationForTest() {
        this.amount = cashFlowHelper.getRandomAmount();
        this.description = cashFlowHelper.getRandomDescription();
        this.date = cashFlowHelper.getRandomDate();

        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(amount, description, date);

        Long id = Long.parseLong(cashFlowHelper.postCreateCashFlow(cashFlowPostRequest).getHeader("Location").substring(35));

        this.id = id;
    }

    @Test
    public void successGetCashFlows() {
        int sizeBefore = cashFlowHelper.getListCashFlows().size();
        id2 = Long.parseLong(cashFlowHelper.postCreateCashFlow(cashFlowHelper.createCashFlowRequest(
                cashFlowHelper.getRandomAmount(), cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate())).getHeader("Location").substring(35));
        int sizeAfter = cashFlowHelper.getListCashFlows().size();



        Assert.assertEquals("Size:", sizeBefore + 1, sizeAfter);
    }

    @AfterEach
    public void cleanAfterTest() {
        cashFlowHelper.deleteCashFlowById(id);
        cashFlowHelper.deleteCashFlowById(id2);
    }

}
