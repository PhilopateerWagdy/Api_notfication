package com.example.notificationapi.controller;

import com.example.notificationapi.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.notificationapi.service.NotificationService;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("api/Notification")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public void AddNotification(@Validated @NonNull @RequestBody Notification notification) throws SQLException {
        notificationService.AddNotification(notification);
    }

    @GetMapping
    public List<Notification> GetNotification() throws SQLException {
        return notificationService.GetNotification();
    }

    @GetMapping(path ="{id}")
    public Notification GetNotificationByID(@PathVariable("id") int id){
        return notificationService.GetNotificationByID(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void DeleteNotification(@PathVariable("id") int id){
        notificationService.DeleteNotification(id);
    }

    @PutMapping(path = "{id}")
    public void UpdateNotification(@PathVariable("id") int id ,@Validated @NonNull @RequestBody Notification notification){
        notificationService.UpdateNotification(id, notification);
    }



}
