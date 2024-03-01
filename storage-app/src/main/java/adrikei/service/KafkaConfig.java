package adrikei.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@EnableKafka
@Configuration
public class KafkaConfig {

    private static final String TRANSACTIONS_TOPIC = "transactions";
    public static final String BALANCES_TOPIC = "balances";
    @Bean
    public NewTopic transactions() {
        return TopicBuilder.name(TRANSACTIONS_TOPIC).build();
    }

    @Bean
    public NewTopic balances() {
        return TopicBuilder.name(BALANCES_TOPIC).build();
    }

    @Value(value = "${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String groupId;

    public ConsumerFactory<String, BalancesUpdatedEvent> balanceUpdatedEventConsumerFactory() {
        HashMap<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        ObjectMapper objectMapper = JacksonUtils.enhancedObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        JsonDeserializer<BalancesUpdatedEvent> balanceUpdatedEventJsonDeserializer = new JsonDeserializer<>(BalancesUpdatedEvent.class, objectMapper);
//        JsonDeserializer<BalanceUpdatedEvent> balanceUpdatedEventJsonDeserializer = new JsonDeserializer<>(BalanceUpdatedEvent.class);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                balanceUpdatedEventJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BalancesUpdatedEvent>
    balancesUpdatedListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, BalancesUpdatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(balanceUpdatedEventConsumerFactory());
        return factory;
    }

//    @KafkaListener(id="transactionsListener", topics=TRANSACTIONS_TOPIC)
//    public void listenToTransactions(String in) {
//        System.out.println(in);
//    }
//     {"Name":"TransactionCreated","Payload":{"id":"3b6ddf96-6a7e-4933-aa07-ff1b838789ab","account_id_from":"faa198e3-99a0-4114-8bc4-82c0ded58ec9","account_id_to":"90f5c758-8330-4a27-aae8-c28631a9a359","amount":100}}
}
