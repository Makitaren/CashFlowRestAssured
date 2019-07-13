package helper;

import com.jayway.restassured.response.Response;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public String getAnotherRandomDescription(String description) {
        String anotherDescription = getRandomDescription();

        if (!description.equals(anotherDescription)) {
            return anotherDescription;
        }

        return getAnotherRandomDescription(description);
    }

    public Double getRandomAmount() {

        double scale = Math.pow(10, 2);
        return Math.round(Math.random() * 100 * scale) / scale;
    }

    public Double getAnotherRandomAmount(Double amount) {
        Double anotherAmount = getRandomAmount();

        if (!amount.equals(anotherAmount)) {
            return anotherAmount;
        }

        return getAnotherRandomAmount(amount);
    }

    public String getRandomDate() {
        Integer days = (int) (Math.random() * 30 + 1);
        String day = days.toString();
        if (days.toString().length() < 2) {
            day = "0" + days.toString();
        }

        Integer months = (int) (Math.random() * 12 + 1);
        String month = months.toString();
        if (months.toString().length() < 2) {
            month = "0" + months.toString();
        }

        return "2020-" + month + "-" + day;
    }

    public String getAnotherRandomDate(String date) {
        String anotherDate = getRandomDate();

        if (!date.equals(anotherDate)) {
            return anotherDate;
        }
        return getAnotherRandomDate(date);
    }

    public CashFlowPostRequest createCashFlowRequest(Double amonut, String description, String date) {
        CashFlowPostRequest cashFlowPostRequest = new CashFlowPostRequest(amonut, description, date);
        return cashFlowPostRequest;
    }

    public List<CashFlowGetResponse> getListCashFlows() {
        return Arrays.asList(getCashFlows().getBody().as(CashFlowGetResponse[].class));

    }

    public Long getExistCashFlowId(List<CashFlowGetResponse> list) {
        return list.get((int) (Math.random() * list.size())).getId();
    }

    public Map<String, Float> getDifferent(Map<String, Float> first, Map<String, Float> second) {
        return second.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue() - first.get(e.getKey())));
    }

    public Map<String, Float> compareMaps(Map<String, Float> map1, Map<String, Float> map2) {
        Map<String, Float> withoutNuLL  = map2.entrySet().stream()
                .filter(x -> map1.get(x.getKey()) != null)
                .filter(x -> x.getValue() - map1.get(x.getKey()) != 0)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue() - map1.get(x.getKey())));

        Map<String, Float> withNuLL = map2.entrySet().stream()
                .filter(x -> map1.get(x.getKey()) == null)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        return Stream.of(withoutNuLL, withNuLL).map(Map::entrySet).flatMap(Collection::stream)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

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

    public Response getCashFlowRaport() {

        return given()
                .contentType("application/json")
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/cashFlow/raport");
    }

    public Response getCashFlows() {

        return given()
                .contentType("application/json")
                .when()
                .log()
                .all()
                .get("http://localhost:8080/api/cashFlows");
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
