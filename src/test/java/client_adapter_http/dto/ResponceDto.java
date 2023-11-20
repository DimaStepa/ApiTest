package client_adapter_http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponceDto {

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;
}
