package client_adapter_http.dto;

import java.util.List;

public class ClientRequestDto {
    public ScenarioDto getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioDto scenario) {
        this.scenario = scenario;
    }

    public List<String> getTrackdata() {
        return trackdata;
    }

    public void setTrackdata(List<String> trackdata) {
        this.trackdata = trackdata;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    private ScenarioDto scenario;
    private List<String> trackdata;
    private String callback;

}
