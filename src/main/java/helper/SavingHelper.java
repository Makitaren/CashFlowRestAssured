package helper;

import javafx.util.Pair;
import path.PeriodPath;
import path.SavingPath;
import payload.PeriodPostRequest;
import payload.SavingPostRequest;

import java.util.ArrayList;
import java.util.List;

public class SavingHelper {
    protected SavingPath savingPath = new SavingPath();

    public List<Pair<Long, SavingPostRequest>> postSaving(Integer amount) {
        List<Pair<Long, SavingPostRequest>> saving = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            SavingPostRequest savingPostRequest = getSaving(i + 1);
            Long id = Long.parseLong(savingPath.postCreateSaving(savingPostRequest).getHeader("Location").substring(33));
            Pair<Long, SavingPostRequest> entity = new Pair<>(id, savingPostRequest);
            saving.add(entity);
        }
        return saving;
    }

    public SavingPostRequest getSaving(int number) {
        switch (number) {
            case 1:
                return new SavingPostRequest("ING", 2000.0);
            case 2:
                return new SavingPostRequest("Degiro", 3000.0);
            case 3:
                return new SavingPostRequest("Cash", 1535.0);
            case 4:
                return new SavingPostRequest("Gold", 5000.0);
        }
        return new SavingPostRequest("ING", 2000.0);
    }


}
