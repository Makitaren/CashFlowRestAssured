package test;

import helper.CashFlowHelper;
import org.junit.Test;

public class PostCashFlowByFile {

    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    @Test
    public void successPostCashFlowByFile() {
        cashFlowHelper.postCashFlowByFile();
    }
}
