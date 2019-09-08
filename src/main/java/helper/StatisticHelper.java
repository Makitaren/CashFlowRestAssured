package helper;

import javafx.util.Pair;
import payload.CashFlowPostRequest;
import payload.PeriodGetResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticHelper {
    protected PeriodHelper p = new PeriodHelper();

//    public List<Map<String, Double>> getRaport() throws ParseException {
//        List<Map<String, Double>> raports = new ArrayList<>();
//
//        List<Period> allPeriods = periodRepository.findAll();
//
//        for (Period p : allPeriods) {
//            Period period = periodRepository.findById(p.getId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Period", "id", p.getId()));
//
//            List<CashFlow> allCashFlow = cashFlowRepository.findAllByDateBetween(
//                    new SimpleDateFormat("yyyy-MM-dd").parse(period.getStartDate()),
//                    new SimpleDateFormat("yyyy-MM-dd").parse(period.getEndDate()));
//
//            Map<String, Double> cashFlowByDescription = allCashFlow.stream().collect(
//                    Collectors.groupingBy(CashFlow::getDescription, Collectors.summingDouble(CashFlow::getAmount)));
//
//            raports.add(cashFlowByDescription);
//        }
//        return raports;
//    }

    public List<Pair<Long, CashFlowPostRequest>> getCashFlowsByPeriod(List<Pair<Long, CashFlowPostRequest>> req, LocalDate startDate, LocalDate endDate) {

        List<Pair<Long, CashFlowPostRequest>> collect = req.stream()
                .filter(x -> (startDate.isBefore(LocalDate.parse(x.getValue().getDate())) ||
                        startDate.equals(LocalDate.parse(x.getValue().getDate()))) &&
                        endDate.isAfter(LocalDate.parse(x.getValue().getDate())))
                .collect(Collectors.toList());

        return collect;
    }

    public List<List<Pair<Long, CashFlowPostRequest>>> getCashFlowsByPeriods(List<Pair<Long, CashFlowPostRequest>> req) {
        List<PeriodGetResponse> periods = p.getPeriods();
        List<List<Pair<Long, CashFlowPostRequest>>> raports = new ArrayList<>();

        for (int i = 0; i < periods.size(); i++) {
            List<Pair<Long, CashFlowPostRequest>> list = getCashFlowsByPeriod(req,
                    LocalDate.parse(periods.get(i).getStartDate()), LocalDate.parse(periods.get(i).getEndDate()));
            raports.add(list);
        }
        return raports;
    }

    public Map<String, Double> getRaportByPeriods(List<Pair<Long, CashFlowPostRequest>> req) {
        List<CashFlowPostRequest> allCashFlow = new ArrayList<>();

        for (Pair<Long, CashFlowPostRequest> c : req) {
            allCashFlow.add(c.getValue());
        }

        return allCashFlow.stream().collect(
                Collectors.groupingBy(CashFlowPostRequest::getDescription, Collectors.summingDouble(CashFlowPostRequest::getAmount)));
    }

    public List<Map<String, Double>> getRaportByPeriodss(List<Pair<Long, CashFlowPostRequest>> req) {
        List<List<Pair<Long, CashFlowPostRequest>>> list = getCashFlowsByPeriods(req);
        List<Map<String, Double>> back = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            back.add(getRaportByPeriods(list.get(i)));
        }

        return back;
    }


}
