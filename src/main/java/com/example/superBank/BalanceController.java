package com.example.superBank;

import com.example.superBank.model.TransferBalance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
@Slf4j
@RestController("/balance")
@AllArgsConstructor
public class BalanceController {

    private final BankService bankService ;

    @GetMapping ("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId) throws IllegalAccessException {
       return  bankService.getBalance(accountId);


    }
    @PostMapping("/add")
    public BigDecimal addMoney(@RequestBody TransferBalance transferBalance) {
        return  bankService.addMoney(transferBalance.getTo(),transferBalance.getAmour()) ;

    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferBalance transferBalance) {
        bankService.makeTransfer(transferBalance);


    }
    @ExceptionHandler(IllegalAccessError.class)
    public String handle(IllegalAccessException e){
        log.error(e.getMessage());
        return "mama ya slomalsya" ;
    }


}
