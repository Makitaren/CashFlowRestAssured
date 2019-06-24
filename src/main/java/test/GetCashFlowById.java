package test;

import com.jayway.restassured.response.Response;
import helper.CashFlowHelper;
import org.junit.Test;

public class GetCashFlowById {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successGetCashFlowById() {

        Response res = cashFlowHelper.getCashFlowById(cashFlowHelper.getExistCashFlowId(cashFlowHelper.getListCashFlows()));

        res.then().statusCode(200);
    }
}
