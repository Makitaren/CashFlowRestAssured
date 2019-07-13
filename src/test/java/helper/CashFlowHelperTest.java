package helper;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class CashFlowHelperTest {
    CashFlowHelper cashFlowHelper;
    Map<String, Float> map;
    Map<String, Float> map2;
    Map<String, Float> mapDiffExpected;

    @BeforeEach
    void initial() {
        cashFlowHelper = new CashFlowHelper();
        map = new HashMap<>();
        map.put("FOOD", 10.0f);
        map.put("SPORT", 20.0f);
        map.put("PHONE", 30.0f);
//        map.put("TEST", 30.5f);
//        map.put("TEST2", 30.5f);
        System.out.println("map" + map);

        map2 = new HashMap<>();
        map2.put("FOOD", 11.0f);
        map2.put("SPORT", 20.0f);
        map2.put("PHONE", 30.5f);
        map2.put("TEST", 30.0f);
//        map2.put("TEST3", 30.0f);
        System.out.println("map2" + map2);

        mapDiffExpected = new HashMap<>();
        mapDiffExpected.put("FOOD", 1.0f);
        mapDiffExpected.put("PHONE", 0.5f);
        mapDiffExpected.put("TEST", 30.0f);
        System.out.println(mapDiffExpected);
    }

    @Test
    void getDifferent() {
        Map<String, Float> mapDiffActual = cashFlowHelper.getDifferent(map, map2);
        System.out.println(mapDiffActual);

        Assert.assertEquals(
                mapDiffExpected,
                mapDiffActual);
    }

    @Test
    void mergeMaps() {
        Map<String, Float> mapDiffActual = cashFlowHelper.compareMaps(map, map2);

        Assert.assertEquals(
                mapDiffExpected,
                mapDiffActual);
    }
}