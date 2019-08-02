package helper;

import javafx.util.Pair;
import path.PeriodPath;
import payload.PeriodGetResponse;
import payload.PeriodPostRequest;

import java.util.ArrayList;
import java.util.List;

public class PeriodHelper {
    protected PeriodPath p = new PeriodPath();

    public PeriodGetResponse getPeriodById(Long id) {
        return p.getPeriodById(id).as(PeriodGetResponse.class);
    }

    public List<Pair<Long, PeriodPostRequest>> createPeriods(Integer amount) {
        List<Pair<Long, PeriodPostRequest>> cashFlows = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            PeriodPostRequest periodPostRequest = new PeriodPostRequest("2019-01-01", "2019-06-01");
            Long id = Long.parseLong(p.postCreatePeriod(periodPostRequest).getHeader("Location").substring(33));
            Pair<Long, PeriodPostRequest> entity = new Pair<>(id, periodPostRequest);
            cashFlows.add(entity);
        }
        return cashFlows;
    }
}