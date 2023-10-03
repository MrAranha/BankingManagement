package com.banking.application.data.service;

import com.banking.application.DTO.TransactionHistoryDTO;
import com.banking.application.DTO.UsersDTO;
import com.banking.application.views.bankinghistory.BankingHistoryView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Filter;

@Service
public class SamplePersonService {
    private final SamplePersonRepository userRepository;
    public SamplePersonService(SamplePersonRepository userRepository){
        this.userRepository = userRepository;
    }
    public TransactionHistoryDTO get(long id) {
        return userRepository.get(id);
    }
    public void delete(long id) {
        userRepository.delete(id);
    };
    public void update(TransactionHistoryDTO id) {
        userRepository.update(id);
    };
    public void add(String sender, String Receiver, Date Date, Double MoneySent) {
        userRepository.add(sender, Receiver, Date, MoneySent);
    };
    public Page<TransactionHistoryDTO> list(Pageable pageable) {
        return userRepository.list(pageable);
    }

    public Page<TransactionHistoryDTO> list(Pageable pageable, BankingHistoryView.Filters filter) {
        return userRepository.list(pageable, filter);
    }
}