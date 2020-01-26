package path;

import com.jayway.restassured.response.Response;
import payload.PeriodPostRequest;

import static com.jayway.restassured.RestAssured.given;

public class PeriodPath extends RestAssuredTemplate {
    public Response postCreatePeriod(PeriodPostRequest periodPostRequest) {

        return given()
                .spec(specApi)
                .body(periodPostRequest)
                .post("/period");
    }

    public Response deletePeriodById(Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .delete("/period/{id}");
    }

    public Response getPeriods() {

        return given()
                .spec(specApi)
                .get("/periods");
    }

    public Response putPeriodById(PeriodPostRequest periodPostRequest, Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .body(periodPostRequest)
                .put("/period/{id}");
    }

    public Response getPeriodById(Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .get("/period/{id}");
    }
}
