package adrikei.service;

import adrikei.service.core.BalanceRecord;
import adrikei.service.core.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balances")
public class BalancesController {

    @Autowired
    private BalanceService balanceService;
    @GetMapping("/{account}")
    public ResponseEntity<BalanceRecord> getBalance(@PathVariable String account) {
        return ResponseEntity.of(balanceService.getBalance(account));
    }
}
