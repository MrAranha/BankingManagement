package com.banking.application.data.service;

import com.banking.application.DTO.UsersDTO;
import com.banking.application.views.bankinghistory.BankingHistoryView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Filter;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public UsersDTO findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    public UsersDTO get(long id) {
        return userRepository.get(id);
    }
    public UsersDTO update(UsersDTO entity) {
        return userRepository.update(entity);
    }
    public void delete(long id) {
        userRepository.delete(id);
    };
    public Page<UsersDTO> list(Pageable pageable) {
        return userRepository.list(pageable);
    }

    public Page<UsersDTO> list(Pageable pageable, BankingHistoryView.Filters filter) {
        return userRepository.list(pageable, filter);
    }
}