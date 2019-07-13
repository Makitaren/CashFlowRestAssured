package payload;


public class CashFlowPostRequest {
    private Double amount;
    private String description;
    private String date;

    public CashFlowPostRequest(Double amount, String description, String date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CashFlowPostRequest{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
