package helper;

import com.jayway.restassured.response.Response;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class CashFlowHelper {
    public String getRandomDescription() {
        List<String> Descriptions = new ArrayList<String>();
        Descriptions.add("FOOD");
        Descriptions.add("CLOTH");
        Descriptions.add("SAVE");
        Descriptions.add("HOME");
        Descriptions.add("ALCOHOL");

        return Descriptions.get((int) (Math.random() * Descriptions.size() - 1));
    }

    public Double getRandomAmount() {

        double scale = Math.pow(10, 2);
        return Math.round(Math.random() * 100 * scale) / scale;
    }

    public String getRandomDate() {
        Integer days = (int) (Math.random() * 30 + 1);
        String day = days.toString();
        if(days.toString().length() < 2) {day = "0" + days.toString();}

        Integer months = (int) (Math.random() * 12 + 1);
        String month = months.toString();
        if(months.toString().length() < 2) {month = "0" + months.toString();}

        return "2020-" + month + "-" + day;
    }

    public CashFlowPostRequest createCashFlowRequest(Double amonut, String description, String date) {
        CashFlowPostRequest cashFlowPostRequest = new CashFlowPostRequest(amonut, description, date);
        return cashFlowPostRequest;
    }

    public List<CashFlowGetResponse> getListCashFlows() {
       return Arrays.asList(getCashFlows().getBody().as(CashFlowGetResponse[].class));

    }

    public Long getExistCashFlowId(List<CashFlowGetResponse> list) {
        return list.get((int)(Math.random() * list.size())).getId();
    }

    public Response postCreateCashFlow(CashFlowPostRequest cashFlowPostRequest) {

        return given()
                .contentType("application/json")
                .body(cashFlowPostRequest)
                .when()
                .post("http://localhost:8080/api/cashFlow");
    }

    public Response deleteCashFlowById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .delete("http://localhost:8080/api/cashFlow/{id}");
    }

    public Response getCashFlowById(Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .get("http://localhost:8080/api/cashFlow/{id}");
    }

    public Response getCashFlowRaport() {

        return given()
                .contentType("application/json")
                .when()
                .get("http://localhost:8080/api/cashFlow/raport");
    }

    public Response getCashFlows() {

        return given()
                .contentType("application/json")
                .when()
                .get("http://localhost:8080/api/cashFlows");
    }

    public Response postCashFlowByFile() {

        return given()
                .multiPart("file", new File("/home/makitaren/Desktop/csv.csv"))
                .contentType("multipart/form-data")
                .when()
                .post("http://localhost:8080/api/cashFlow/uploadCashFlow");
    }

    public Response putCashFlowById(CashFlowPostRequest cashFlowPostRequest, Long id) {

        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(cashFlowPostRequest)
                .when()
                .put("http://localhost:8080/api/cashFlow/{id}");
    }

    public Response getDescriptionCashFlow() {

        return given()
                .contentType("text/plain;charset=UTF-8")
                .when()
                .get("http://localhost:8080");
    }
}
