package com.eip.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.eip.domain.ContactAdmin;

@Repository
public interface ContactAdminRepository extends MongoRepository<ContactAdmin, String> {

    Optional<ContactAdmin> findByEmpId(String empId);

    void deleteContactAdminByEmpId(String empId);


}
