package com.blue.controller;

import com.blue.entity.Receive;
import com.blue.service.SendMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public class WorkerController {

    @Autowired
    private SendMsgService sendMsgService;

    @PostMapping("/msg")
    public void ReceiveMsg(@RequestBody Receive msg) {
        sendMsgService.sendMsg(msg);
    }



}
