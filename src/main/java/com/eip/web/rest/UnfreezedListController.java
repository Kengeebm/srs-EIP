package com.eip.web.rest;

import com.eip.domain.UnfreezedList;
import com.eip.service.UnfreezedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UnfreezedListController {

    @Autowired
    UnfreezedListService unfreezedListService;

    @PostMapping("/timesheet/unfreez-list/post")
    public UnfreezedList save(@RequestBody UnfreezedList unfreezedList) {
        return unfreezedListService.save(unfreezedList);
    }

    @GetMapping("/timesheet/unfreez-list/all")
    public List<UnfreezedList> findAll() {
        return unfreezedListService.findAll();
    }

}
