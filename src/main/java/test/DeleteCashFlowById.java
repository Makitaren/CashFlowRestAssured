package test;

import org.junit.Test;
import helper.CashFlowHelper;

public class DeleteCashFlowById {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successDeleteCashFlowById() {
        Long id = cashFlowHelper.getExistCashFlowId(cashFlowHelper.getListCashFlows());
        cashFlowHelper.deleteCashFlowById(id);

        cashFlowHelper.getCashFlowById(id).then().statusCode(404);
    }
}
