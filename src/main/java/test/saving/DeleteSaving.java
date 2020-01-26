package test.saving;

import helper.SavingHelper;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.SavingPath;
import payload.PeriodPostRequest;
import payload.SavingPostRequest;

import java.util.List;

public class DeleteSaving {
    protected SavingHelper savingHelper = new SavingHelper();
    protected SavingPath savingPath = new SavingPath();

    private Long id = 0L;
    List<Pair<Long, SavingPostRequest>> saving;
    PeriodPostRequest firtPeriodPostRequest;

    @BeforeEach
    public void preparationForTest() {
        saving = savingHelper.postSaving(1);

        this.id = saving.get(0).getKey();
    }

    @Test
    public void successDeletePeriodById() {
        savingPath.deleteSavingById(id);

        savingPath.getSavindById(id).then().statusCode(404);
    }
}
