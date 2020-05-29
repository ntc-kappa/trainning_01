package com.tas.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "log_delete_user_failure", schema = "trainning01", catalog = "")
public class LogDeleteUserFailure {
    private int id;
    private String messeage;
    private String stackTrace;
    private Date occurredAt;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "messeage", nullable = true, length = -1)
    public String getMesseage() {
        return messeage;
    }

    public void setMesseage(String messeage) {
        this.messeage = messeage;
    }

    @Basic
    @Column(name = "stackTrace", nullable = true, length = -1)
    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Basic
    @Column(name = "occurred_at", nullable = false)
    public Date getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Date occurredAt) {
        this.occurredAt = occurredAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogDeleteUserFailure that = (LogDeleteUserFailure) o;
        return id == that.id &&
                Objects.equals(messeage, that.messeage) &&
                Objects.equals(stackTrace, that.stackTrace) &&
                Objects.equals(occurredAt, that.occurredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messeage, stackTrace, occurredAt);
    }
}
