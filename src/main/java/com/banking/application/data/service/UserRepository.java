package com.banking.application.data.service;

import com.banking.application.DTO.UsersDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.banking.application.data.unitOfWork.IUnitOfWork;
import com.banking.application.views.bankinghistory.BankingHistoryView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    private final Connection conn;

    public UserRepository(IUnitOfWork unitOfWork) {
        conn = unitOfWork.getConnection();
    }

    public UsersDTO get(long id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE ID = ?")) {
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

    public UsersDTO findByUserName(String username) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users where uLogin = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsersDTO update(UsersDTO entity) {
        String sql = "UPDATE Users set ";
        sql += ("Balance = " + entity.getBalance());
        if(entity.getuLogin() != null)
            sql += (" AND uLogin = " + entity.getuLogin());
        if(entity.getuName() != null)
            sql += (" AND uName = " + entity.getuName());
        if(entity.getuPassword() != null)
            sql += (" AND uPassword = " + entity.getuPassword());


        sql += " WHERE ID = " + entity.getID();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
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
        try (PreparedStatement preparedStatement = conn.prepareStatement("delete from Users where ID = " + id)) {
            return;
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return;
    }

    public Page<UsersDTO> list(Pageable pageable) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UsersDTO> users = new ArrayList<>();
            int i = 1;
            while(resultSet.next())
            {
                users.add((UsersDTO)resultSet.getObject(i));
                i++;
            }
            final int start = (int)pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), users.size());
            final Page<UsersDTO> page = new PageImpl<>(users.subList(start, end), pageable, users.size());
            return page;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Page<UsersDTO> list(Pageable pageable, BankingHistoryView.Filters filter) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UsersDTO> users = new ArrayList<>();
            int i = 1;
            while(resultSet.next())
            {
                users.add((UsersDTO)resultSet.getObject(i));
                i++;
            }
            final int start = (int)pageable.getOffset();
            final int end = Math.min((start + pageable.getPageSize()), users.size());
            final Page<UsersDTO> page = new PageImpl<>(users.subList(start, end), pageable, users.size());
            return page;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private UsersDTO mapUser(ResultSet resultSet) throws SQLException {
        UsersDTO user = new UsersDTO();
        user.setID(resultSet.getLong("id"));
        user.setuLogin(resultSet.getString("uLogin"));
        user.setBalance(resultSet.getDouble("Balance"));
        user.setuPassword(resultSet.getString("uPassword"));
        user.setuName(resultSet.getString("uName"));
        user.setIsAdmin((Objects.equals(resultSet.getString("isAdmin"), "1")));
        return user;
    }
}
