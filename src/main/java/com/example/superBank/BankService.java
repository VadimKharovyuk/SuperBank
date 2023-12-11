package com.example.superBank;

import com.example.superBank.model.TransferBalance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BankService {
    private  final BalanceRepository repository ;
    public BigDecimal getBalance(Long accountId) throws IllegalAccessException {
         BigDecimal balance = repository.getBalanceId(accountId);
         if (balance==null){
             throw new IllegalAccessException() ;
         }
         return balance ;
    }

    public BigDecimal addMoney(Long to, BigDecimal amour) {
        BigDecimal currendBalance  = repository.getBalanceId(to);
        if (currendBalance ==null){
            repository.save(to,amour);
            return amour;
        }else {
            BigDecimal updateBalance= currendBalance.add(amour) ;
            repository.save(to,updateBalance);
            return updateBalance ;
        }
    }

    public void makeTransfer(TransferBalance transferBalance) {
        BigDecimal fromBalance = repository.getBalanceId(transferBalance.getFrom());
        BigDecimal toBalance = repository.getBalanceId(transferBalance.getTo());
        if (fromBalance== null|| toBalance == null) throw  new IllegalArgumentException("no ids") ;
        if (transferBalance.getAmour().compareTo(fromBalance)>0 ) throw new IllegalArgumentException("no money") ;

           final BigDecimal updateFromBalance= fromBalance.subtract(transferBalance.getAmour());
           final BigDecimal updateToBalance= toBalance.add(transferBalance.getAmour());

           repository.save(transferBalance.getFrom(),updateFromBalance);
           repository.save(transferBalance.getTo(),updateToBalance);


        }
}
