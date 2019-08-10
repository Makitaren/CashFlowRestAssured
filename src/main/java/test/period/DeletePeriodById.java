package test.period;

import helper.PeriodHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.PeriodPath;
import payload.PeriodPostRequest;

public class DeletePeriodById {

    protected PeriodHelper periodHelper = new PeriodHelper();
    protected PeriodPath periodPath = new PeriodPath();

    private Long id = 0L;

    @BeforeEach
    public void preparationForTest() {
        PeriodPostRequest periodPostRequest = periodHelper.postPeriods(1).get(0).getValue();

        this.id = Long.parseLong(periodPath.postCreatePeriod(periodPostRequest).getHeader("Location").substring(33));
    }

    @Test
    public void successDeletePeriodById() {
        periodPath.deletePeriodById(id);

        periodPath.getPeriodById(id).then().statusCode(404);
    }
}
