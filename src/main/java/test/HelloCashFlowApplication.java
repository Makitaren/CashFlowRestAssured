package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import com.jayway.restassured.response.Response;
import path.CashFlowPath;

public class HelloCashFlowApplication {

    protected CashFlowPath cashFlowPath = new CashFlowPath();

    @Test
    public void successPutCashFlowById() {
        Response res = cashFlowPath.getDescriptionCashFlow();
        String description = "Hello and Welcome to the CashFlow application. You can create a new CashFlow by making a POST request to /api/notes endpoint.";
        Assert.assertEquals(description, res.prettyPrint());
    }
}
