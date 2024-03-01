package adrikei.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static adrikei.service.KafkaConfig.BALANCES_TOPIC;

@Component
public class KafkaConsumers {

    private final List<Consumer<BalancesUpdatedEvent>> subscribers = new ArrayList<>();

    public void subscribeBalancesUpdated(Consumer<BalancesUpdatedEvent> subscriber) {
        subscribers.add(subscriber);
    }

    //{
    //  "Name":"BalanceUpdated",
    //  "Payload":{
    //      "account_id_from":"faa198e3-99a0-4114-8bc4-82c0ded58ec9",
    //      "account_id_to":"90f5c758-8330-4a27-aae8-c28631a9a359",
    //      "balance_account_id_from":400,
    //      "balance_account_id_to":1100
    //  }
    //}
    @KafkaListener(topics = BALANCES_TOPIC,
            containerFactory = "balancesUpdatedListenerContainerFactory"
    )
    public void listenToBalances(BalancesUpdatedEvent in) {
        subscribers.forEach(sub -> sub.accept(in));
    }
}
