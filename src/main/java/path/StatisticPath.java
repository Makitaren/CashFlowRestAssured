package path;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class StatisticPath {

    public Response getCashFlowRaport() {

        return given()
                .contentType("application/json")
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/cashFlows/raport");
    }
}
