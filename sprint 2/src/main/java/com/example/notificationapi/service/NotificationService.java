package com.example.notificationapi.service;



import com.example.notificationapi.dal.INotificationDataAccessLayer;
import com.example.notificationapi.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final INotificationDataAccessLayer notificationDAL;

    @Autowired
    public NotificationService(@Qualifier("MySQLDb") INotificationDataAccessLayer notificationDAL) {
        this.notificationDAL = notificationDAL;
    }

    public int AddNotification(Notification notification) throws SQLException {
        return notificationDAL.AddNotification(notification);
    }

    public List<Notification> GetNotification() throws SQLException {
        return notificationDAL.GetNotification();
    }

    public Optional<Notification> GetNotificationByID(int id){
        return notificationDAL.GetNotificationByID(id);
    }

    public boolean DeleteNotification (int id){
        return notificationDAL.DeleteNotification(id);
    }

    public int UpdateNotification(int id ,Notification notification){
        return notificationDAL.UpdateNotification(id, notification);
    }

    public int SendNotification(int id){
        return notificationDAL.sendNotification(id);
    }


}