package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.Test;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

public class PostCashFlow {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successCreateCashFlow() {
        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(
                cashFlowHelper.getRandomAmount(), cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate());

        String location = cashFlowHelper.postCreateCashFlow(cashFlowPostRequest).getHeader("Location");

        CashFlowGetResponse cashFlowGetResponse = cashFlowHelper.getCashFlowById(
                Long.parseLong(location.substring(35))).getBody().as(CashFlowGetResponse.class);

        Assert.assertEquals("Amount:", cashFlowPostRequest.getAmount().toString(), cashFlowGetResponse.getAmount().toString());
        Assert.assertEquals("Date:", cashFlowPostRequest.getDate(), cashFlowGetResponse.getDate());
        Assert.assertEquals("Description:", cashFlowPostRequest.getDescription(), cashFlowGetResponse.getDescription());
    }
}
