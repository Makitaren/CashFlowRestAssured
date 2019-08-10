package path;

import com.jayway.restassured.response.Response;
import payload.CashFlowPostRequest;
import payload.SavingPostRequest;

import static com.jayway.restassured.RestAssured.given;

public class SavingPath {
    public Response postCreateSaving(SavingPostRequest savingPostRequest) {

        return given()
                .contentType("application/json")
                .body(savingPostRequest)
                .when()
                .log()
                .all()
                .post("http://localhost:8080/api/saving");
    }

    public Response deleteSavingById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .log()
                .all()
                .delete("http://localhost:8080/api/saving/{id}");
    }

    public Response getSavindById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/saving/{id}");
    }

    public Response getSaving() {

        return given()
                .contentType("application/json")
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/saving");
    }

    public Response putSavingById(SavingPostRequest savingPostRequest, Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(savingPostRequest)
                .when()
                .log()
                .all()
                .put("http://localhost:8080/api/saving/{id}");
    }
}
