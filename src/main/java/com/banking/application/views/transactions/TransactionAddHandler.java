package com.banking.application.views.transactions;

import com.banking.application.DTO.TransactionHistoryDTO;
import com.banking.application.data.service.SamplePersonService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionAddHandler {
    private final SamplePersonService _samplePersonService;
    public TransactionAddHandler(SamplePersonService samplePersonService) {
        _samplePersonService = samplePersonService;
    }
    public void addTransaction(Long id, String sender, String Receiver, Date Date, Double MoneySent, boolean isDelete, boolean isUpdate){
        if(isDelete) {
            _samplePersonService.delete(id);
            return;
        }
        if(isUpdate) {
            _samplePersonService.update(new TransactionHistoryDTO(id, sender, Receiver, MoneySent, Date));
            return;
        }
        _samplePersonService.add(sender, Receiver, Date, MoneySent);
    }
}
