package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "timesheet_date_status")
public class TimeSheetDateStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String status;

    @Field("date_time")
    private LocalDate date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TimeSheetStatus{" +
            "id='" + id + '\'' +
            ", date=" + date +
            ", status='" + status + '\'' +
            '}';
    }
}
