package path;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class StatisticPath extends RestAssuredTemplate{

    public Response getCashFlowRaport() {

        return given()
                .spec(specApi)
                .get("/cashFlows/raport");
    }
}
