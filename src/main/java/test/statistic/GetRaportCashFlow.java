package test.statistic;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.CashFlowPath;
import payload.CashFlowPostRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetRaportCashFlow {
    protected CashFlowHelper helper = new CashFlowHelper();
    protected CashFlowPath c = new CashFlowPath();

    protected List<Map<String, Double>> actualReports;
    protected List<CashFlowPostRequest> expectedReports;
    protected List<Long> id;

    @BeforeEach
    public void preparationForTest() {
        actualReports = new ArrayList<>();
        expectedReports = new ArrayList<>();
        id = new ArrayList<>();

        expectedReports.add(helper.createCashFlowRequest(helper.getRandomAmount(), helper.getRandomDescription(), helper.getRandomDate()));
        expectedReports.add(helper.createCashFlowRequest(helper.getRandomAmount(), helper.getRandomDescription(), helper.getRandomDate()));
        expectedReports.add(helper.createCashFlowRequest(helper.getRandomAmount(), helper.getRandomDescription(), helper.getRandomDate()));
        expectedReports.add(helper.createCashFlowRequest(helper.getRandomAmount(), helper.getRandomDescription(), helper.getRandomDate()));

        Map<String, String> map = c.getCashFlowRaport().body().jsonPath().getMap("");
        System.out.println("dupa" + map);
//        actualReports.add(helper.convertStringToDoubleInMap(map));

        id.add(Long.parseLong(c.postCreateCashFlow(expectedReports.get(0)).getHeader("Location").substring(35)));

        Map<String, String> map2 = c.getCashFlowRaport().body().jsonPath().getMap("");
        System.out.println("dupa" + map2);
//        actualReports.add(helper.convertStringToDoubleInMap(map2));

        id.add(Long.parseLong(c.postCreateCashFlow(expectedReports.get(1)).getHeader("Location").substring(35)));

        Map<String, String> map3 = c.getCashFlowRaport().body().jsonPath().getMap("");
//        actualReports.add(helper.convertStringToDoubleInMap(map3));

        id.add(Long.parseLong(c.postCreateCashFlow(expectedReports.get(2)).getHeader("Location").substring(35)));

        Map<String, String> map4 = c.getCashFlowRaport().body().jsonPath().getMap("");
//        actualReports.add(helper.convertStringToDoubleInMap(map4));

        id.add(Long.parseLong(c.postCreateCashFlow(expectedReports.get(3)).getHeader("Location").substring(35)));
    }

    @Test
    public void successGetCashFlowRaport() {
        Map<String, Double> actual = helper.compareMaps(actualReports.get(0), actualReports.get(1));
        Map<String, Double> actual1 = helper.compareMaps(actualReports.get(1), actualReports.get(2));
        Map<String, Double> actual2 = helper.compareMaps(actualReports.get(2), actualReports.get(3));

        System.out.println(expectedReports.get(0).getAmount() + "=" + actual.get(expectedReports.get(0).getDescription()));
        Assert.assertEquals("1",
                expectedReports.get(0).getAmount(),
                actual.get(expectedReports.get(0).getDescription()),
                0.001);

        Assert.assertEquals("2",
                expectedReports.get(1).getAmount(),
                actual1.get(expectedReports.get(1).getDescription()),
                0.001);

        Assert.assertEquals("3",
                expectedReports.get(2).getAmount(),
                actual2.get(expectedReports.get(2).getDescription()),
                0.001);
    }

    @AfterEach
    public void cleanAfterTest() {
        c.deleteCashFlowById(id.get(0));
        c.deleteCashFlowById(id.get(1));
        c.deleteCashFlowById(id.get(2));
        c.deleteCashFlowById(id.get(3));
    }
}
