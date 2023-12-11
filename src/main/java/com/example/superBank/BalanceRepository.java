package com.example.superBank;

import lombok.val;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BalanceRepository {
    private final Map<Long, BigDecimal> storage = new HashMap<> (Map.of(1L,BigDecimal.TEN));


    public BigDecimal getBalanceId(Long accountId) {
        return storage.get(accountId);
    }


    public  void  save(Long id, BigDecimal amour) {
    storage.put(id,amour) ;
    }
}
