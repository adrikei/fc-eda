package adrikei.service;

public class BalancesUpdatedEvent {
    private String name;

    private BalancesUpdatedPayload payload;

    public BalancesUpdatedEvent() {
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BalancesUpdatedPayload getPayload() {
        return payload;
    }

    public void setPayload(BalancesUpdatedPayload payload) {
        this.payload = payload;
    }
}
