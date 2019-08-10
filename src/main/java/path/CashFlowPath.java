package path;

import com.jayway.restassured.response.Response;
import payload.CashFlowPostRequest;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

public class CashFlowPath {
    public Response postCreateCashFlow(CashFlowPostRequest cashFlowPostRequest) {

        return given()
                .contentType("application/json")
                .body(cashFlowPostRequest)
                .when()
                .log()
                .all()
                .post("http://localhost:8080/api/cashFlow");
    }

    public Response deleteCashFlowById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .log()
                .all()
                .delete("http://localhost:8080/api/cashFlow/{id}");
    }

    public Response getCashFlowById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/cashFlow/{id}");
    }

    public Response getCashFlows() {

        return given()
                .contentType("application/json")
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/cashFlows");
    }

    public Response getCashFlows(String startDate, String endDate) {

        return given()
                .contentType("application/json")
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/cashFlows");
    }

    public Response getCashFlows(Integer period) {

        return given()
                .contentType("application/json")
                .pathParam("period", period)
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/cashFlows/{period}");
    }

    public Response postCashFlowByFile(String path) {

        return given()
                .multiPart("file", new File(path))
                .contentType("multipart/form-data")
                .when()
                .log()
                .all()
                .post("http://localhost:8080/api/cashFlow/uploadCashFlow");
    }

    public Response putCashFlowById(CashFlowPostRequest cashFlowPostRequest, Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(cashFlowPostRequest)
                .when()
                .log()
                .all()
                .put("http://localhost:8080/api/cashFlow/{id}");
    }

    public Response getDescriptionCashFlow() {

        return given()
                .contentType("text/plain;charset=UTF-8")
                .when()
                .log()
                .all()
                .get("http://localhost:8080");
    }
}
