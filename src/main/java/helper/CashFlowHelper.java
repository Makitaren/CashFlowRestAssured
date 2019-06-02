package helper;

import payload.CashFlowPostRequest;
import java.text.ParseException;
import static com.jayway.restassured.RestAssured.given;

public class CashFlowHelper {

    public CashFlowPostRequest createCashFlowRequest() throws ParseException {
        CashFlowPostRequest cashFlowPostRequest = new CashFlowPostRequest();
        cashFlowPostRequest.setAmount(25.0);
        cashFlowPostRequest.setDescription("Food");
        cashFlowPostRequest.setDate("2019-05-25");
        return cashFlowPostRequest;
    }

    public void PostCerateCashFlow(CashFlowPostRequest cashFlowPostRequest) {

        given()
                .contentType("application/json")
                .body(cashFlowPostRequest)
                .when()
                .post("http://localhost:8080/api/cashFlow")
                .then()
                .statusCode(201);
    }
}
