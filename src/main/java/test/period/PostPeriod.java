package test.period;

import helper.PeriodHelper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.PeriodPath;
import payload.PeriodGetResponse;
import payload.PeriodPostRequest;

public class PostPeriod {
    protected PeriodHelper periodHelper = new PeriodHelper();
    protected PeriodPath p = new PeriodPath();

    private Long id = 0L;
    PeriodPostRequest periodPostRequest;


    @BeforeEach
    public void preparationForTest() {
        periodPostRequest = periodHelper.postPeriods(1).get(0).getValue();
    }

    @Test
    public void successCreatePeriod() {
        this.id = Long.parseLong(p.postCreatePeriod(periodPostRequest).getHeader("Location").substring(33));

        PeriodGetResponse periodGetResponse = p.getPeriodById(id).getBody().as(PeriodGetResponse.class);

        Assert.assertEquals("Amount:", periodPostRequest.getStartDate(), periodGetResponse.getStartDate());
        Assert.assertEquals("Date:", periodPostRequest.getEndDate(), periodGetResponse.getEndDate());
    }

    @AfterEach
    public void cleanAfterTest() {
        p.deletePeriodById(id);
    }
}
