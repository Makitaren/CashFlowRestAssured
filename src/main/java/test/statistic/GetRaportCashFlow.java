package test.statistic;

import helper.CashFlowHelper;
import helper.StatisticHelper;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.CashFlowPath;
import path.StatisticPath;
import payload.CashFlowPostRequest;

import java.util.List;
import java.util.Map;

public class GetRaportCashFlow {
    protected CashFlowHelper cashFlowHelper = new CashFlowHelper();
    protected StatisticPath statisticPath = new StatisticPath();
    protected CashFlowPath cashFlowPath = new CashFlowPath();
    protected StatisticHelper statisticHelper = new StatisticHelper();

    protected List<Pair<Long, CashFlowPostRequest>> postCashFlow;

    protected List<Map<String, Double>> added;
    protected List<Map<String, Float>> before;
    protected List<Map<String, Float>> actual;
    protected List<Map<String, Double>> diff;

    @BeforeEach
    public void preparationForTest() {
        before = statisticPath.getCashFlowRaport().body().jsonPath().getList("");
        postCashFlow = cashFlowHelper.createCashFlowsPairs(10);

        actual = statisticPath.getCashFlowRaport().body().jsonPath().getList("");
        added = statisticHelper.getRaportByPeriodss(postCashFlow);
    }

    @Test
    public void successGetCashFlowRaport() {

        diff = cashFlowHelper.compareListMaps(cashFlowHelper.convertStringToDoubleInMap(before), cashFlowHelper.convertStringToDoubleInMap(actual));

        Assert.assertEquals(
                added,
                diff);

    }

    @AfterEach
    public void cleanAfterTest() {
        postCashFlow.forEach((k) -> cashFlowPath.deleteCashFlowById(k.getKey()));
    }
}
