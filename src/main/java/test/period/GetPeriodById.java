package test.period;

import com.jayway.restassured.response.Response;
import helper.PeriodHelper;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.PeriodPath;
import payload.PeriodGetResponse;
import payload.PeriodPostRequest;

public class GetPeriodById {
    protected PeriodHelper periodHelper = new PeriodHelper();
    protected PeriodPath periodPath = new PeriodPath();

    private Long id = 0L;
    PeriodPostRequest periodPostRequest;

    @BeforeEach
    public void preparationForTest() {
        periodPostRequest = periodHelper.postPeriods(1).get(0).getValue();

        this.id = Long.parseLong(periodPath.postCreatePeriod(periodPostRequest).getHeader("Location").substring(33));
    }

    @Test
    public void successGetPeriodById() {
        Response response = periodPath.getPeriodById(id);

        PeriodGetResponse periodGetResponse = response.as(PeriodGetResponse.class);

        response.then().statusCode(200);

        Assert.assertEquals("startDate:", periodPostRequest.getStartDate(), periodGetResponse.getStartDate());
        Assert.assertEquals("endDate:", periodPostRequest.getEndDate(), periodGetResponse.getEndDate());
    }

    @AfterEach
    public void cleanAfterTest() {
        periodPath.deletePeriodById(id);
    }
}
