package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

public class PostCashFlow {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    private Long id = 0L;
    private Double amount = 0D;
    private String description = "";
    private String date = "";

    @BeforeEach
    public void preparationForTest() {
        this.amount = cashFlowHelper.getRandomAmount();
        this.description = cashFlowHelper.getRandomDescription();
        this.date = cashFlowHelper.getRandomDate();
    }
    @Test
    public void successCreateCashFlow() {
        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(amount, description, date);

        this.id = Long.parseLong(cashFlowHelper.postCreateCashFlow(cashFlowPostRequest).getHeader("Location").substring(35));

        CashFlowGetResponse cashFlowGetResponse = cashFlowHelper.getCashFlowById(id).getBody().as(CashFlowGetResponse.class);

        Assert.assertEquals("Amount:", cashFlowPostRequest.getAmount().toString(), cashFlowGetResponse.getAmount().toString());
        Assert.assertEquals("Date:", cashFlowPostRequest.getDate(), cashFlowGetResponse.getDate());
        Assert.assertEquals("Description:", cashFlowPostRequest.getDescription(), cashFlowGetResponse.getDescription());
    }

    @AfterEach
    public void cleanAfterTest() {
        cashFlowHelper.deleteCashFlowById(id);
    }
}
