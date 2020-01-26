package helper;

import javafx.util.Pair;
import path.PeriodPath;
import payload.PeriodGetResponse;
import payload.PeriodPostRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeriodHelper {
    protected PeriodPath p = new PeriodPath();

    public PeriodGetResponse getPeriodById(Long id) {
        return p.getPeriodById(id).as(PeriodGetResponse.class);
    }

    public List<PeriodGetResponse> getPeriods() {
        return Arrays.asList(p.getPeriods().getBody().as(PeriodGetResponse[].class));
    }

    public List<Pair<Long, PeriodPostRequest>> postPeriods(Integer amount) {
        List<Pair<Long, PeriodPostRequest>> cashFlows = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            PeriodPostRequest periodPostRequest = getPeriods(i + 1);
            Long id = Long.parseLong(p.postCreatePeriod(periodPostRequest).getHeader("Location").substring(33));
            Pair<Long, PeriodPostRequest> entity = new Pair<>(id, periodPostRequest);
            cashFlows.add(entity);
        }
        return cashFlows;
    }

    public PeriodPostRequest getPeriods(int month) {
        switch (month) {
            case 1:
                return new PeriodPostRequest("2019-01-01", "2019-01-31");
            case 2:
                return new PeriodPostRequest("2019-02-01", "2019-02-28");
            case 3:
                return new PeriodPostRequest("2019-03-01", "2019-03-31");
            case 4:
                return new PeriodPostRequest("2019-04-01", "2019-04-30");
            case 5:
                return new PeriodPostRequest("2019-05-01", "2019-05-31");
            case 6:
                return new PeriodPostRequest("2019-06-01", "2019-06-30");
            case 7:
                return new PeriodPostRequest("2019-07-01", "2019-07-31");
            case 8:
                return new PeriodPostRequest("2019-08-01", "2019-08-31");
            case 9:
                return new PeriodPostRequest("2019-09-01", "2019-09-30");
            case 10:
                return new PeriodPostRequest("2019-10-01", "2019-10-31");
            case 11:
                return new PeriodPostRequest("2019-11-01", "2019-11-30");
            case 12:
                return new PeriodPostRequest("2019-12-01", "2019-12-31");
        }
        return new PeriodPostRequest("2019-01-01", "2019-01-31");
    }
}
