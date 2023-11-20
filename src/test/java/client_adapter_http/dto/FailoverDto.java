package client_adapter_http.dto;

public class FailoverDto {
    public String getTll() {
        return tll;
    }

    public void setTll(String tll) {
        this.tll = tll;
    }

    public String getConditionStatus() {
        return conditionStatus;
    }

    public void setConditionStatus(String conditionStatus) {
        this.conditionStatus = conditionStatus;
    }

    private String tll;
    private String conditionStatus;

}
