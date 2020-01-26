package test.period;

import helper.PeriodHelper;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.PeriodPath;
import payload.PeriodGetResponse;
import payload.PeriodPostRequest;

import java.util.List;

public class PutPeriodById {
    protected PeriodHelper periodHelper = new PeriodHelper();
    protected PeriodPath p = new PeriodPath();

    private Long id = 0L;
    List<Pair<Long, PeriodPostRequest>> periodPostRequests;
    PeriodPostRequest firtPeriodPostRequest;
    PeriodPostRequest secondPeriodPostRequest;

    @BeforeEach
    public void preparationForTest() {
        periodPostRequests = periodHelper.postPeriods(1);
        firtPeriodPostRequest = periodPostRequests.get(0).getValue();

        this.id = periodPostRequests.get(0).getKey();
        secondPeriodPostRequest = periodHelper.getPeriods(2);
    }

    @Test
    public void successPutPeriodById() {

        p.putPeriodById(secondPeriodPostRequest, id);

        PeriodGetResponse periodGetResponse = p.getPeriodById(id).getBody().as(PeriodGetResponse.class);

        Assert.assertEquals("startDate:", secondPeriodPostRequest.getStartDate(), periodGetResponse.getStartDate());
        Assert.assertEquals("endDate:", secondPeriodPostRequest.getEndDate(), periodGetResponse.getEndDate());
    }

    @AfterEach
    public void cleanAfterTest() {
        p.deletePeriodById(id);
    }
}
