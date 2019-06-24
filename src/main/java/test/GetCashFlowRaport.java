package test;

import helper.CashFlowHelper;
import org.junit.Test;
import payload.RaportResponse;

import java.util.Map;

public class GetCashFlowRaport {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successGetCashFlowRaport() {
        RaportResponse raport = cashFlowHelper.getCashFlowRaport().getBody().as(RaportResponse.class);
        cashFlowHelper.postCreateCashFlow(cashFlowHelper.createCashFlowRequest(
                cashFlowHelper.getRandomAmount(), cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate()));

        RaportResponse newRaport = cashFlowHelper.getCashFlowRaport().getBody().as(RaportResponse.class);
        Map<String, Double> s1 = raport.getRaport();
        Map<String, Double> s2 = newRaport.getRaport();

    }
}
