package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.Test;

public class GetCashFlows {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successGetCashFlows() {
        int sizeBefore = cashFlowHelper.getListCashFlows().size();
        cashFlowHelper.postCreateCashFlow(cashFlowHelper.createCashFlowRequest(
                cashFlowHelper.getRandomAmount(), cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate()));
        int sizeAfter = cashFlowHelper.getListCashFlows().size();

        Assert.assertEquals("Size:", sizeBefore + 1, sizeAfter);
    }
}
