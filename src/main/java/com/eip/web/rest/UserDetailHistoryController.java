package com.eip.web.rest;

import com.eip.domain.UserDetailHistory;
import com.eip.service.UserDetailHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user_history")
public class UserDetailHistoryController {

    Logger logger = LoggerFactory.getLogger(UserDetailHistoryController.class);

    @Autowired
    UserDetailHistoryService userDetailHistoryService;

    @PostMapping()
    public void create(@RequestBody UserDetailHistory userDetailHistory) {
        logger.warn("create start");
        userDetailHistoryService.save(userDetailHistory);
    }

    @GetMapping("/findall")
    public List<UserDetailHistory> findAll() {
        return userDetailHistoryService.findAll();
    }

}
