package com.example.notificationapi.controller;

import com.example.notificationapi.model.Notification;
import com.example.notificationapi.model.Type;
import com.example.notificationapi.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/Notification/")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public boolean SendNotification(@Validated @NonNull @RequestBody Notification notification){
        return notificationService.SendNotification(notification);
    }

    @GetMapping(path ="{user}")
    public List<Notification> GetNotificationOfUser(@PathVariable("user") String user){

        return notificationService.GetNotificationOfUser(user);
    }
    @GetMapping
    public List<Notification> GetNotificationOfUsers(){
        return notificationService.GetNotificationsOfUsers();
    }
}
