package adrikei.service.core;

import adrikei.service.BalancesUpdatedEvent;
import adrikei.service.BalancesUpdatedPayload;
import adrikei.service.KafkaConsumers;
import adrikei.service.core.persistence.Balance;
import adrikei.service.core.persistence.BalanceRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {

    private final KafkaConsumers consumers;
    private final BalanceRepository repository;

    public BalanceService(KafkaConsumers consumers, BalanceRepository repository) {
        this.consumers = consumers;
        this.repository = repository;
    }

    private void onEvent(BalancesUpdatedEvent event){
        System.out.println("BalanceService: Balance updated");
        BalancesUpdatedPayload payload = event.getPayload();

        Balance from = new Balance();
        from.setAccount(payload.getAccountIdFrom());
        from.setBalance(payload.getBalanceAccountIdFrom());
        repository.save(from);

        Balance to = new Balance();
        to.setAccount(payload.getAccountIdTo());
        to.setBalance(payload.getBalanceAccountIdTo());
        repository.save(to);
    }

    @PostConstruct
    private void init() {
        System.out.println("BalanceService: init");
        consumers.subscribeBalancesUpdated(this::onEvent);
    }

    public Optional<BalanceRecord> getBalance(String account){
        return repository.findById(account).map(b -> new BalanceRecord(b.getAccount(), b.getBalance()));
    }

}
