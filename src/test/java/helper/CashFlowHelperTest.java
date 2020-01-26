package helper;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CashFlowHelperTest {
    CashFlowHelper cashFlowHelper;
    Map<String, Double> map;
    Map<String, Double> map2;
    Map<String, Double> mapDiffExpected;

    List<Pair<Long, CashFlowPostRequest>> reqList;
    List<CashFlowGetResponse> resList;

    @BeforeEach
    void initial() {


//
//        reqList = new ArrayList<>();
//
//        CashFlowPostRequest req = new CashFlowPostRequest();
//        req.setAmount(1.0);
//        req.setDescription("AAA");
//        req.setDate("2019-01-01");
//
//        CashFlowPostRequest req2 = new CashFlowPostRequest();
//        req2.setAmount(2.0);
//        req2.setDescription("BBB");
//        req2.setDate("2019-01-02");
//
//        CashFlowPostRequest req3 = new CashFlowPostRequest();
//        req3.setAmount(3.0);
//        req3.setDescription("CCC");
//        req3.setDate("2019-01-03");
//
//        CashFlowPostRequest req4 = new CashFlowPostRequest();
//        req4.setAmount(4.0);
//        req4.setDescription("FOOD");
//        req4.setDate("2019-01-04");
//
//        CashFlowPostRequest req5 = new CashFlowPostRequest();
//        req5.setAmount(5.0);
//        req5.setDescription("FOOD");
//        req5.setDate("2019-01-05");
//
//        reqList.add(new Pair<>(1L, req));
//        reqList.add(new Pair<>(2L, req2));
//        reqList.add(new Pair<>(3L, req3));
//        reqList.add(new Pair<>(4L, req4));
//        reqList.add(new Pair<>(5L, req5));
//
//
//
//        resList = new ArrayList<>();
//
//        CashFlowGetResponse res = new CashFlowGetResponse();
//        res.setId(1L);
//        res.setAmount(1.0);
//        res.setDescription("AAA");
//        res.setDate("2019-01-01");
//
//        CashFlowGetResponse res2 = new CashFlowGetResponse();
//        res2.setId(2L);
//        res2.setAmount(2.0);
//        res2.setDescription("BBB");
//        res2.setDate("2019-01-02");
//
//        CashFlowGetResponse res3 = new CashFlowGetResponse();
//        res3.setId(3L);
//        res3.setAmount(3.0);
//        res3.setDescription("DDD");
//        res3.setDate("2019-01-04");
//
//        resList.add(res);
//        resList.add(res2);
//        resList.add(res3);

        cashFlowHelper = new CashFlowHelper();
        map = new HashMap<>();
        map.put("FOOD", 10.0);
        map.put("SPORT", 20.0);
        map.put("PHONE", 30.0);
//        map.put("TEST", 30.5f);
//        map.put("TEST2", 30.5f);
//        System.out.println("map" + map);

        map2 = new HashMap<>();
        map2.put("FOOD", 11.0);
        map2.put("SPORT", 20.0);
        map2.put("PHONE", 30.5);
        map2.put("TEST", 30.0);
//        map2.put("TEST3", 30.0f);
//        System.out.println("map2" + map2);

        mapDiffExpected = new HashMap<>();
        mapDiffExpected.put("FOOD", 1.0);
        mapDiffExpected.put("PHONE", 0.5);
        mapDiffExpected.put("TEST", 30.0);
//        System.out.println(mapDiffExpected);

    }

    @Test
    void compareMaps() {
        Map<String, Double> mapDiffActual = cashFlowHelper.compareMaps(map, map2);

        System.out.println(mapDiffActual);

        Assert.assertEquals(
                mapDiffExpected,
                mapDiffActual);
    }

//    @Test
//    void compareRequestWithResponse() {
//        int aa = cashFlowHelper.compareRequestWithResponse(reqList, resList);
//        System.out.println(aa);
//    }

//    @Test
//    void compareRequestWithResponseByPathParam() {
//        List<Pair<Long, CashFlowPostRequest>> test = cashFlowHelper.compareRequestWithResponseByPathParam(reqList, LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-03"));
//
//        System.out.println(test);
//    }
}