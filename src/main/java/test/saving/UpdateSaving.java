package test.saving;

import helper.SavingHelper;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import path.SavingPath;
import payload.SavingGetResponse;
import payload.SavingPostRequest;

import java.util.List;

public class UpdateSaving {
    protected SavingHelper savingHelper = new SavingHelper();
    protected SavingPath savingPath = new SavingPath();

    private Long id = 0L;
    List<Pair<Long, SavingPostRequest>> saving;
    SavingPostRequest firtSavingPostRequest;
    SavingPostRequest secondSavingPostRequest;
    SavingGetResponse savingGetResponse;

    @BeforeEach
    public void preparationForTest() {
        saving = savingHelper.postSaving(1);
        firtSavingPostRequest = saving.get(0).getValue();

        this.id = saving.get(0).getKey();
        secondSavingPostRequest = savingHelper.getSaving(2);
    }

    @Test
    public void successPutPeriodById() {
        savingPath.putSavingById(secondSavingPostRequest, id);

        savingGetResponse = savingPath.getSavindById(id).getBody().as(SavingGetResponse.class);

        Assert.assertEquals("startDate:", secondSavingPostRequest.getName(), savingGetResponse.getName());
        Assert.assertEquals("endDate:", secondSavingPostRequest.getAmount(), savingGetResponse.getAmount());
    }

    @AfterEach
    public void cleanAfterTest() {
        savingPath.deleteSavingById(id);
    }
}
