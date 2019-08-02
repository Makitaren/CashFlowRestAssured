package helper;

import javafx.util.Pair;
import path.CashFlowPath;
import payload.CashFlowGetResponse;
import payload.CashFlowPostRequest;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CashFlowHelper {
    protected CashFlowPath c = new CashFlowPath();

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
        Integer days = (int) (Math.random() * 28 + 1);
        String day = days.toString();
        if (days.toString().length() < 2) {
            day = "0" + days.toString();
        }

        Integer months = (int) (Math.random() * 12 + 1);
        String month = months.toString();
        if (months.toString().length() < 2) {
            month = "0" + months.toString();
        }

        return "2019-" + month + "-" + day;
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

    public List<Pair<Long, CashFlowPostRequest>> createCashFlows(Integer amount) {
        List<Pair<Long, CashFlowPostRequest>> cashFlows = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            CashFlowPostRequest cashFlowPostRequest = createCashFlowRequest(getRandomAmount(), getRandomDescription(), getRandomDate());
            Long id = Long.parseLong(c.postCreateCashFlow(cashFlowPostRequest).getHeader("Location").substring(35));
            Pair<Long, CashFlowPostRequest> entity = new Pair<>(id, cashFlowPostRequest);
            cashFlows.add(entity);
        }
        return cashFlows;
    }

    public List<CashFlowGetResponse> getListCashFlows() {
        return Arrays.asList(c.getCashFlows().getBody().as(CashFlowGetResponse[].class));

    }

    public List<CashFlowGetResponse> getListCashFlows(String startDate, String endDate) {
        return Arrays.asList(c.getCashFlows(startDate, endDate).getBody().as(CashFlowGetResponse[].class));

    }

    public List<CashFlowGetResponse> getListCashFlows(Integer period) {
        return Arrays.asList(c.getCashFlows(period).getBody().as(CashFlowGetResponse[].class));

    }

    public Map<String, Double> compareMaps(Map<String, Double> map1, Map<String, Double> map2) {
        Map<String, Double> withoutNuLL = map2.entrySet().stream()
                .filter(x -> map1.get(x.getKey()) != null)
                .filter(x -> x.getValue() - map1.get(x.getKey()) != 0)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue() - map1.get(x.getKey())));

        Map<String, Double> withNuLL = map2.entrySet().stream()
                .filter(x -> map1.get(x.getKey()) == null)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        return Stream.of(withoutNuLL, withNuLL).map(Map::entrySet).flatMap(Collection::stream)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    }

    public int compareRequestWithResponse(List<Pair<Long, CashFlowPostRequest>> req, List<CashFlowGetResponse> res) {
        AtomicInteger count = new AtomicInteger();

        req.forEach((q) -> res.forEach((s) -> {
            if (q.getKey().equals(s.getId()) && q.getValue().getAmount().equals(s.getAmount()) &&
                    q.getValue().getDescription().equals(s.getDescription()) &&
                    q.getValue().getDate().equals(s.getDate())) {

                count.set(count.get() + 1);
            }
        }));

        return count.get();
    }

    public List<Pair<Long, CashFlowPostRequest>> getCashFlowsByPeriod(List<Pair<Long, CashFlowPostRequest>> req, LocalDate startDate, LocalDate endDate) {

        List<Pair<Long, CashFlowPostRequest>> collect = req.stream()
                .filter(x -> (startDate.isBefore(LocalDate.parse(x.getValue().getDate())) ||
                        startDate.equals(LocalDate.parse(x.getValue().getDate()))) &&
                        endDate.isAfter(LocalDate.parse(x.getValue().getDate())))
                .collect(Collectors.toList());

        return collect;
    }

    public int compareRequestWithResponseByPathParam(List<Pair<Long, CashFlowPostRequest>> req, List<CashFlowGetResponse> res) {
        AtomicInteger count = new AtomicInteger();

        req.forEach((q) -> res.forEach((s) -> {
            if (q.getKey().equals(s.getId()) && q.getValue().getAmount().equals(s.getAmount()) &&
                    q.getValue().getDescription().equals(s.getDescription()) &&
                    q.getValue().getDate().equals(s.getDate())) {

                count.set(count.get() + 1);
            }
        }));
        return count.get();
    }

    public Map<String, Double> convertStringToDoubleInMap (Map<String, String> map){
        return map
                .entrySet()
                .stream()
                .collect(Collectors.toMap(x -> x.getKey(), x -> Double.parseDouble(x.getValue())));
    }
}

