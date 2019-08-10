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

public class CreateSaving {
    protected SavingHelper savingHelper = new SavingHelper();
    protected SavingPath savingPath = new SavingPath();
    protected List<Pair<Long, SavingPostRequest>> saving;
    protected SavingGetResponse savingGetResponse;
    protected SavingPostRequest savingPostRequest;
    private Long id = 0L;



    @BeforeEach
    public void preparationForTest() {
        saving = savingHelper.postSaving(1);
        savingPostRequest = saving.get(0).getValue();
        this.id = saving.get(0).getKey();
        savingGetResponse = savingPath.getSavindById(id).getBody().as(SavingGetResponse.class);
    }

    @Test
    public void successCreatePeriod() {
        Assert.assertEquals("name:", savingPostRequest.getName(), savingGetResponse.getName());
        Assert.assertEquals("amount:", savingPostRequest.getAmount(), savingGetResponse.getAmount());
    }

    @AfterEach
    public void cleanAfterTest() {
        savingPath.deleteSavingById(id);
    }
}
