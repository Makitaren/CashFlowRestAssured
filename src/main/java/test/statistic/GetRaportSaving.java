package test.statistic;

import helper.CashFlowHelper;
import javafx.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.CashFlowPath;
import path.StatisticPath;
import payload.CashFlowPostRequest;
import payload.RaportResponse;

import java.util.Arrays;
import java.util.List;

public class GetRaportSaving {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();
    protected CashFlowPath cashFlowPath = new CashFlowPath();
    protected StatisticPath statisticPath = new StatisticPath();

    List<Pair<Long, CashFlowPostRequest>> postCashFlow;//

    @BeforeEach
    public void preparationForTest() {
        List<RaportResponse> map = Arrays.asList(statisticPath.getCashFlowRaport().as(RaportResponse[].class));
        System.out.println(map);
    }

    @Test
    public void successGetCashFlowRaport() {

    }

    @AfterEach
    public void cleanAfterTest() {

    }
}
