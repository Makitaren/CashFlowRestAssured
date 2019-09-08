package path;

import com.jayway.restassured.response.Response;
import payload.SavingPostRequest;

import static com.jayway.restassured.RestAssured.given;

public class SavingPath extends RestAssuredTemplate {
    public Response postCreateSaving(SavingPostRequest savingPostRequest) {

        return given()
                .spec(specApi)
                .body(savingPostRequest)
                .post("/saving");
    }

    public Response deleteSavingById(Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .delete("/saving/{id}");
    }

    public Response getSavindById(Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .get("/saving/{id}");
    }

    public Response getSaving() {

        return given()
                .spec(specApi)
                .get("/saving");
    }

    public Response putSavingById(SavingPostRequest savingPostRequest, Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .body(savingPostRequest)
                .put("/saving/{id}");
    }
}
