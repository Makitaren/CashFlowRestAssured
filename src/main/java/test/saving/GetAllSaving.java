package test.saving;

import helper.CashFlowHelper;
import helper.PeriodHelper;
import helper.SavingHelper;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.CashFlowPath;
import path.SavingPath;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;
import payload.PeriodPostRequest;

import java.util.List;

public class GetAllSaving {

    protected CashFlowHelper h = new CashFlowHelper();
    protected PeriodHelper p = new PeriodHelper();
    protected CashFlowPath c = new CashFlowPath();
    protected SavingHelper savingHelper = new SavingHelper();
    protected SavingPath savingPath = new SavingPath();

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
        req = h.createCashFlowsPairs(sizeCreate);

        sizeAfter = h.getListCashFlows().size();
        System.out.println("sizeAfter" + sizeAfter);

        sizeDiff = sizeAfter - sizeBefore;
        System.out.println("sizeDiff" + sizeDiff);

        periods = p.postPeriods(1);

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
    public void successGetPeriods() {
        List<Pair<Long, CashFlowPostRequest>> expected;

        Assert.assertEquals("The request and response are different:",
                sizeCreate, h.compareRequestWithResponse(req, res));
    }

    @AfterEach
    public void cleanAfterTest() {
        req.forEach((k) -> c.deleteCashFlowById(k.getKey()));
    }
}
