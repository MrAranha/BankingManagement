package com.banking.application.views.transactions;

import com.banking.application.data.service.SamplePersonService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionAddHandler {
    private final SamplePersonService _samplePersonService;
    public TransactionAddHandler(SamplePersonService samplePersonService) {
        _samplePersonService = samplePersonService;
    }
    public void addTransaction(String sender, String Receiver, Date Date, Double MoneySent){
        _samplePersonService.add(sender, Receiver, Date, MoneySent);
    }
}
