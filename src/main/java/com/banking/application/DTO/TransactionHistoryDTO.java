package com.banking.application.DTO;

import com.banking.application.data.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class TransactionHistoryDTO extends AbstractEntity {
    public TransactionHistoryDTO(){

    }
    public TransactionHistoryDTO(Long Id, String Sender, String Receiver, Double MoneySent, Date Date) {
        this.ID = Id;
        this.Sender = Sender;
        this.Receiver = Receiver;
        this.MoneySent = MoneySent;
        this.Date = Date;
    }
    @Id
    private Long ID;
    private String Sender;
    private String Receiver;
    private Double MoneySent;
    public Date Date;
    private boolean important;

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public boolean isImportant() {
        return important;
    }
    public void setImportant(boolean important) {
        this.important = important;
    }
    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public Double getMoneySent() {
        return MoneySent;
    }

    public void setMoneySent(Double moneySent) {
        MoneySent = moneySent;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
