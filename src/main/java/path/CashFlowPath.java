package path;

import com.jayway.restassured.response.Response;
import payload.CashFlowPostRequest;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

public class CashFlowPath extends RestAssuredTemplate {
    public Response postCreateCashFlow(CashFlowPostRequest cashFlowPostRequest) {

        return given()
                .spec(specApi)
                .body(cashFlowPostRequest)
                .post("/cashFlow");
    }

    public Response deleteCashFlowById(Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .delete("/cashFlow/{id}");
    }

    public Response getCashFlowById(Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .get("/cashFlow/{id}");
    }

    public Response getCashFlows() {

        return given()
                .spec(specApi)
                .get("/cashFlows");
    }

    public Response getCashFlows(String startDate, String endDate) {

        return given()
                .spec(specApi)
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .get("/cashFlows");
    }

    public Response getCashFlows(Integer period) {

        return given()
                .spec(specApi)
                .pathParam("period", period)
                .get("/cashFlow/{period}");
    }

    public Response postCashFlowByFile(String path) {

        return given()
                .spec(specApi)
                .multiPart("file", new File(path))
                .contentType("multipart/form-data")
                .post("/uploadCashFlow");
    }

    public Response putCashFlowById(CashFlowPostRequest cashFlowPostRequest, Long id) {

        return given()
                .spec(specApi)
                .pathParam("id", id)
                .body(cashFlowPostRequest)
                .put("/cashFlow/{id}");
    }

    public Response getDescriptionCashFlow() {

        return given()
                .spec(specApi)
                .contentType("text/plain;charset=UTF-8")
                .get("");
    }
}
