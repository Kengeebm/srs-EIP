package com.eip.domain;

import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "user_leave")
public class UserLeave implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Unique
    @Field("employee_id")
    private String employeeId;

    @Field("total_leaves")
    private int totalLeaves;

    @Field("leave_balance")
    private int leaveBalance;

    @Field("sick_leaves")
    private int sickLeaves;

    @Field("annual_leaves")
    private int annualLeaves;

    public UserLeave() {
        super();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public void setTotalLeaves(int totalLeaves) {
        this.totalLeaves = totalLeaves;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public int getSickLeaves() {
        return sickLeaves;
    }

    public void setSickLeaves(int sickLeaves) {
        this.sickLeaves = sickLeaves;
    }

    public int getAnnualLeaves() {
        return annualLeaves;
    }

    public void setAnnualLeaves(int annualLeaves) {
        this.annualLeaves = annualLeaves;
    }

    @Override
    public String toString() {
        return "UserLeave{" +
            "id='" + id + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", totalLeaves=" + totalLeaves +
            ", leaveBalance=" + leaveBalance +
            ", sickLeaves=" + sickLeaves +
            ", annualLeaves=" + annualLeaves +
            '}';
    }
}
