package payload;

import java.util.Map;

public class RaportResponse {
    Map<String, Double> raport;

    public Map<String, Double> getRaport() {
        return raport;
    }

    public void setRaport(Map<String, Double> raport) {
        this.raport = raport;
    }
}
