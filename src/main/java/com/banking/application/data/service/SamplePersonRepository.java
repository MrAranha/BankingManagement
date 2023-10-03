package com.banking.application.data.service;

import com.banking.application.DTO.TransactionHistoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.banking.application.DTO.UsersDTO;
import com.banking.application.data.unitOfWork.IUnitOfWork;
import com.banking.application.views.bankinghistory.BankingHistoryView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class SamplePersonRepository {

    private final Connection conn;
    public SamplePersonRepository(IUnitOfWork unitOfWork) {
        conn = unitOfWork.getConnection();
    }

    public void update(TransactionHistoryDTO entity) {
        String sql = "UPDATE TransactionHistory set ";
        sql += ("Receiver = " + entity.getReceiver());
        if(entity.getSender() != null)
            sql += (" AND Sender = " + entity.getSender());
        if(entity.getMoneySent() != null)
            sql += (" AND MoneySent = " + entity.getMoneySent());


        sql += " WHERE ID = " + entity.getID();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public void add(String sender, String Receiver, Date Date, Double MoneySent) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("insert into TransactionHistory (Receiver, Sender, Date, MoneySent, important) values (?, ?, ?, ?, false)")) {
            preparedStatement.setString(1, Receiver);
            preparedStatement.setString(2, sender);
            preparedStatement.setDate(3, new java.sql.Date(Date.getTime()));
            preparedStatement.setDouble(4, MoneySent);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
    public TransactionHistoryDTO get(long id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM TransactionHistory WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(long id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("delete from TransactionHistory where ID = " + id)) {
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public Page<TransactionHistoryDTO> list(Pageable pageable) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM TransactionHistory")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TransactionHistoryDTO> users = new ArrayList<>();
            while(resultSet.next())
            {
                users.add(mapUser(resultSet));
            }
            final int start = (int)pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), users.size());
            final Page<TransactionHistoryDTO> page = new PageImpl<>(users.subList(start, end), pageable, users.size());
            return page;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Page<TransactionHistoryDTO> list(Pageable pageable, BankingHistoryView.Filters filter) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM TransactionHistory")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TransactionHistoryDTO> users = new ArrayList<>();
            while(resultSet.next())
            {
                users.add(mapUser(resultSet));
            }
            final int start = (int)pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), users.size());
            final Page<TransactionHistoryDTO> page = new PageImpl<>(users.subList(start, end), pageable, users.size());
            return page;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TransactionHistoryDTO mapUser(ResultSet resultSet) throws SQLException {
        TransactionHistoryDTO user = new TransactionHistoryDTO();
        user.setID(resultSet.getInt("ID"));
        user.setDate(resultSet.getDate("Date"));
        user.setReceiver(resultSet.getString("Receiver"));
        user.setMoneySent(resultSet.getDouble("MoneySent"));
        user.setSender(resultSet.getString("Sender"));
        return user;
    }
}