package path;

import com.jayway.restassured.response.Response;
import payload.PeriodPostRequest;

import static com.jayway.restassured.RestAssured.given;

public class PeriodPath {
    public Response postCreatePeriod(PeriodPostRequest periodPostRequest) {

        return given()
                .contentType("application/json")
                .body(periodPostRequest)
                .when()
                .log()
                .all()
                .post("http://localhost:8080/api/period");
    }

    public Response deletePeriodById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .log()
                .all()
                .delete("http://localhost:8080/api/period/{id}");
    }

    public Response getPeriods() {

        return given()
                .contentType("application/json")
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/periods");
    }

    public Response putPeriodById(PeriodPostRequest periodPostRequest, Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(periodPostRequest)
                .when()
                .log()
                .all()
                .put("http://localhost:8080/api/period/{id}");
    }

    public Response getPeriodById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/period/{id}");
    }
}
