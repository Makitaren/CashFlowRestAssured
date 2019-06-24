package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.Test;
import com.jayway.restassured.response.Response;

public class HelloCashFlowApplication {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successPutCashFlowById() {
        Response res = cashFlowHelper.getDescriptionCashFlow();
        String description = "Hello and Welcome to the CashFlow application. You can create a new CashFlow by making a POST request to /api/notes endpoint.";
        Assert.assertEquals(description, res.prettyPrint());
    }
}
