package test;

import helper.CashFlowHelper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payload.CashFlowPostRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetCashFlowRaport {

    protected CashFlowHelper helper = new CashFlowHelper();

    protected List<Map<String, Float>> actualReports;
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

        Map<String, Float> map = helper.getCashFlowRaport().body().jsonPath().getMap("");
        actualReports.add(map);
        id.add(Long.parseLong(helper.postCreateCashFlow(expectedReports.get(0)).getHeader("Location").substring(35)));

        Map<String, Float> map2 = helper.getCashFlowRaport().body().jsonPath().getMap("");
        actualReports.add(map2);
        id.add(Long.parseLong(helper.postCreateCashFlow(expectedReports.get(1)).getHeader("Location").substring(35)));

        Map<String, Float> map3 = helper.getCashFlowRaport().body().jsonPath().getMap("");
        actualReports.add(map3);
        id.add(Long.parseLong(helper.postCreateCashFlow(expectedReports.get(2)).getHeader("Location").substring(35)));

        Map<String, Float> map4 = helper.getCashFlowRaport().body().jsonPath().getMap("");
        actualReports.add(map4);
        id.add(Long.parseLong(helper.postCreateCashFlow(expectedReports.get(3)).getHeader("Location").substring(35)));
    }

    @Test
    public void successGetCashFlowRaport() {
        Map<String, Float> actual = helper.compareMaps(actualReports.get(0), actualReports.get(1));
        Map<String, Float> actual1 = helper.compareMaps(actualReports.get(1), actualReports.get(2));
        Map<String, Float> actual2 = helper.compareMaps(actualReports.get(2), actualReports.get(3));

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
        helper.deleteCashFlowById(id.get(0));
        helper.deleteCashFlowById(id.get(1));
        helper.deleteCashFlowById(id.get(2));
        helper.deleteCashFlowById(id.get(3));
    }

}
