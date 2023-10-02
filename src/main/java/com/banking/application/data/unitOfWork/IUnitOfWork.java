package com.banking.application.data.unitOfWork;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Service
public class IUnitOfWork {

    private final Connection conn;

    public Connection getConnection()
    {
        return conn;
    }

    public IUnitOfWork() throws SQLException {
        conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/banking?user=root&password=admin");
    }
}
