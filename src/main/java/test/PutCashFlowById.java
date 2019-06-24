package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.Test;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

public class PutCashFlowById {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successPutCashFlowById() {
        Long id = cashFlowHelper.getExistCashFlowId(cashFlowHelper.getListCashFlows());

        CashFlowPostRequest cashFlowPostRequest = cashFlowHelper.createCashFlowRequest(
                cashFlowHelper.getRandomAmount(), cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate());

        cashFlowHelper.putCashFlowById(cashFlowPostRequest, id);

        CashFlowGetResponse cashFlowGetResponse = cashFlowHelper.getCashFlowById(id).getBody().as(CashFlowGetResponse.class);

        Assert.assertEquals("Amount:", cashFlowPostRequest.getAmount().toString(), cashFlowGetResponse.getAmount().toString());
        Assert.assertEquals("Date:", cashFlowPostRequest.getDate(), cashFlowGetResponse.getDate());
        Assert.assertEquals("Description:", cashFlowPostRequest.getDescription(), cashFlowGetResponse.getDescription());
    }
}
