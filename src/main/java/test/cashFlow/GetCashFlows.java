package test.cashFlow;

import helper.CashFlowHelper;
import helper.PeriodHelper;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.CashFlowPath;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;
import payload.PeriodGetResponse;
import payload.PeriodPostRequest;

import java.time.LocalDate;
import java.util.List;

public class GetCashFlows {
    protected CashFlowHelper h = new CashFlowHelper();
    protected PeriodHelper p = new PeriodHelper();
    protected CashFlowPath c = new CashFlowPath();

    int sizeBefore = 0;
    int sizeCreate = 10;
    int sizeAfter = 0;
    int sizeDiff = 0;
    private List<Pair<Long, CashFlowPostRequest>> req;
    private List<CashFlowGetResponse> res;
    private List<CashFlowGetResponse> resQueryParamsBefore;
    private List<CashFlowGetResponse> resQueryParams;
    private List<CashFlowGetResponse> resPathParamsBefore;
    private List<CashFlowGetResponse> resPathParams;
    private List<Pair<Long, PeriodPostRequest>> periods;

    @BeforeEach
    public void preparationForTest() {
        sizeBefore = h.getListCashFlows().size();
        System.out.println("sizeBefore" + sizeBefore);

        resPathParamsBefore = h.getListCashFlows(1);
        resQueryParamsBefore = h.getListCashFlows("2019-01-01", "2019-07-01");
        req = h.createCashFlows(sizeCreate);

        sizeAfter = h.getListCashFlows().size();
        System.out.println("sizeAfter" + sizeAfter);

        sizeDiff = sizeAfter - sizeBefore;
        System.out.println("sizeDiff" + sizeDiff);

        periods = p.createPeriods(1);

        res = h.getListCashFlows();
        resQueryParams = h.getListCashFlows("2019-01-01", "2019-07-01");
        resPathParams = h.getListCashFlows(1);
    }

    @Test
    public void successGetSize() {

        Assert.assertEquals("The number of created cash flows is different than the number of returned cash flows",
                sizeCreate, sizeDiff);
    }

    @Test
    public void successGetCashFlows() {
        List<Pair<Long, CashFlowPostRequest>> expected;

        Assert.assertEquals("The request and response are different:",
                sizeCreate, h.compareRequestWithResponse(req, res));
    }

    @Test
    public void successGetCashFlowsByQueryParams() {
        List<Pair<Long, CashFlowPostRequest>> CashFlowsByPeriod = h.getCashFlowsByPeriod(req,
                LocalDate.parse("2019-01-01"), LocalDate.parse("2019-07-01"));
        int reqQueryParamsSize = h.compareRequestWithResponseByPathParam(CashFlowsByPeriod, resQueryParams);

        Assert.assertEquals("The returned response does not match the searches:",
                resQueryParams.size() - resQueryParamsBefore.size(), reqQueryParamsSize);
    }

    @Test
    public void successGetCashFlowsByPathParam() {
        PeriodGetResponse periodGetResponse = p.getPeriodById(1L);
        List<Pair<Long, CashFlowPostRequest>> CashFlowsByPeriod = h.getCashFlowsByPeriod(req,
                LocalDate.parse(periodGetResponse.getStartDate()), LocalDate.parse(periodGetResponse.getEndDate()));
        int reqPathParamsSize = h.compareRequestWithResponseByPathParam(CashFlowsByPeriod, resQueryParams);

        Assert.assertEquals("The returned response does not match the searches:",
                resPathParams.size() - resPathParamsBefore.size(), reqPathParamsSize);
    }

    @AfterEach
    public void cleanAfterTest() {
        req.forEach((k) -> c.deleteCashFlowById(k.getKey()));
    }
}
