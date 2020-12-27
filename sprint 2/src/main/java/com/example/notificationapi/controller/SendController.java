package com.example.notificationapi.controller;

import com.example.notificationapi.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Notification/send")
public class SendController {
    private final NotificationService notificationService;

    @Autowired
    public SendController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(path = "{id}")
    public void SendNotification(@PathVariable("id")int id){
        notificationService.SendNotification(id);
    }
}
