package com.eip.web.rest;

import com.eip.domain.ContactAdmin;
import com.eip.service.ContactAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/request")
public class ContactAdminController {

    Logger logger = LoggerFactory.getLogger(ContactAdminController.class);

    @Autowired
    ContactAdminService contactadminService;

    @PostMapping()
    public ContactAdmin create(@RequestBody ContactAdmin contactAdmin) {
        logger.warn("create start");
        return contactadminService.createRequest(contactAdmin);
    }

    @GetMapping("/findAll")
    public List<ContactAdmin> findAll() {
        return contactadminService.findAllRequest();
    }

    @GetMapping("/{empId}")
    public Optional<ContactAdmin> findByEmpId(@PathVariable String empId) {
        return contactadminService.findContactAdminByEmpId(empId);
    }

    @DeleteMapping("/{empId}")
    public void deleteById(@PathVariable String empId) {
        contactadminService.deleteContactAdminByEmpId(empId);

    }

}
