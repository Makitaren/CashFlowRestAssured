package test.cashFlow;

import com.jayway.restassured.response.Response;
import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import path.CashFlowPath;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

public class GetCashFlowById {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();
    protected CashFlowPath c = new CashFlowPath();

    private Long id = 0L;
    private Double amount = 0D;
    private String description = "";
    private String date = "";

    @BeforeEach
    public void preparationForTest() {
        this.amount = cashFlowHelper.getRandomAmount();
        this.description = cashFlowHelper.getRandomDescription();
        this.date = cashFlowHelper.getRandomDate();
        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(amount, description, date);

        Long id = Long.parseLong(c.postCreateCashFlow(cashFlowPostRequest).getHeader("Location").substring(35));

        this.id = id;
    }

    @Test
    public void successGetCashFlowById() {

        Response response = c.getCashFlowById(id);

        CashFlowGetResponse cashFlowGetResponse1 = response.as(CashFlowGetResponse.class);

        response.then().statusCode(200);
        System.out.println(response.print());

        Assert.assertEquals("Amount:", amount.toString(), cashFlowGetResponse1.getAmount().toString());
        Assert.assertEquals("Description:", description, cashFlowGetResponse1.getDescription());
        Assert.assertEquals("Date:", date, cashFlowGetResponse1.getDate());

    }

    @AfterEach
    public void cleanAfterTest() {
        c.deleteCashFlowById(id);
    }
}
