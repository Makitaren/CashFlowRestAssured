package test;

import helper.CashFlowHelper;
import org.junit.Test;

import java.text.ParseException;

public class PostCashFlow {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void succesCreateCashFlow() throws ParseException {
        cashFlowHelper.PostCerateCashFlow(cashFlowHelper.createCashFlowRequest());
    }
}
