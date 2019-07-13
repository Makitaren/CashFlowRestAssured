package test;

import helper.CashFlowHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payload.CashFlowGetResponse;

import java.util.List;

public class PostCashFlowByFile {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();

    String path;
    Long id = 1L;
    int sizeBefore = 1;
    int sizeAfter;

    @BeforeEach
    public void preparationForTest() {
        path = "/home/makitaren/Desktop/TestFile.csv";
        List<CashFlowGetResponse> cashFlows = cashFlowHelper.getListCashFlows();

        id = Long.parseLong(cashFlowHelper.postCreateCashFlow(cashFlowHelper.createCashFlowRequest(cashFlowHelper.getRandomAmount(),
                cashFlowHelper.getRandomDescription(), cashFlowHelper.getRandomDate())).getHeader("Location").substring(35));
    }

    @Test
    public void successPostCashFlowByFile() {

        cashFlowHelper.postCashFlowByFile(path);
        sizeAfter = cashFlowHelper.getListCashFlows().size();

    }

    @AfterEach
    public void cleanAfterTest() {

        int sizediff = sizeAfter - sizeBefore;
        for (Long i = id; i <= id + sizediff; i++) {
            System.out.println(i);
            cashFlowHelper.deleteCashFlowById(i);
        }
    }
}
