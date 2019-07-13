package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

public class PutCashFlowById {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    private Long id = 0L;
    private Double amount = 0D;
    private String description = "";
    private String date = "";

    private Double amount2 = 0D;
    private String description2 = "";
    private String date2 = "";

    @BeforeEach
    public void preparationForTest() {
        this.amount = cashFlowHelper.getRandomAmount();
        this.description = cashFlowHelper.getRandomDescription();
        this.date = cashFlowHelper.getRandomDate();

        this.amount2 = cashFlowHelper.getAnotherRandomAmount(amount);
        this.description2 = cashFlowHelper.getAnotherRandomDescription(description);
        this.date2 = cashFlowHelper.getAnotherRandomDate(date);

        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(amount, description, date);

        Long id = Long.parseLong(cashFlowHelper.postCreateCashFlow(cashFlowPostRequest).getHeader("Location").substring(35));

        this.id = id;
    }

    @Test
    public void successPutCashFlowById() {
        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(
                amount2, description2, date2);

        cashFlowHelper.putCashFlowById(cashFlowPostRequest, id);

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
