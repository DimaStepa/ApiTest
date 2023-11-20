package client_adapter_http.dto;

public class ScenarioDto {
    private ChannelList channel;

    public ChannelList getChannel() {
        return channel;
    }

    public void setChannel(ChannelList channel) {
        this.channel = channel;
    }

    public RecipientDto getRecipient() {
        return recipient;
    }

    public void setRecipient(RecipientDto recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FailoverDto getFailover() {
        return failover;
    }

    public void setFailover(FailoverDto failover) {
        this.failover = failover;
    }

    private RecipientDto recipient;
    private String sender;
    private String text;
    private FailoverDto failover;
}
