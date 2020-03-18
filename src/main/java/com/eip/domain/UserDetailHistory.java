package com.eip.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "user_detail_history")
public class UserDetailHistory implements Serializable {

    @Id
    String id;

    String empId;

    String login;

    String firstName;

    String lastName;

    String email;

    String status;

    String visitPage;

    LocalDate loginTime;

    String modifyContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDate loginTime) {
        this.loginTime = loginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVisitPage() {
        return visitPage;
    }

    public void setVisitPage(String visitPage) {
        this.visitPage = visitPage;
    }

    public String getModifyContent() {
        return modifyContent;
    }


    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent;
    }

    @Override
    public String toString() {
        return "UserDetailHistory{" +
            "id='" + id + '\'' +
            ", empId='" + empId + '\'' +
            ", login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", loginStatus='" + status + '\'' +
            ", loginTime=" + loginTime +
            ", visitPage=" + visitPage +
            ", modifyContent='" + modifyContent + '\'' +
            '}';
    }
}
